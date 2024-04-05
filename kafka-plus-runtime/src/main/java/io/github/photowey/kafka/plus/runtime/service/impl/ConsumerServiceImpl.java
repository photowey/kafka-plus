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
package io.github.photowey.kafka.plus.runtime.service.impl;

import io.github.photowey.kafka.plus.core.clients.builder.consumer.ConsumerBuilder;
import io.github.photowey.kafka.plus.core.clients.builder.consumer.ConsumerBuilderImpl;
import io.github.photowey.kafka.plus.runtime.service.ConsumerService;

/**
 * {@code ConsumerServiceImpl}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public ConsumerBuilder createConsumer() {
        return new ConsumerBuilderImpl();
    }
}