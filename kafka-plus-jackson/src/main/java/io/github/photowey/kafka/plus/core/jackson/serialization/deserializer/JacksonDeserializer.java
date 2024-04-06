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
package io.github.photowey.kafka.plus.core.jackson.serialization.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.photowey.kafka.plus.core.jackson.serialization.ApplyObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * {@code JacksonDeserializer}
 *
 * @author photowey
 * @date 2024/04/06
 * @since 1.0.0
 */
public class JacksonDeserializer<T> implements Deserializer<Object>, ApplyObjectMapper {

    private final ObjectMapper objectMapper;
    private String encoding = StandardCharsets.UTF_8.name();

    public JacksonDeserializer() {
        this.objectMapper = this.initObjectMapper();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.deserializer.encoding" : "value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("deserializer.encoding");
        }
        if (encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }

            return this.objectMapper.readValue(data, Object.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Object by [com.fasterxml.jackson.databind.ObjectMapper].");
        }
    }

    @Override
    public Object deserialize(String topic, Headers headers, ByteBuffer data) {
        if (data == null) {
            return null;
        }

        try {
            if (data.hasArray()) {
                String json = new String(data.array(), data.position() + data.arrayOffset(), data.remaining(), encoding);
                return this.objectMapper.readValue(json, Object.class);
            }

            String json = new String(Utils.toArray(data), encoding);
            return this.objectMapper.readValue(json, Object.class);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error when deserializing ByteBuffer to string due to unsupported encoding " + encoding);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing ByteBuffer to Object by [com.fasterxml.jackson.databind.ObjectMapper].");
        }
    }
}