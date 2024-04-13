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

import io.github.photowey.kafka.plus.core.clients.builder.AbstractBuilder;
import io.github.photowey.kafka.plus.core.enums.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

import static io.github.photowey.kafka.plus.core.checker.ConfigChecker.checkNotBlank;
import static io.github.photowey.kafka.plus.core.checker.ConfigChecker.checkNotNull;

/**
 * {@code ProducerBuilderImpl}
 *
 * @author photowey
 * @version 3.7.0.1.0
 * @since 2024/04/05
 */
public class ProducerBuilderImpl extends AbstractBuilder implements ProducerBuilder {

    private Serializer<?> keySerializer;
    private Serializer<?> valueSerializer;

    @Override
    public ProducerBuilder boostrapServers(String bootstrapServers) {
        checkNotBlank("bootstrap.servers", bootstrapServers);
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Bootstrap.Server.ADDRESS.value(), bootstrapServers);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public <K> ProducerBuilder keySerializer(Serializer<K> keySerializer) {
        checkNotNull("keySerializer", keySerializer);
        this.keySerializer = keySerializer;

        return this;
    }

    @Override
    public <V> ProducerBuilder valueSerializer(Serializer<V> valueSerializer) {
        checkNotNull("valueSerializer", valueSerializer);
        this.valueSerializer = valueSerializer;

        return this;
    }

    @Override
    public ProducerBuilder keySerializer(String keySerializer) {
        checkNotBlank("keySerializer", keySerializer);
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.KEY_SERIALIZER.key(), keySerializer);

        return this;
    }

    @Override
    public ProducerBuilder valueSerializer(String valueSerializer) {
        checkNotBlank("valueSerializer", valueSerializer);
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.VALUE_DESERIALIZER.key(), valueSerializer);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder props(Properties props) {
        checkNotNull("props", props);
        super.props = props;

        return this;
    }

    @Override
    public ProducerBuilder configs(Map<String, Object> configs) {
        checkNotNull("configs", configs);
        super.configs = configs;

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder enhanceProps(Consumer<Properties> fx) {
        checkNotNull("enhanceProps.fx", fx);
        if (null != super.props) {
            fx.accept(super.props);
        }

        return this;
    }

    @Override
    public ProducerBuilder enhanceConfigs(Consumer<Map<String, Object>> fx) {
        checkNotNull("enhanceConfigs.fx", fx);
        if (null != super.configs) {
            fx.accept(super.configs);
        }

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder interceptor(String interceptor) {
        checkNotBlank("interceptor", interceptor);
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.INTERCEPTOR.key(), interceptor);

        return this;
    }

    @Override
    public ProducerBuilder partitioner(String partitioner) {
        checkNotBlank("partitioner", partitioner);
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.PARTITIONER.key(), partitioner);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder acks(Kafka.Producer.Acks acks) {
        checkNotNull("acks", acks);
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.ACKS.key(), acks.value());

        return this;
    }

    @Override
    public ProducerBuilder retries(long retries) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.RETRIES.key(), retries);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder batchSize(long batchSize) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.BATCH_SIZE.key(), batchSize);

        return this;
    }

    @Override
    public ProducerBuilder bufferMemorySize(long bufferMemorySize) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.BUFFER_MEMORY_SIZE.key(), bufferMemorySize);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder lingerMs(long millis) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.LINGER_MS.key(), millis);

        return this;
    }

    @Override
    public ProducerBuilder maxBlockMs(long millis) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.MAX_BLOCK_MS.key(), millis);

        return this;
    }

    @Override
    public ProducerBuilder requestTimeoutMs(long millis) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.REQUEST_TIMEOUT_MS.key(), millis);

        return this;
    }

    @Override
    public ProducerBuilder deliveryTimeoutMs(long millis) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.DELIVERY_TIMEOUT_MS.key(), millis);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder idempotence(boolean enabled) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Producer.IDEMPOTENCE_ENABLED.key(), String.valueOf(enabled));

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ProducerBuilder checkProps(Consumer<Properties> fx) {
        checkNotNull("checkProps.fx", fx);
        fx.accept(super.props);

        return this;
    }

    @Override
    public ProducerBuilder checkConfigs(Consumer<Map<String, Object>> fx) {
        checkNotNull("checkConfigs.fx", fx);
        fx.accept(super.configs);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> KafkaProducer<K, V> build() {
        if (null != super.props) {
            this.checkPropsIfNecessary();

            return new KafkaProducer<>(super.props, (Serializer<K>) this.keySerializer, (Serializer<V>) this.valueSerializer);
        }

        this.checkConfigsIfNecessary();
        return new KafkaProducer<>(super.configs, (Serializer<K>) this.keySerializer, (Serializer<V>) this.valueSerializer);
    }
}