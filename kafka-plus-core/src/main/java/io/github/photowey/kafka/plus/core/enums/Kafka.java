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
import org.apache.kafka.common.IsolationLevel;

/**
 * {@code Kafka}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/05
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
            DEFAULT_LOCALHOST("default.localhost.bootstrap.servers", "localhost:9092"),
            DEFAULT_LOOPBACK("default.loopback.bootstrap.servers", "127.0.0.1:9092"),

            ;

            private final String doc;
            private final String value;

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

        // ---------------------------------------------------------------- 3.7.0.1.4

        GROUP_INSTANCE_ID(CommonClientConfigs.GROUP_INSTANCE_ID_DOC, ConsumerConfig.GROUP_INSTANCE_ID_CONFIG),
        ISOLATION_LEVEL(ConsumerConfig.ISOLATION_LEVEL_DOC, ConsumerConfig.ISOLATION_LEVEL_CONFIG),
        PARTITION_ASSIGNMENT_STRATEGY(Document.Consumer.PARTITION_ASSIGNMENT_STRATEGY_DOC, ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG),

        ;

        private final String doc;
        private final String key;

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

            private final String doc;
            private final String value;

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

        public enum Isolation {

            READ_COMMITTED("consumer.poll() will only return transactional messages which have been committed", IsolationLevel.READ_COMMITTED.toString()),
            READ_UNCOMMITTED("consumer.poll() will return all messages, even transactional messages which have been aborted", IsolationLevel.READ_UNCOMMITTED.toString()),

            ;

            private final String doc;
            private final String value;

            Isolation(String doc, String value) {
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

        INTERCEPTOR(ProducerConfig.INTERCEPTOR_CLASSES_DOC, ProducerConfig.INTERCEPTOR_CLASSES_CONFIG),
        PARTITIONER("Determines which partition to send a record to when records are produced.", ProducerConfig.PARTITIONER_CLASS_CONFIG),

        ACKS("The number of acknowledgments the producer requires the leader to have received before considering a request complete.", ProducerConfig.ACKS_CONFIG),
        RETRIES("Setting a value greater than zero will cause the client to resend any record whose send fails with a potentially transient error.", ProducerConfig.RETRIES_CONFIG),

        BATCH_SIZE("The producer will attempt to batch records together into fewer requests whenever multiple records are being sent to the same partition.", ProducerConfig.BATCH_SIZE_CONFIG),
        BUFFER_MEMORY_SIZE("The total bytes of memory the producer can use to buffer records waiting to be sent to the server.", ProducerConfig.BUFFER_MEMORY_CONFIG),

        LINGER_MS("The producer groups together any records that arrive in between request transmissions into a single batched request.", ProducerConfig.LINGER_MS_CONFIG),
        MAX_BLOCK_MS("The configuration controls how long the KafkaProducer send()/... methods will block.", ProducerConfig.MAX_BLOCK_MS_CONFIG),
        REQUEST_TIMEOUT_MS(CommonClientConfigs.REQUEST_TIMEOUT_MS_DOC, ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG),
        DELIVERY_TIMEOUT_MS("An upper bound on the time to report success or failure", ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG),

        /**
         * @see Producer#ACKS (== 0)
         * @see Producer#RETRIES (&gt; 0)
         */
        IDEMPOTENCE_ENABLED(ProducerConfig.ENABLE_IDEMPOTENCE_DOC, ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG),

        ;

        private final String doc;
        private final String key;

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

        public enum Acks {

            IGNORE("If set to zero then the producer will not wait for any acknowledgment from the server at all", "0"),
            LEADER("This will mean the leader will write the record to its local log but will respond without awaiting full acknowledgement from all followers", "1"),
            ALL("This means the leader will wait for the full set of in-sync replicas to acknowledge the record.", "-1"),

            ;

            private final String doc;
            private final String value;

            Acks(String doc, String value) {
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

    /**
     * @since 3.7.0.1.4
     */
    interface Document {

        interface Server {}

        interface Consumer {
            String PARTITION_ASSIGNMENT_STRATEGY_DOC = "A list of class names or class types, " +
                    "ordered by preference, of supported partition assignment strategies that the client will use to distribute " +
                    "partition ownership amongst consumer instances when group management is used. Available options are:" +
                    "<ul>" +
                    "<li><code>org.apache.kafka.clients.consumer.RangeAssignor</code>: Assigns partitions on a per-topic basis.</li>" +
                    "<li><code>org.apache.kafka.clients.consumer.RoundRobinAssignor</code>: Assigns partitions to consumers in a round-robin fashion.</li>" +
                    "<li><code>org.apache.kafka.clients.consumer.StickyAssignor</code>: Guarantees an assignment that is " +
                    "maximally balanced while preserving as many existing partition assignments as possible.</li>" +
                    "<li><code>org.apache.kafka.clients.consumer.CooperativeStickyAssignor</code>: Follows the same StickyAssignor " +
                    "logic, but allows for cooperative rebalancing.</li>" +
                    "</ul>" +
                    "<p>The default assignor is [RangeAssignor, CooperativeStickyAssignor], which will use the RangeAssignor by default, " +
                    "but allows upgrading to the CooperativeStickyAssignor with just a single rolling bounce that removes the RangeAssignor from the list.</p>" +
                    "<p>Implementing the <code>org.apache.kafka.clients.consumer.ConsumerPartitionAssignor</code> " +
                    "interface allows you to plug in a custom assignment strategy.</p>";
        }

        interface Producer {}
    }
}
