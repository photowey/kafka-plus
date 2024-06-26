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
package io.github.photowey.kafka.plus.autoconfigure.core.property;

import io.github.photowey.kafka.plus.core.enums.Kafka;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@code KafkaPlusProperties}
 *
 * @author photowey
 * @version 3.7.0.1.0
 * @since 2024/04/06
 */
public class KafkaPlusProperties implements Serializable {

    public static final String SPRING_KAFKA_PLUS_PROPERTY_PREFIX = "spring.kafkaplus";

    private static final long serialVersionUID = 3337360099086676508L;

    // ----------------------------------------------------------------

    public static String getPrefix() {
        return SPRING_KAFKA_PLUS_PROPERTY_PREFIX;
    }

    // ----------------------------------------------------------------

    /**
     * The mode of the Kafka cluster.
     */
    private Mode mode = new Mode();
    /**
     * The bootstrap properties.
     */
    private Bootstrap bootstrap = new Bootstrap();
    /**
     * The admin properties.
     */
    private Admin admin = new Admin();
    /**
     * The consumer properties.
     */
    private Consumer consumer = new Consumer();
    /**
     * The producer properties.
     */
    private Producer producer = new Producer();

    // ----------------------------------------------------------------

    public static class Mode implements Serializable {

        private static final long serialVersionUID = -4204876677696854460L;

        private Kafka.Mode mode = Kafka.Mode.STANDALONE;

        // ----------------------------------------------------------------

        public Kafka.Mode getMode() {
            return mode;
        }

        // ----------------------------------------------------------------

        public Kafka.Mode mode() {
            return mode;
        }

        // ----------------------------------------------------------------

        public void setMode(Kafka.Mode mode) {
            this.mode = mode;
        }
    }

    public static class Bootstrap implements Serializable {

        private static final long serialVersionUID = 1400298527365044251L;

        /**
         * The {@code bootstrap.servers}.
         */
        private String servers = "localhost:9092";

        // ----------------------------------------------------------------

        public String getServers() {
            return servers;
        }

        // ----------------------------------------------------------------

        public String servers() {
            return servers;
        }

        // ----------------------------------------------------------------

        public void setServers(String servers) {
            this.servers = servers;
        }
    }

    public static class Admin implements Serializable {

        private static final long serialVersionUID = -1008246731350725859L;


        private List<Topic> topics = new ArrayList<>();

        public static class Topic implements Serializable {

            private static final long serialVersionUID = -4411325091525328608L;

            /**
             * The topic name.
             */
            private String topic;
            /**
             * The number of partitions.
             */
            private int numPartitions = 1;
            /**
             * The replication factor.
             */
            private int replicationFactor = 1;
            /**
             * The replicas assignments.
             */
            private Map<Integer, List<Integer>> replicasAssignments;

            // ----------------------------------------------------------------

            public String getTopic() {
                return topic;
            }

            public int getNumPartitions() {
                return numPartitions;
            }

            public int getReplicationFactor() {
                return replicationFactor;
            }

            public Map<Integer, List<Integer>> getReplicasAssignments() {
                return replicasAssignments;
            }

            // ----------------------------------------------------------------

            public String topic() {
                return topic;
            }

            public int numPartitions() {
                return numPartitions;
            }

            public int replicationFactor() {
                return replicationFactor;
            }

            public Map<Integer, List<Integer>> replicasAssignments() {
                return replicasAssignments;
            }

            // ----------------------------------------------------------------

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public void setNumPartitions(int numPartitions) {
                this.numPartitions = numPartitions;
            }

            public void setReplicationFactor(int replicationFactor) {
                this.replicationFactor = replicationFactor;
            }

            public void setReplicasAssignments(Map<Integer, List<Integer>> replicasAssignments) {
                this.replicasAssignments = replicasAssignments;
            }
        }

        // ----------------------------------------------------------------

        public List<Topic> getTopics() {
            return topics;
        }

        // ----------------------------------------------------------------

        public List<Topic> topics() {
            return topics;
        }

        // ----------------------------------------------------------------

        public void setTopics(List<Topic> topics) {
            this.topics = topics;
        }
    }

    public static class Consumer implements Serializable {

        private static final long serialVersionUID = 6473628614295963537L;

        /**
         * The key deserializer.
         */
        private String keyDeserializer = StringSerializer.class.getName();
        /**
         * The value deserializer.
         */
        private String valueDeserializer = StringSerializer.class.getName();
        /**
         * The {@code auto.offset.reset}.
         */
        private Kafka.Consumer.AutoOffsetReset autoOffsetReset;
        /**
         * The {@code group.id}.
         */
        private String groupId;
        /**
         * The {@code enable.auto.commit}.
         */
        private Boolean autoCommit;
        /**
         * Subscribes
         * |- A,B,C,...,Z
         */
        private String subscribes;

        // ----------------------------------------------------------------

        public String getKeyDeserializer() {
            return keyDeserializer;
        }

        public String getValueDeserializer() {
            return valueDeserializer;
        }

        public Kafka.Consumer.AutoOffsetReset getAutoOffsetReset() {
            return autoOffsetReset;
        }

        public String getGroupId() {
            return groupId;
        }

        public Boolean getAutoCommit() {
            return autoCommit;
        }

        public String getSubscribes() {
            return subscribes;
        }

        // ----------------------------------------------------------------

        public String keyDeserializer() {
            return keyDeserializer;
        }

        public String valueDeserializer() {
            return valueDeserializer;
        }

        public Kafka.Consumer.AutoOffsetReset autoOffsetReset() {
            return autoOffsetReset;
        }

        public String groupId() {
            return groupId;
        }

