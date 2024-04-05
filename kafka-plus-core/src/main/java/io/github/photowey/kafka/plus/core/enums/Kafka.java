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
package io.github.photowey.kafka.plus.core.enums;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * {@code Kafka}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public enum Kafka {

    ;

    public enum Mode {

        STANDALONE,
        CLUSTER,

        ;

    }

    public enum Bootstrap {

        ;

        public enum Server {

            ADDRESS(CommonClientConfigs.BOOTSTRAP_SERVERS_DOC, AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG),
            DEFAULT_LOCALHOST("bootstrap.servers.default.localhost", "localhost:9092"),
            DEFAULT_LOOPBACK("bootstrap.servers.default.loopback", "127.0.0.1:9092"),

            ;

            private String doc;
            private String value;

            Server(String doc, String value) {
                this.doc = doc;
                this.value = value;
            }

            public String doc() {
                return this.doc;
            }

            public String value() {
                return this.value;
            }
        }
    }

    public enum Consumer {

        KEY_DESERIALIZER(ConsumerConfig.KEY_DESERIALIZER_CLASS_DOC, ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG),
        VALUE_DESERIALIZER(ConsumerConfig.VALUE_DESERIALIZER_CLASS_DOC, ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG),

        AUTO_OFFSET_RESET(ConsumerConfig.AUTO_OFFSET_RESET_DOC, ConsumerConfig.AUTO_OFFSET_RESET_CONFIG),

        GROUP_ID(CommonClientConfigs.GROUP_ID_DOC, ConsumerConfig.GROUP_ID_CONFIG),
        AUTO_COMMIT_ENABLED("If true the consumer's offset will be periodically committed in the background.", ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG),

        ;

        private String doc;
        private String key;

        Consumer(String doc, String key) {
            this.doc = doc;
            this.key = key;
        }

        public String doc() {
            return this.doc;
        }

        public String key() {
            return this.key;
        }

        // ----------------------------------------------------------------

        public enum AutoOffsetReset {

            EARLIEST("automatically reset the offset to the earliest offset", "earliest"),
            LATEST("automatically reset the offset to the earliest offset", "latest"),
            NONE("throw exception to the consumer if no previous offset is found for the consumer's group", "none"),
            ANYTHING("throw exception to the consumer", "anything else"),

            ;

            private String doc;
            private String value;

            AutoOffsetReset(String doc, String value) {
                this.doc = doc;
                this.value = value;
            }

            public String doc() {
                return this.doc;
            }

            public String value() {
                return this.value;
            }
        }
    }

    public enum Producer {

        KEY_SERIALIZER(ProducerConfig.KEY_SERIALIZER_CLASS_DOC, ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG),
        VALUE_DESERIALIZER(ProducerConfig.VALUE_SERIALIZER_CLASS_DOC, ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG),

        ;

        private String doc;
        private String key;

        Producer(String doc, String key) {
            this.doc = doc;
            this.key = key;
        }

        public String doc() {
            return this.doc;
        }

        public String key() {
            return this.key;
        }
    }

}
