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

import io.github.photowey.kafka.plus.core.enums.Kafka;
import io.github.photowey.kafka.plus.engine.holder.KafkaEngineHolder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * {@code LocalTest}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public abstract class LocalTest {

    protected static final String DEFAULT_BOOTSTRAP_SERVERS = Kafka.Bootstrap.Server.DEFAULT_LOCALHOST.value();
    protected static final String DEFAULT_HELLO_WORLD_TOPIC = "io.github.photowey.topic.helloworld";
    protected static final String DEFAULT_HELLO_WORLD_GROUP = "io.github.photowey.group.helloworld";

    protected KafkaEngine kafkaEngine() {
        return KafkaEngineHolder.INSTANCE.kafkaEngine();
    }

    protected String defaultTopic() {
        return DEFAULT_HELLO_WORLD_TOPIC;
    }

    protected String defaultGroup() {
        return DEFAULT_HELLO_WORLD_GROUP;
    }

    protected String defaultBoostrapServers() {
        return DEFAULT_BOOTSTRAP_SERVERS;
    }

    protected void testBoostrapServers(Map<String, Object> configs) {
        if (null == configs.get(Kafka.Bootstrap.Server.ADDRESS.value())) {
            throw new RuntimeException("The bootstrap server address can't be none/empty");
        }
    }

    protected static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}