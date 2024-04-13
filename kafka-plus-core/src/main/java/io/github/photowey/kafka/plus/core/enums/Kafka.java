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
 * @version 3.7.0.1.0
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

        AUTO_COMMIT_ENABLED(Document.Consumer.ENABLE_AUTO_COMMIT_DOC, ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG),

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
        PARTITIONER(Document.Producer.PARTITIONER_CLASS_DOC, ProducerConfig.PARTITIONER_CLASS_CONFIG),

        ACKS(Document.Producer.ACKS_DOC, ProducerConfig.ACKS_CONFIG),
        RETRIES(Document.Producer.RETRIES_DOC, ProducerConfig.RETRIES_CONFIG),

        BATCH_SIZE(Document.Producer.BATCH_SIZE_DOC, ProducerConfig.BATCH_SIZE_CONFIG),
        BUFFER_MEMORY_SIZE(Document.Producer.BATCH_SIZE_DOC, ProducerConfig.BUFFER_MEMORY_CONFIG),

        LINGER_MS(Document.Producer.LINGER_MS_DOC, ProducerConfig.LINGER_MS_CONFIG),
        MAX_BLOCK_MS(Document.Producer.MAX_BLOCK_MS_DOC, ProducerConfig.MAX_BLOCK_MS_CONFIG),
        REQUEST_TIMEOUT_MS(CommonClientConfigs.REQUEST_TIMEOUT_MS_DOC, ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG),
        DELIVERY_TIMEOUT_MS(Document.Producer.DELIVERY_TIMEOUT_MS_DOC, ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG),

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
     * Copy from kafka.
     *
     * @since 3.7.0.1.4
     */
    interface Document {

        interface Server {}

        /**
         * @see ConsumerConfig
         */
        interface Consumer {

            String ENABLE_AUTO_COMMIT_DOC = "If true the consumer's offset will be periodically committed in the background.";

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

        /**
         * @see ProducerConfig
         */
        interface Producer {
            String PARTITIONER_CLASS_DOC = "Determines which partition to send a record to when records are produced. Available options are:" +
                    "<ul>" +
                    "<li>If not set, the default partitioning logic is used. " +
                    "This strategy send records to a partition until at least " + ProducerConfig.BATCH_SIZE_CONFIG + " bytes is produced to the partition. It works with the strategy:" +
                    "<p> 1) If no partition is specified but a key is present, choose a partition based on a hash of the key." +
                    "<p> 2) If no partition or key is present, choose the sticky partition that changes when at least " + ProducerConfig.BATCH_SIZE_CONFIG + " bytes are produced to the partition." +
                    "</li>" +
                    "<li><code>org.apache.kafka.clients.producer.RoundRobinPartitioner</code>: A partitioning strategy where " +
                    "each record in a series of consecutive records is sent to a different partition, regardless of whether the 'key' is provided or not, " +
                    "until partitions run out and the process starts over again. Note: There's a known issue that will cause uneven distribution when a new batch is created. " +
                    "See KAFKA-9965 for more detail." +
                    "</li>" +
                    "</ul>" +
                    "<p>Implementing the <code>org.apache.kafka.clients.producer.Partitioner</code> interface allows you to plug in a custom partitioner.";

            String ACKS_DOC = "The number of acknowledgments the producer requires the leader to have received before considering a request complete. This controls the "
                    + " durability of records that are sent. The following settings are allowed: "
                    + " <ul>"
                    + " <li><code>acks=0</code> If set to zero then the producer will not wait for any acknowledgment from the"
                    + " server at all. The record will be immediately added to the socket buffer and considered sent. No guarantee can be"
                    + " made that the server has received the record in this case, and the <code>retries</code> configuration will not"
                    + " take effect (as the client won't generally know of any failures). The offset given back for each record will"
                    + " always be set to <code>-1</code>."
                    + " <li><code>acks=1</code> This will mean the leader will write the record to its local log but will respond"
                    + " without awaiting full acknowledgement from all followers. In this case should the leader fail immediately after"
                    + " acknowledging the record but before the followers have replicated it then the record will be lost."
                    + " <li><code>acks=all</code> This means the leader will wait for the full set of in-sync replicas to"
                    + " acknowledge the record. This guarantees that the record will not be lost as long as at least one in-sync replica"
                    + " remains alive. This is the strongest available guarantee. This is equivalent to the acks=-1 setting."
                    + "</ul>"
                    + "<p>"
                    + "Note that enabling idempotence requires this config value to be 'all'."
                    + " If conflicting configurations are set and idempotence is not explicitly enabled, idempotence is disabled.";

            String RETRIES_DOC = "Setting a value greater than zero will cause the client to resend any record whose send fails with a potentially transient error."
                    + " Note that this retry is no different than if the client resent the record upon receiving the error."
                    + " Produce requests will be failed before the number of retries has been exhausted if the timeout configured by"
                    + " <code>" + ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG + "</code> expires first before successful acknowledgement. Users should generally"
                    + " prefer to leave this config unset and instead use <code>" + ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG + "</code> to control"
                    + " retry behavior."
                    + "<p>"
                    + "Enabling idempotence requires this config value to be greater than 0."
                    + " If conflicting configurations are set and idempotence is not explicitly enabled, idempotence is disabled."
                    + "<p>"
                    + "Allowing retries while setting <code>enable.idempotence</code> to <code>false</code> and <code>" + ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION + "</code> to greater than 1 will potentially change the"
                    + " ordering of records because if two batches are sent to a single partition, and the first fails and is retried but the second"
                    + " succeeds, then the records in the second batch may appear first.";

            String BATCH_SIZE_DOC = "The producer will attempt to batch records together into fewer requests whenever multiple records are being sent"
                    + " to the same partition. This helps performance on both the client and the server. This configuration controls the "
                    + "default batch size in bytes. "
                    + "<p>"
                    + "No attempt will be made to batch records larger than this size. "
                    + "<p>"
                    + "Requests sent to brokers will contain multiple batches, one for each partition with data available to be sent. "
                    + "<p>"
                    + "A small batch size will make batching less common and may reduce throughput (a batch size of zero will disable "
                    + "batching entirely). A very large batch size may use memory a bit more wastefully as we will always allocate a "
                    + "buffer of the specified batch size in anticipation of additional records."
                    + "<p>"
                    + "Note: This setting gives the upper bound of the batch size to be sent. If we have fewer than this many bytes accumulated "
                    + "for this partition, we will 'linger' for the <code>linger.ms</code> time waiting for more records to show up. "
                    + "This <code>linger.ms</code> setting defaults to 0, which means we'll immediately send out a record even the accumulated "
                    + "batch size is under this <code>batch.size</code> setting.";

            String BUFFER_MEMORY_DOC = "The total bytes of memory the producer can use to buffer records waiting to be sent to the server. If records are "
                    + "sent faster than they can be delivered to the server the producer will block for <code>" + ProducerConfig.MAX_BLOCK_MS_CONFIG + "</code> after which it will throw an exception."
                    + "<p>"
                    + "This setting should correspond roughly to the total memory the producer will use, but is not a hard bound since "
                    + "not all memory the producer uses is used for buffering. Some additional memory will be used for compression (if "
                    + "compression is enabled) as well as for maintaining in-flight requests.";

            String LINGER_MS_DOC = "The producer groups together any records that arrive in between request transmissions into a single batched request. "
                    + "Normally this occurs only under load when records arrive faster than they can be sent out. However in some circumstances the client may want to "
                    + "reduce the number of requests even under moderate load. This setting accomplishes this by adding a small amount "
                    + "of artificial delay&mdash;that is, rather than immediately sending out a record, the producer will wait for up to "
                    + "the given delay to allow other records to be sent so that the sends can be batched together. This can be thought "
                    + "of as analogous to Nagle's algorithm in TCP. This setting gives the upper bound on the delay for batching: once "
                    + "we get <code>" + ProducerConfig.BATCH_SIZE_CONFIG + "</code> worth of records for a partition it will be sent immediately regardless of this "
                    + "setting, however if we have fewer than this many bytes accumulated for this partition we will 'linger' for the "
                    + "specified time waiting for more records to show up. This setting defaults to 0 (i.e. no delay). Setting <code>" + ProducerConfig.LINGER_MS_CONFIG + "=5</code>, "
                    + "for example, would have the effect of reducing the number of requests sent but would add up to 5ms of latency to records sent in the absence of load.";

            String MAX_BLOCK_MS_DOC = "The configuration controls how long the <code>KafkaProducer</code>'s <code>send()</code>, <code>partitionsFor()</code>, "
                    + "<code>initTransactions()</code>, <code>sendOffsetsToTransaction()</code>, <code>commitTransaction()</code> "
                    + "and <code>abortTransaction()</code> methods will block. "
                    + "For <code>send()</code> this timeout bounds the total time waiting for both metadata fetch and buffer allocation "
                    + "(blocking in the user-supplied serializers or partitioner is not counted against this timeout). "
                    + "For <code>partitionsFor()</code> this timeout bounds the time spent waiting for metadata if it is unavailable. "
                    + "The transaction-related methods always block, but may timeout if "
                    + "the transaction coordinator could not be discovered or did not respond within the timeout.";

            String DELIVERY_TIMEOUT_MS_DOC = "An upper bound on the time to report success or failure "
                    + "after a call to <code>send()</code> returns. This limits the total time that a record will be delayed "
                    + "prior to sending, the time to await acknowledgement from the broker (if expected), and the time allowed "
                    + "for retriable send failures. The producer may report failure to send a record earlier than this config if "
                    + "either an unrecoverable error is encountered, the retries have been exhausted, "
                    + "or the record is added to a batch which reached an earlier delivery expiration deadline. "
                    + "The value of this config should be greater than or equal to the sum of <code>" + ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG + "</code> "
                    + "and <code>" + ProducerConfig.LINGER_MS_CONFIG + "</code>.";

        }
    }
}
