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
package io.github.photowey.kafka.plus.core.clients.builder.consumer;

import io.github.photowey.kafka.plus.core.clients.builder.AbstractBuilder;
import io.github.photowey.kafka.plus.core.enums.Kafka;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * {@code ConsumerBuilderImpl}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public class ConsumerBuilderImpl extends AbstractBuilder implements ConsumerBuilder {

    private Deserializer<?> keyDeserializer;
    private Deserializer<?> valueDeserializer;

    private Collection<String> topics;

    @Override
    public ConsumerBuilder boostrapServers(String bootstrapServers) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Bootstrap.Server.ADDRESS.value(), bootstrapServers);

        return this;
    }

    @Override
    public ConsumerBuilder keyDeserializer(String keyDeserializer) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Consumer.KEY_DESERIALIZER.key(), keyDeserializer);

        return this;
    }

    @Override
    public ConsumerBuilder valueDeserializer(String valueDeserializer) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Consumer.VALUE_DESERIALIZER.key(), valueDeserializer);

        return this;
    }

    @Override
    public ConsumerBuilder autoOffsetReset(Kafka.Consumer.AutoOffsetReset offsetReset) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Consumer.AUTO_OFFSET_RESET.key(), offsetReset.value());

        return this;
    }

    @Override
    public ConsumerBuilder groupId(String groupId) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Consumer.GROUP_ID.key(), groupId);

        return this;
    }

    @Override
    public ConsumerBuilder autoCommitEnabled(boolean enabled) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Consumer.AUTO_COMMIT_ENABLED.key(), String.valueOf(enabled));

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public <K> ConsumerBuilder keyDeserializer(Deserializer<K> keyDeserializer) {
        this.keyDeserializer = keyDeserializer;

        return this;
    }

    @Override
    public <V> ConsumerBuilder valueDeserializer(Deserializer<V> valueDeserializer) {
        this.valueDeserializer = valueDeserializer;

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ConsumerBuilder props(Properties props) {
        super.props = props;

        return this;
    }

    @Override
    public ConsumerBuilder configs(Map<String, Object> configs) {
        super.configs = configs;

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ConsumerBuilder checkProps(Consumer<Properties> fx) {
        fx.accept(super.props);

        return this;
    }

    @Override
    public ConsumerBuilder checkConfigs(Consumer<Map<String, Object>> fx) {
        fx.accept(super.configs);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public ConsumerBuilder subscribe(Collection<String> topics) {
        this.topics = topics;

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> KafkaConsumer<K, V> build() {
        if (null != super.props) {
            this.checkPropsIfNecessary();

            KafkaConsumer<K, V> consumer = new KafkaConsumer<>(
                    super.props, (Deserializer<K>) this.keyDeserializer, (Deserializer<V>) this.valueDeserializer);
            this.subscribe(consumer, this.topics);

            return consumer;
        }

        this.checkConfigsIfNecessary();
        KafkaConsumer<K, V> consumer = new KafkaConsumer<>(
                this.configs, (Deserializer<K>) this.keyDeserializer, (Deserializer<V>) this.valueDeserializer);
        this.subscribe(consumer, this.topics);

        return consumer;
    }

    private <K, V> void subscribe(KafkaConsumer<K, V> consumer, Collection<String> topics) {
        if (null != this.topics && !this.topics.isEmpty()) {
            consumer.subscribe(topics);
        }
    }
}