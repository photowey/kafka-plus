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
package io.github.photowey.kafka.plus.core.exception;

/**
 * {@code KafkaPlusRuntimeException}
 *
 * @author photowey
 * @since 2024/04/05
 * @version 1.0.0
 */
public class KafkaPlusRuntimeException extends RuntimeException {

    public KafkaPlusRuntimeException() {
        super();
    }

    public KafkaPlusRuntimeException(String message, Object... args) {
        super(String.format(message, args));
    }

    public KafkaPlusRuntimeException(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
    }

    public KafkaPlusRuntimeException(Throwable cause) {
        super(cause);
    }

    protected KafkaPlusRuntimeException(Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message, Object... args) {
        super(String.format(message, args), cause, enableSuppression, writableStackTrace);
    }
}