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
package io.github.photowey.kafka.plus.spring.boot.starter.config;

import io.github.photowey.kafka.plus.autoconfigure.KafkaPlusConfigure;
import io.github.photowey.kafka.plus.autoconfigure.core.property.KafkaPlusProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * {@code KafkaPlusAutoConfigure}
 *
 * @author photowey
 * @since 2024/04/06
 * @version 3.7.0.1.0
 */
@Import(value = {
        KafkaPlusConfigure.class,
        KafkaPlusAutoConfigure.PropertyConfigure.class,
})
@Configuration
public class KafkaPlusAutoConfigure {

    @Configuration
    static class PropertyConfigure {

        @Bean
        public KafkaPlusProperties kafkaPlusProperties(Environment environment) {
            return bind(environment, KafkaPlusProperties.getPrefix(), KafkaPlusProperties.class);
        }
    }

    static <T> T bind(Environment environment, String prefix, Class<T> clazz) {
        Binder binder = Binder.get(environment);

        return binder.bind(prefix, clazz).get();
    }
}