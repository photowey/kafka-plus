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
package io.github.photowey.kafka.plus.engine;

import io.github.photowey.kafka.plus.engine.holder.KafkaEngineHolder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * {@code AbstractEngine}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public abstract class AbstractEngine implements KafkaEngine {

    protected final ConcurrentHashMap<Class<?>, Object> sharedObjects = new ConcurrentHashMap<>();

    // ----------------------------------------------------------------

    public <T> void setSharedObject(Class<T> sharedType, T t) {
        this.sharedObjects.put(sharedType, t);
    }

    // ----------------------------------------------------------------

    public <T> T getSharedObject(Class<T> sharedType) {
        Object target = this.sharedObjects.get(sharedType);
        return (T) target;
    }

    public <T> T getSharedObject(Class<T> sharedType, Supplier<T> fx) {
        Object target = this.sharedObjects.computeIfAbsent(sharedType, (x) -> {
            T t = fx.get();
            // Inject KafkaEngine if necessary.
            if (t instanceof KafkaEngineAware) {
                ((KafkaEngineAware) t).setKafkaEngine(KafkaEngineHolder.INSTANCE.kafkaEngine());
            }

            return t;
        });

        return (T) target;
    }

    // ----------------------------------------------------------------

    public void cleanSharedObjects() {
        this.sharedObjects.clear();
    }
}