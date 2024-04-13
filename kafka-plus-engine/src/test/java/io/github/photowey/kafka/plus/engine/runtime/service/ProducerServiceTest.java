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
package io.github.photowey.kafka.plus.engine.runtime.service;

import io.github.photowey.kafka.plus.core.jackson.serialization.serializer.JacksonSerializer;
import io.github.photowey.kafka.plus.engine.KafkaEngine;
import io.github.photowey.kafka.plus.engine.LocalTest;
import io.github.photowey.kafka.plus.engine.model.Person;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@code ProducerServiceTest}
 *
 * @author photowey
 * @since 2024/04/05
 * @version 3.7.0.1.0
 */
class ProducerServiceTest extends LocalTest {

    @Test
    void testProducer() {
        StringSerializer keySerializer = new StringSerializer();
        StringSerializer valueSerializer = new StringSerializer();

        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, String> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(keySerializer)
                .valueSerializer(valueSerializer)
                .build()) {

            Assertions.assertNotNull(producer);
        }
    }

    @Test
    void testProducerRecord() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        ProducerRecord<String, String> record = kafkaEngine.producerService().createProducerRecord()
                .topic(this.defaultTopic())
                .key("key-9527")
                .value("value-9527")
                .build();

        Assertions.assertNotNull(record);
    }


    @Test
    void testProducer_github_ci() {
        this.tryCreateTopicIfNecessary();

        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, String> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class)
                .valueSerializer(StringSerializer.class)
                .build()) {

            ProducerRecord<String, String> record = kafkaEngine.producerService().createProducerRecord()
                    .topic(this.defaultTopic())
                    .key("key-9527")
                    .value("value-9527")
                    .build();

            producer.send(record);
        }

        sleep(1_000L);
    }

    //@Test
    void testProducer_serializer_instance() {
        StringSerializer keySerializer = new StringSerializer();
        StringSerializer valueSerializer = new StringSerializer();

        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, String> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(keySerializer)
                .valueSerializer(valueSerializer)
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = kafkaEngine.producerService().createProducerRecord()
                        .topic(this.defaultTopic())
                        .key("key-" + i)
                        .value("value-" + i)
                        .build();

                producer.send(record);
            }
        }

        sleep(1_000L);
    }

    //@Test
    void testProducer_serializer_class() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, String> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class)
                .valueSerializer(StringSerializer.class)
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = kafkaEngine.producerService().createProducerRecord()
                        .topic(this.defaultTopic())
                        .key("key-" + i)
                        .value("value-" + i)
                        .build();

                producer.send(record);
            }
        }

        sleep(1_000L);
    }

    //@Test
    void testProducer_serializer_string() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, String> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class.getName())
                .valueSerializer(StringSerializer.class.getName())
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, String> record = kafkaEngine.producerService().createProducerRecord()
                        .topic(this.defaultTopic())
                        .key("key-" + i)
                        .value("value-" + i)
                        .build();

                producer.send(record);
            }
        }

        sleep(1_000L);
    }

    //@Test
    void testProducer_serializer_custom_jackson() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, Person> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class.getName())
                .valueSerializer(JacksonSerializer.class.getName())
                .build()) {

            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, Person> record = kafkaEngine.producerService().createProducerRecord()
                        .topic(this.defaultTopic())
                        .key("key-" + i)
                        .value(new Person(Person.uuid(), "value-" + i))
                        .build();

                producer.send(record);
            }
        }

        sleep(1_000L);
    }
}