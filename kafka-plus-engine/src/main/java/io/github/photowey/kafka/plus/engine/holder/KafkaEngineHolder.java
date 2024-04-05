/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.photowey.kafka.plus.engine.holder;

import io.github.photowey.kafka.plus.engine.KafkaEngine;
import io.github.photowey.kafka.plus.engine.KafkaEngineImpl;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@code KafkaEngineHolder}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public enum KafkaEngineHolder {

    INSTANCE,

    ;

    private final AtomicBoolean initialized = new AtomicBoolean(false);

    private KafkaEngine kafkaEngine;
    private final Lock lock = new ReentrantLock();

    // ----------------------------------------------------------------

    private static class KafkaEngineFactory implements Serializable {
        private static final KafkaEngine INSTANCE = new KafkaEngineImpl();
    }

    // ----------------------------------------------------------------

    public void kafkaEngine(KafkaEngine kafkaEngine) {
        this.kafkaEngine(kafkaEngine, false);
    }

    public void kafkaEngine(KafkaEngine kafkaEngine, boolean refresh) {
        if (refresh) {
            this.initialized.compareAndSet(false, true);
            this.kafkaEngine = kafkaEngine;

            return;
        }

        if (this.initialized.compareAndSet(false, true)) {
            this.kafkaEngine = kafkaEngine;
        }
    }

    public KafkaEngine kafkaEngine() {
        if (null == this.kafkaEngine) {
            this.lock.lock();
            try {
                if (null == this.kafkaEngine) {
                    this.kafkaEngine(KafkaEngineFactory.INSTANCE, true);
                }
            } finally {
                this.lock.unlock();
            }
        }

        return this.kafkaEngine;
    }
}
