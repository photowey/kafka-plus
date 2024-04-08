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
package io.github.photowey.kafka.plus.core.clients.builder.record;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;

/**
 * {@code ProducerRecordBuilder}
 *
 * @author photowey
 * @since 2024/04/06
 * @version 1.0.0
 */
public interface ProducerRecordBuilder {

    /**
     * The topic name.
     *
     * @param topic the topic name.
     * @return {@link ProducerRecordBuilder}
     */
    ProducerRecordBuilder topic(String topic);

    /**
     * The partition number.
     *
     * @param partition the partition number.
     * @return {@link ProducerRecordBuilder}
     */
    ProducerRecordBuilder partition(Integer partition);

    /**
     * The headers.
     *
     * @param headers the headers.
     * @return {@link ProducerRecordBuilder}
     */
    ProducerRecordBuilder headers(Iterable<Header> headers);

    /**
     * The key.
     *
     * @param key the record key.
     * @param <K> the key type.
     * @return {@link ProducerRecordBuilder}
     */
    <K> ProducerRecordBuilder key(K key);

    /**
     * The value.
     *
     * @param value the record value.
     * @param <V>   the value type.
     * @return {@link ProducerRecordBuilder}
     */
    <V> ProducerRecordBuilder value(V value);

    /**
     * The timestamp.
     *
     * @param timestamp the timestamp.
     * @return {@link ProducerRecordBuilder}
     */
    ProducerRecordBuilder timestamp(Long timestamp);

    /**
     * Build {@link ProducerRecord} instance.
     *
     * @param <K> the key type.
     * @param <V> the value type.
     * @return {@link ProducerRecord}
     */
    <K, V> ProducerRecord<K, V> build();
}
