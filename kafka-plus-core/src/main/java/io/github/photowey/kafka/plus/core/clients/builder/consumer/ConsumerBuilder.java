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

import io.github.photowey.kafka.plus.core.enums.Kafka;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.IsolationLevel;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.*;
import java.util.function.Consumer;

/**
 * {@code ConsumerBuilder}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/05
 */
public interface ConsumerBuilder {

    /**
     * The {@code bootstrap.servers}.
     *
     * @param bootstrapServers the bootstrap.servers
     * @return {@link  ConsumerBuilder}
     */
    ConsumerBuilder boostrapServers(String bootstrapServers);

    /**
     * The key deserializer.
     *
     * @param keyDeserializer the key deserializer Class.
     * @param <K>             the key type.
     * @return {@link ConsumerBuilder}
     */
    default <K> ConsumerBuilder keyDeserializer(Class<K> keyDeserializer) {
        return this.keyDeserializer(keyDeserializer.getName());
    }

    /**
     * The value deserializer.
     *
     * @param valueDeserializer the value deserializer Class.
     * @param <V>               the value type.
     * @return {@link ConsumerBuilder}
     */
    default <V> ConsumerBuilder valueDeserializer(Class<V> valueDeserializer) {
        return this.valueDeserializer(valueDeserializer.getName());
    }

    /**
     * The key deserializer.
     *
     * @param keyDeserializer the key deserializer.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder keyDeserializer(String keyDeserializer);

    /**
     * The value deserializer.
     *
     * @param valueDeserializer the value deserializer.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder valueDeserializer(String valueDeserializer);

    /**
     * The {@code auto.offset.reset}.
     *
     * @param offsetReset the {@code auto.offset.reset}
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder autoOffsetReset(Kafka.Consumer.AutoOffsetReset offsetReset);

    /**
     * The {@code group.id}.
     *
     * @param groupId the {@code group.id}
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder groupId(String groupId);

    /**
     * The {@code enable.auto.commit}.
     *
     * @param enabled the {@code enable.auto.commit}
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder autoCommit(boolean enabled);

    // ----------------------------------------------------------------

    /**
     * The {@code isolation.level}.
     *
     * @param isolation the {@code isolation.level}
     * @return {@link ConsumerBuilder}
     * @see IsolationLevel
     * @since 3.7.0.1.4
     */
    ConsumerBuilder isolation(Kafka.Consumer.Isolation isolation);

    /**
     * The consumer group instance ID.
     * |- {@code group.instance.id}
     *
     * @param instanceId the {@code group.instance.id}.
     * @since 3.7.0.1.4
     */
    ConsumerBuilder instanceId(String instanceId);

    /**
     * The partition assignment strategy.
     * |- {@code partition.assignment.strategy}
     *
     * @param strategy the Class of strategy.
     * @since 3.7.0.1.4
     */
    default ConsumerBuilder strategy(Class<?> strategy) {
        return this.strategy(strategy.getName());
    }

    /**
     * The partition assignment strategy.
     * |- {@code partition.assignment.strategy}
     *
     * @param strategy {@code partition.assignment.strategy}
     * @since 3.7.0.1.4
     */
    ConsumerBuilder strategy(String strategy);

    // ----------------------------------------------------------------

    /**
     * The key deserializer.
     *
     * @param keyDeserializer the key deserializer.
     * @param <K>             the key type.
     * @return {@link ConsumerBuilder}
     */
    <K> ConsumerBuilder keyDeserializer(Deserializer<K> keyDeserializer);

    /**
     * The value deserializer.
     *
     * @param valueDeserializer the value deserializer.
     * @param <V>               the value type.
     * @return {@link ConsumerBuilder}
     */
    <V> ConsumerBuilder valueDeserializer(Deserializer<V> valueDeserializer);

    // ----------------------------------------------------------------

    /**
     * The custom property configs.
     *
     * @param props the custom property configs.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder props(Properties props);

    /**
     * The custom configs.
     *
     * @param configs the custom configs.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder configs(Map<String, Object> configs);

    // ----------------------------------------------------------------

    /**
     * Check custom property configs.
     *
     * @param fx the callback.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder checkProps(Consumer<Properties> fx);

    /**
     * Check custom configs.
     *
     * @param fx the callback.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder checkConfigs(Consumer<Map<String, Object>> fx);

    // ----------------------------------------------------------------

    /**
     * The topics of the consumer subscribe.
     *
     * @param topics the topic names.
     * @return {@link ConsumerBuilder}
     */
    default ConsumerBuilder subscribe(String... topics) {
        return this.subscribe(new HashSet<>(Arrays.asList(topics)));
    }

    /**
     * The topics of the consumer subscribe.
     *
     * @param topics the topic names.
     * @return {@link ConsumerBuilder}
     */
    ConsumerBuilder subscribe(Collection<String> topics);

    // ----------------------------------------------------------------

    /**
     * Build {@link KafkaConsumer} instance.
     *
     * @param <K> the key type.
     * @param <V> the value type.
     * @return {@link KafkaConsumer}
     */
    <K, V> KafkaConsumer<K, V> build();
}
