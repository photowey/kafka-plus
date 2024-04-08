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

import io.github.photowey.kafka.plus.core.clients.builder.consumer.ConsumerBuilder;
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
 * @since 2024/04/05
 * @version 1.0.0
 */
public interface ProducerBuilder {

    /**
     * The {@code bootstrap.servers}.
     *
     * @param bootstrapServers the bootstrap.servers
     * @return {@link  ProducerBuilder}
     */
    ProducerBuilder boostrapServers(String bootstrapServers);

    // ----------------------------------------------------------------

    /**
     * The key serializer.
     *
     * @param keySerializer the key serializer.
     * @param <K>           the key type.
     * @return {@link ProducerBuilder}
     */
    <K> ProducerBuilder keySerializer(Serializer<K> keySerializer);

    /**
     * The value serializer.
     *
     * @param valueSerializer the value serializer.
     * @param <V>             the value type.
     * @return {@link ProducerBuilder}
     */
    <V> ProducerBuilder valueSerializer(Serializer<V> valueSerializer);

    /**
     * The key serializer.
     *
     * @param keySerializer the key serializer Class.
     * @param <K>           the key type.
     * @return {@link ProducerBuilder}
     */
    default <K> ProducerBuilder keySerializer(Class<K> keySerializer) {
        return this.keySerializer(keySerializer.getName());
    }

    /**
     * The value serializer.
     *
     * @param valueSerializer the value serializer Class.
     * @param <V>             the value type.
     * @return {@link ProducerBuilder}
     */
    default <V> ProducerBuilder valueSerializer(Class<V> valueSerializer) {
        return this.valueSerializer(valueSerializer.getName());
    }

    /**
     * The key serializer.
     *
     * @param keySerializer the key serializer.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder keySerializer(String keySerializer);

    /**
     * The value serializer.
     *
     * @param valueSerializer the value serializer.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder valueSerializer(String valueSerializer);

    // ----------------------------------------------------------------

    /**
     * The custom property configs.
     *
     * @param props the custom property configs.
     * @return {@link ConsumerBuilder}
     */
    ProducerBuilder props(Properties props);

    /**
     * The custom configs.
     *
     * @param configs the custom configs.
     * @return {@link ConsumerBuilder}
     */
    ProducerBuilder configs(Map<String, Object> configs);

    // ----------------------------------------------------------------

    /**
     * Enhance custom property configs.
     *
     * @param fx the callback.
     * @return {@link ConsumerBuilder}
     */
    ProducerBuilder enhanceProps(Consumer<Properties> fx);

    /**
     * Enhance custom configs.
     *
     * @param fx the callback.
     * @return {@link ConsumerBuilder}
     */
    ProducerBuilder enhanceConfigs(Consumer<Map<String, Object>> fx);

    // ----------------------------------------------------------------

    /**
     * The interceptor.
     *
     * @param interceptor the interceptor Class
     * @param <K>         the key type.
     * @param <V>         the value type.
     * @param <I>         the {@link ProducerInterceptor} type.
     * @return {@link ProducerBuilder}
     */
    default <K, V, I extends ProducerInterceptor<K, V>> ProducerBuilder interceptor(Class<I> interceptor) {
        return this.interceptor(interceptor.getName());
    }

    /**
     * The partitioner.
     *
     * @param partitioner the partitioner Class.
     * @param <P>         the partitioner type.
     * @return {@link ProducerBuilder}
     */
    default <P extends Partitioner> ProducerBuilder partitioner(Class<P> partitioner) {
        return this.partitioner(partitioner.getName());
    }

    /**
     * The interceptor.
     *
     * @param interceptor the interceptor Class name
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder interceptor(String interceptor);

    /**
     * The partitioner.
     *
     * @param partitioner the partitioner Class name.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder partitioner(String partitioner);

    // ----------------------------------------------------------------

    /**
     * The {@code acks}.
     *
     * @param acks {@code acks}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder acks(Kafka.Producer.Acks acks);

    /**
     * The {@code retries}.
     *
     * @param retries {@code retries}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder retries(long retries);

    // ----------------------------------------------------------------

    /**
     * The {@code batch.size}.
     *
     * @param batchSize the batch size.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder batchSize(long batchSize);

    /**
     * The {@code buffer.memory}.
     *
     * @param bufferMemorySize the buffer memory size.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder bufferMemorySize(long bufferMemorySize);

    // ----------------------------------------------------------------

    /**
     * The {@code linger.ms}.
     *
     * @param millis {@code linger.ms}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder lingerMs(long millis);

    /**
     * The {@code max.block.ms}.
     *
     * @param millis {@code max.block.ms}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder maxBlockMs(long millis);

    /**
     * The {@code request.timeout.ms}.
     *
     * @param millis {@code request.timeout.ms}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder requestTimeoutMs(long millis);

    /**
     * The {@code delivery.timeout.ms}.
     *
     * @param millis {@code delivery.timeout.ms}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder deliveryTimeoutMs(long millis);

    default ProducerBuilder requestTimeoutMs(long timeout, TimeUnit unit) {
        return this.requestTimeoutMs(unit.toMillis(timeout));
    }

    default ProducerBuilder deliveryTimeoutMs(long timeout, TimeUnit unit) {
        return this.deliveryTimeoutMs(unit.toMillis(timeout));
    }

    // ----------------------------------------------------------------

    /**
     * The {@code enable.idempotence}.
     *
     * @param enabled {@code enable.idempotence}.
     * @return {@link ProducerBuilder}
     */
    ProducerBuilder idempotence(boolean enabled);

    // ----------------------------------------------------------------

    /**
     * Check custom property configs.
     *
     * @param fx the callback.
     * @return {@link ConsumerBuilder}
     */
    ProducerBuilder checkProps(Consumer<Properties> fx);

    /**
     * Check custom configs.
     *
     * @param fx the callback.
     * @return {@link ConsumerBuilder}
     */
    ProducerBuilder checkConfigs(Consumer<Map<String, Object>> fx);

    // ----------------------------------------------------------------

    /**
     * Build {@link KafkaProducer} instance.
     *
     * @param <K> the key type.
     * @param <V> the value type.
     * @return {@link KafkaProducer}
     */
    <K, V> KafkaProducer<K, V> build();
}