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
package io.github.photowey.kafka.plus.core.clients.builder.admin;

import org.apache.kafka.clients.admin.Admin;

import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * {@code AdminBuilder}
 *
 * @author photowey
 * @version 3.7.0.1.0
 * @since 2024/04/05
 */
public interface AdminBuilder {

    /**
     * The {@code bootstrap.servers}.
     *
     * @param bootstrapServers the {@code bootstrap.servers}.
     * @return {@link  AdminBuilder}
     */
    AdminBuilder boostrapServers(String bootstrapServers);

    /**
     * The custom property configs.
     *
     * @param props the custom property configs.
     * @return {@link AdminBuilder}
     */
    AdminBuilder props(Properties props);

    /**
     * The custom configs.
     *
     * @param configs the custom configs.
     * @return {@link AdminBuilder}
     */
    AdminBuilder configs(Map<String, Object> configs);

    /**
     * Check custom property configs.
     *
     * @param fx the callback.
     * @return {@link AdminBuilder}
     */
    AdminBuilder checkProps(Consumer<Properties> fx);

    /**
     * Check custom configs.
     *
     * @param fx the callback.
     * @return {@link AdminBuilder}
     */
    AdminBuilder checkConfigs(Consumer<Map<String, Object>> fx);

    /**
     * Build {@link Admin} instance.
     *
     * @return {@link Admin}
     */
    Admin build();
}