        public Boolean autoCommit() {
            return autoCommit;
        }

        public String subscribes() {
            return subscribes;
        }

        // ----------------------------------------------------------------

        public void setKeyDeserializer(String keyDeserializer) {
            this.keyDeserializer = keyDeserializer;
        }

        public void setValueDeserializer(String valueDeserializer) {
            this.valueDeserializer = valueDeserializer;
        }

        public void setAutoOffsetReset(Kafka.Consumer.AutoOffsetReset autoOffsetReset) {
            this.autoOffsetReset = autoOffsetReset;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public void setAutoCommit(Boolean autoCommit) {
            this.autoCommit = autoCommit;
        }

        public void setSubscribes(String subscribes) {
            this.subscribes = subscribes;
        }
    }

    public static class Producer implements Serializable {

        private static final long serialVersionUID = 8700675817188492332L;

        /**
         * The key serializer.
         */
        private String keySerializer = StringSerializer.class.getName();
        /**
         * The value serializer.
         */
        private String valueSerializer = StringSerializer.class.getName();
        /**
         * The interceptor.
         */
        private String interceptor;
        /**
         * The partitioner.
         */
        private String partitioner;
        /**
         * The {@code acks}.
         */
        private Kafka.Producer.Acks acks;
        /**
         * The {@code retries}.
         */
        private Long retries;
        /**
         * The {@code batch.size}.
         */
        private Long batchSize;
        /**
         * The {@code buffer.memory}.
         */
        private Long bufferMemorySize;
        /**
         * The {@code linger.ms}.
         */
        private Long lingerMs;
        /**
         * The {@code max.block.ms}.
         */
        private Long maxBlockMs;
        /**
         * The {@code request.timeout.ms}.
         */
        private Long requestTimeoutMs;
        /**
         * The {@code delivery.timeout.ms}.
         */
        private Long deliveryTimeoutMs;
        /**
         * The {@code enable.idempotence}.
         */
        private Boolean idempotence;

        // ----------------------------------------------------------------

        public String getKeySerializer() {
            return keySerializer;
        }

        public String getValueSerializer() {
            return valueSerializer;
        }

        public String getInterceptor() {
            return interceptor;
        }

        public String getPartitioner() {
            return partitioner;
        }

        public Kafka.Producer.Acks getAcks() {
            return acks;
        }

        public Long getRetries() {
            return retries;
        }

        public Long getBatchSize() {
            return batchSize;
        }

        public Long getBufferMemorySize() {
            return bufferMemorySize;
        }

        public Long getLingerMs() {
            return lingerMs;
        }

        public Long getMaxBlockMs() {
            return maxBlockMs;
        }

        public Long getRequestTimeoutMs() {
            return requestTimeoutMs;
        }

        public Long getDeliveryTimeoutMs() {
            return deliveryTimeoutMs;
        }

        public Boolean getIdempotence() {
            return idempotence;
        }

        // ----------------------------------------------------------------

        public String keySerializer() {
            return keySerializer;
        }

        public String valueSerializer() {
            return valueSerializer;
        }

        public String interceptor() {
            return interceptor;
        }

        public String partitioner() {
            return partitioner;
        }

        public Kafka.Producer.Acks acks() {
            return acks;
        }

        public Long retries() {
            return retries;
        }

        public Long batchSize() {
            return batchSize;
        }

        public Long bufferMemorySize() {
            return bufferMemorySize;
        }

        public Long lingerMs() {
            return lingerMs;
        }

        public Long maxBlockMs() {
            return maxBlockMs;
        }

        public Long requestTimeoutMs() {
            return requestTimeoutMs;
        }

        public Long deliveryTimeoutMs() {
            return deliveryTimeoutMs;
        }

        public Boolean idempotence() {
            return idempotence;
        }

        // ----------------------------------------------------------------

        public void setKeySerializer(String keySerializer) {
            this.keySerializer = keySerializer;
        }

        public void setValueSerializer(String valueSerializer) {
            this.valueSerializer = valueSerializer;
        }

        public void setInterceptor(String interceptor) {
            this.interceptor = interceptor;
        }

        public void setPartitioner(String partitioner) {
            this.partitioner = partitioner;
        }

        public void setAcks(Kafka.Producer.Acks acks) {
            this.acks = acks;
        }

        public void setRetries(Long retries) {
            this.retries = retries;
        }

        public void setBatchSize(Long batchSize) {
            this.batchSize = batchSize;
        }

        public void setBufferMemorySize(Long bufferMemorySize) {
            this.bufferMemorySize = bufferMemorySize;
        }

        public void setLingerMs(Long lingerMs) {
            this.lingerMs = lingerMs;
        }

        public void setMaxBlockMs(Long maxBlockMs) {
            this.maxBlockMs = maxBlockMs;
        }

        public void setRequestTimeoutMs(Long requestTimeoutMs) {
            this.requestTimeoutMs = requestTimeoutMs;
        }

        public void setDeliveryTimeoutMs(Long deliveryTimeoutMs) {
            this.deliveryTimeoutMs = deliveryTimeoutMs;
        }

        public void setIdempotence(Boolean idempotence) {
            this.idempotence = idempotence;
        }
    }

    // ----------------------------------------------------------------

    public Mode getMode() {
        return mode;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Producer getProducer() {
        return producer;
    }

    // ----------------------------------------------------------------

    public Mode mode() {
        return mode;
    }

    public Bootstrap getbootstrapBootstrap() {
        return bootstrap;
    }

    public Admin admin() {
        return admin;
    }

    public Consumer consumer() {
        return consumer;
    }

    public Producer producer() {
        return producer;
    }

    // ----------------------------------------------------------------

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}