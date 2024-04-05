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
package io.github.photowey.kafka.plus.engine;

import io.github.photowey.kafka.plus.runtime.service.AdminService;
import io.github.photowey.kafka.plus.runtime.service.ConsumerService;
import io.github.photowey.kafka.plus.runtime.service.ProducerService;
import io.github.photowey.kafka.plus.runtime.service.impl.AdminServiceImpl;
import io.github.photowey.kafka.plus.runtime.service.impl.ConsumerServiceImpl;
import io.github.photowey.kafka.plus.runtime.service.impl.ProducerServiceImpl;

/**
 * {@code KafkaEngineImpl}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public class KafkaEngineImpl extends AbstractEngine {

    @Override
    public AdminService adminService() {
        return this.getSharedObject(AdminService.class, AdminServiceImpl::new);
    }

    @Override
    public ConsumerService consumerService() {
        return this.getSharedObject(ConsumerService.class, ConsumerServiceImpl::new);
    }

    @Override
    public ProducerService producerService() {
        return this.getSharedObject(ProducerService.class, ProducerServiceImpl::new);
    }
}