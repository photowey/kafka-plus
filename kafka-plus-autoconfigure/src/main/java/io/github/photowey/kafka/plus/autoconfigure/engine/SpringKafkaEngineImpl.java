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
package io.github.photowey.kafka.plus.autoconfigure.engine;

import io.github.photowey.kafka.plus.autoconfigure.core.getter.BeanFactoryGetter;
import io.github.photowey.kafka.plus.engine.KafkaEngine;
import io.github.photowey.kafka.plus.engine.holder.KafkaEngineHolder;
import io.github.photowey.kafka.plus.runtime.service.AdminService;
import io.github.photowey.kafka.plus.runtime.service.ConsumerService;
import io.github.photowey.kafka.plus.runtime.service.ProducerService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * {@code SpringKafkaEngineImpl}
 *
 * @author photowey
 * @since 2024/04/06
 * @version 3.7.0.1.0
 */
public class SpringKafkaEngineImpl implements KafkaEngine, BeanFactoryAware, BeanFactoryGetter, SmartInitializingSingleton {

    private ConfigurableListableBeanFactory beanFactory;

    // ----------------------------------------------------------------

    @Override
    public BeanFactory beanFactory() {
        return this.beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    // ----------------------------------------------------------------

    @Override
    public void afterSingletonsInstantiated() {
        KafkaEngineHolder.INSTANCE.kafkaEngine(this, true);
    }

    // ----------------------------------------------------------------

    @Override
    public AdminService adminService() {
        return this.beanFactory.getBean(AdminService.class);
    }

    @Override
    public ConsumerService consumerService() {
        return this.beanFactory.getBean(ConsumerService.class);
    }

    @Override
    public ProducerService producerService() {
        return this.beanFactory.getBean(ProducerService.class);
    }
}