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

import io.github.photowey.kafka.plus.core.clients.builder.AbstractBuilder;
import io.github.photowey.kafka.plus.core.enums.Kafka;
import org.apache.kafka.clients.admin.Admin;

import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * {@code AdminBuilderImpl}
 * <p>
 * Examples:
 * <pre>
 * Properties props = new Properties();
 * Admin admin = new AdminBuilderImpl()
 *    .props(props)
 *    .checkProps((x) -> {})
 *    .build();
 * </pre>
 *
 * <pre>
 * Map<String, Object> configMap = new HashMap<>();
 * Admin admin = new AdminBuilderImpl()
 *    .configMap(configMap)
 *    .checkMap((x) -> {})
 *    .build();
 * </pre>
 *
 * <pre>
 * String bootstrapServers = "localhost:9092";
 * Admin admin = new AdminBuilderImpl()
 *    .boostrapServers(bootstrapServers)
 *    .checkMap((x) -> {})
 *    .build();
 * </pre>
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public class AdminBuilderImpl extends AbstractBuilder implements AdminBuilder {

    @Override
    public AdminBuilder boostrapServers(String bootstrapServers) {
        super.initConfigsIfNecessary();
        super.configs.put(Kafka.Bootstrap.Server.ADDRESS.value(), bootstrapServers);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public AdminBuilder props(Properties props) {
        super.props = props;

        return this;
    }

    @Override
    public AdminBuilder configs(Map<String, Object> configs) {
        super.configs = configs;

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public AdminBuilder checkProps(Consumer<Properties> fx) {
        fx.accept(super.props);

        return this;
    }

    @Override
    public AdminBuilder checkConfigs(Consumer<Map<String, Object>> fx) {
        fx.accept(super.configs);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public Admin build() {
        if (null != super.props) {
            this.checkPropsIfNecessary();

            return Admin.create(super.props);
        }

        this.checkConfigsIfNecessary();
        return Admin.create(super.configs);
    }
}