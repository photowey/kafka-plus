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
package io.github.photowey.kafka.plus.core.checker;

import io.github.photowey.kafka.plus.core.exception.KafkaPlusRuntimeException;

import java.util.Objects;

/**
 * {@code ConfigChecker}
 *
 * @author photowey
 * @version 3.7.0.1.4
 * @since 2024/04/14
 */
public final class ConfigChecker {

    private ConfigChecker() {}

    /**
     * check not blank
     *
     * @param key    the key
     * @param object the object
     */
    public static void checkNotBlank(String key, String object) {
        if (Objects.isNull(object) || object.isEmpty()) {
            throw new KafkaPlusRuntimeException("%s can't be null/empty", key);
        }
    }

    public static <T> void checkNotNull(String key, T object) {
        if (Objects.isNull(object)) {
            throw new KafkaPlusRuntimeException("%s can't be null/empty", key);
        }
    }
}