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

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@code ProducerServiceTest}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
class ProducerServiceTest extends LocalTest {

    @Test
    void testProducer() {
        StringSerializer keySerializer = new StringSerializer();
        StringSerializer valueSerializer = new StringSerializer();
        try (KafkaProducer<String, String> producer = this.kafkaEngine().producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(keySerializer)
                .valueSerializer(valueSerializer)
                .build()) {

            Assertions.assertNotNull(producer);
        }
    }

    //@Test
    void testProducer_serializer_instance() {
        StringSerializer keySerializer = new StringSerializer();
        StringSerializer valueSerializer = new StringSerializer();
        try (KafkaProducer<String, String> producer = this.kafkaEngine().producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(keySerializer)
                .valueSerializer(valueSerializer)
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        this.defaultTopic(), "key-" + i, "value-" + i
                );
                producer.send(record);
            }
        }

        sleep(1_000L);
    }

    //@Test
    void testProducer_serializer_class() {
        try (KafkaProducer<String, String> producer = this.kafkaEngine().producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class)
                .valueSerializer(StringSerializer.class)
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        this.defaultTopic(), "key-" + i, "value-" + i
                );
                producer.send(record);
            }
        }

        sleep(1_000L);
    }

    //@Test
    void testProducer_serializer_string() {
        try (KafkaProducer<String, String> producer = this.kafkaEngine().producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class.getName())
                .valueSerializer(StringSerializer.class.getName())
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        this.defaultTopic(), "key-" + i, "value-" + i
                );
                producer.send(record);
            }
        }

        sleep(1_000L);
    }
}