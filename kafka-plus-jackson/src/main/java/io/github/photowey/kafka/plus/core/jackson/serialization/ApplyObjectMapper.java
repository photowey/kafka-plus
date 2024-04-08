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
package io.github.photowey.kafka.plus.core.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * {@code ApplyObjectMapper}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/06
 */
public interface ApplyObjectMapper {

    /**
     * Init {@link ObjectMapper} instance.
     *
     * @return {@link ObjectMapper}
     */
    default ObjectMapper initObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        this.applyObjectMapper(objectMapper);

        return objectMapper;
    }

    /**
     * Apply {@link ObjectMapper}
     *
     * @param objectMapper {@link ObjectMapper}
     */
    default void applyObjectMapper(ObjectMapper objectMapper) {
        objectMapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(new JavaTimeModule());
    }
}
