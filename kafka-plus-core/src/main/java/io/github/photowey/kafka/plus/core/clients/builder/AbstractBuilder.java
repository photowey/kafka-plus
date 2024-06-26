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
package io.github.photowey.kafka.plus.core.clients.builder;

import io.github.photowey.kafka.plus.core.exception.KafkaPlusRuntimeException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * {@code AbstractService}
 *
 * @author photowey
 * @since 2024/04/05
 * @version 3.7.0.1.0
 */
public abstract class AbstractBuilder {

    /**
     * The custom configs.
     */
    protected Map<String, Object> configs;

    /**
     * The custom {@link Properties} configs.
     */
    protected Properties props;

    protected void initConfigsIfNecessary() {
        if (null == this.configs) {
            this.configs = new HashMap<>(1 << 3);
        }
    }

    protected void checkPropsIfNecessary() {
        if (null == this.props || this.props.isEmpty()) {
            throw new KafkaPlusRuntimeException("The props can't be null/empty");
        }
    }

    protected void checkConfigsIfNecessary() {
        if (null == this.configs || this.configs.isEmpty()) {
            throw new KafkaPlusRuntimeException("The configs can't be null/empty");
        }
    }
}