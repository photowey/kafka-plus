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
package io.github.photowey.kafka.plus.autoconfigure;

import io.github.photowey.kafka.plus.autoconfigure.engine.SpringKafkaEngineImpl;
import io.github.photowey.kafka.plus.autoconfigure.engine.processor.KafkaEngineSpringAwareBeanPostProcessor;
import io.github.photowey.kafka.plus.engine.KafkaEngine;
import io.github.photowey.kafka.plus.runtime.service.AdminService;
import io.github.photowey.kafka.plus.runtime.service.ConsumerService;
import io.github.photowey.kafka.plus.runtime.service.ProducerService;
import io.github.photowey.kafka.plus.runtime.service.impl.AdminServiceImpl;
import io.github.photowey.kafka.plus.runtime.service.impl.ConsumerServiceImpl;
import io.github.photowey.kafka.plus.runtime.service.impl.ProducerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code KafkaPlusConfigure}
 *
 * @author photowey
 * @date 2024/04/06
 * @since 1.0.0
 */
@Import(value = {
        KafkaEngineSpringAwareBeanPostProcessor.class,
        KafkaPlusConfigure.EngineConfigure.class,
        KafkaPlusConfigure.ServiceConfigure.class,
})
@Configuration
public class KafkaPlusConfigure {

    @Configuration
    static class EngineConfigure {

        @Bean
        public KafkaEngine kafkaEngine() {
            return new SpringKafkaEngineImpl();
        }
    }

    @Configuration
    static class ServiceConfigure {

        @Bean
        public AdminService adminService() {
            return new AdminServiceImpl();
        }

        @Bean
        public ConsumerService consumerService() {
            return new ConsumerServiceImpl();
        }

        @Bean
        public ProducerService producerService() {
            return new ProducerServiceImpl();
        }
    }
}