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
package io.github.photowey.kafka.plus.core.clients.builder.producer;

import io.github.photowey.kafka.plus.core.enums.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * {@code ProducerBuilder}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public interface ProducerBuilder {

    ProducerBuilder boostrapServers(String bootstrapServers);

    // ----------------------------------------------------------------

    <K> ProducerBuilder keySerializer(Serializer<K> keySerializer);


    <V> ProducerBuilder valueSerializer(Serializer<V> valueSerializer);

    default <K> ProducerBuilder keySerializer(Class<K> keySerializer) {
        return this.keySerializer(keySerializer.getName());
    }

    default <V> ProducerBuilder valueSerializer(Class<V> valueSerializer) {
        return this.valueSerializer(valueSerializer.getName());
    }

    ProducerBuilder keySerializer(String keySerializer);

    ProducerBuilder valueSerializer(String valueSerializer);

    // ----------------------------------------------------------------
    ProducerBuilder props(Properties props);

    ProducerBuilder configs(Map<String, Object> configs);

    // ----------------------------------------------------------------

    ProducerBuilder enhanceProps(Consumer<Properties> fx);

    ProducerBuilder enhanceConfigs(Consumer<Map<String, Object>> fx);

    // ----------------------------------------------------------------

    default <K, V, I extends ProducerInterceptor<K, V>> ProducerBuilder interceptor(Class<I> interceptor) {
        return this.interceptor(interceptor.getName());
    }

    default <P extends Partitioner> ProducerBuilder partitioner(Class<P> partitioner) {
        return this.partitioner(partitioner.getName());
    }

    ProducerBuilder interceptor(String interceptor);

    ProducerBuilder partitioner(String partitioner);

    // ----------------------------------------------------------------

    ProducerBuilder acks(Kafka.Producer.Acks acks);

    ProducerBuilder retries(long retries);

    // ----------------------------------------------------------------

    ProducerBuilder batchSize(long batchSize);

    ProducerBuilder bufferMemorySize(long bufferMemorySize);

    // ----------------------------------------------------------------

    ProducerBuilder maxBlockMs(long millis);

    ProducerBuilder requestTimeoutMs(long millis);

    ProducerBuilder deliveryTimeoutMs(long millis);

    default ProducerBuilder requestTimeoutMs(long timeout, TimeUnit unit) {
        return this.requestTimeoutMs(unit.toMillis(timeout));
    }

    default ProducerBuilder deliveryTimeoutMs(long timeout, TimeUnit unit) {
        return this.deliveryTimeoutMs(unit.toMillis(timeout));
    }

    // ----------------------------------------------------------------

    ProducerBuilder checkProps(Consumer<Properties> fx);

    ProducerBuilder checkConfigs(Consumer<Map<String, Object>> fx);

    // ----------------------------------------------------------------

    <K, V> KafkaProducer<K, V> build();
}