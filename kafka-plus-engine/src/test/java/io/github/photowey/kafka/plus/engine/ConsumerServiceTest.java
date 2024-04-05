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
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.Collections;

/**
 * {@code ConsumerServiceTest}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
class ConsumerServiceTest extends LocalTest {

    //@Test
    void testConsumer() {
        try (KafkaConsumer<String, String> consumer = this.kafkaEngine().consumerService().createConsumer()
                .boostrapServers(this.defaultBoostrapServers())
                .keyDeserializer(StringDeserializer.class)
                .valueDeserializer(StringDeserializer.class)
                .autoOffsetReset(Kafka.Consumer.AutoOffsetReset.EARLIEST)
                .groupId(this.defaultGroup())
                .autoCommitEnabled(true)
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            consumer.subscribe(Collections.singletonList(this.defaultTopic()));

            Assertions.assertNotNull(consumer);
        }
    }

    //@Test
    void testConsumer_deserializer_class() {
        try (KafkaConsumer<String, String> consumer = this.kafkaEngine().consumerService().createConsumer()
                .boostrapServers(this.defaultBoostrapServers())
                .keyDeserializer(StringDeserializer.class)
                .valueDeserializer(StringDeserializer.class)
                .autoOffsetReset(Kafka.Consumer.AutoOffsetReset.EARLIEST)
                .groupId(this.defaultGroup())
                .autoCommitEnabled(true)
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            consumer.subscribe(Collections.singletonList(this.defaultTopic()));

            for (int i = 0; i < 15; i++) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Key = " + record.key() + ", Value = " + record.value());
                }

                sleep(1_000L);
            }
        }
    }

    //@Test
    void testConsumer_deserializer_string() {
        try (KafkaConsumer<String, String> consumer = this.kafkaEngine().consumerService().createConsumer()
                .boostrapServers(this.defaultBoostrapServers())
                .keyDeserializer(StringDeserializer.class.getName())
                .valueDeserializer(StringDeserializer.class.getName())
                .autoOffsetReset(Kafka.Consumer.AutoOffsetReset.EARLIEST)
                .groupId(this.defaultGroup())
                .autoCommitEnabled(true)
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            consumer.subscribe(Collections.singletonList(this.defaultTopic()));

            for (int i = 0; i < 15; i++) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Key = " + record.key() + ", Value = " + record.value());
                }

                sleep(1_000L);
            }
        }
    }
}