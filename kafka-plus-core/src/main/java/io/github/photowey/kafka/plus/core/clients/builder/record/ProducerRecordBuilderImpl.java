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

import io.github.photowey.kafka.plus.core.clients.builder.AbstractBuilder;
import io.github.photowey.kafka.plus.core.exception.KafkaPlusRuntimeException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;

/**
 * {@code ProducerRecordBuilderImpl}
 *
 * @author photowey
 * @date 2024/04/06
 * @since 1.0.0
 */
public class ProducerRecordBuilderImpl extends AbstractBuilder implements ProducerRecordBuilder {

    private String topic;
    private Integer partition;
    private Iterable<Header> headers;
    private Object key;
    private Object value;
    private Long timestamp;

    @Override
    public ProducerRecordBuilder topic(String topic) {
        this.topic = topic;

        return this;
    }

    @Override
    public ProducerRecordBuilder partition(Integer partition) {
        this.partition = partition;

        return this;
    }

    @Override
    public ProducerRecordBuilder headers(Iterable<Header> headers) {
        this.headers = headers;

        return this;
    }

    @Override
    public <K> ProducerRecordBuilder key(K key) {
        this.key = key;

        return this;
    }

    @Override
    public <V> ProducerRecordBuilder value(V value) {
        this.value = value;

        return this;
    }

    @Override
    public ProducerRecordBuilder timestamp(Long timestamp) {
        this.timestamp = timestamp;

        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> ProducerRecord<K, V> build() {
        this.check();

        return new ProducerRecord<>(this.topic, this.partition, this.timestamp, (K) this.key, (V) this.value, this.headers);
    }

    private void check() {
        this.checkTopic();
        this.checkKey();
        this.checkValue();
    }

    private void checkTopic() {
        if (null == this.topic || this.topic.isEmpty()) {
            throw new KafkaPlusRuntimeException("The topic name can't be null/empty.");
        }
    }

    private void checkKey() {
        this.checkKeyNullable();
        //this.checkKeyNutNull();
    }

    private void checkKeyNullable() {
        // The key nullable.
    }

    private void checkKeyNutNull() {
        if (null == this.key) {
            throw new KafkaPlusRuntimeException("The key can't be null.");
        }
    }

    private void checkValue() {
        if (null == this.value) {
            throw new KafkaPlusRuntimeException("The value can't be null.");
        }
    }
}