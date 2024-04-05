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

import io.github.photowey.kafka.plus.core.clients.builder.admin.AdminBuilder;
import io.github.photowey.kafka.plus.core.clients.builder.admin.AdminBuilderImpl;
import io.github.photowey.kafka.plus.core.clients.builder.admin.topic.NewTopicBuilder;
import io.github.photowey.kafka.plus.core.clients.builder.admin.topic.NewTopicBuilderImpl;
import io.github.photowey.kafka.plus.runtime.service.AdminService;

/**
 * {@code AdminServiceImpl}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public AdminBuilder createAdmin() {
        return new AdminBuilderImpl();
    }

    @Override
    public NewTopicBuilder createTopic() {
        return new NewTopicBuilderImpl();
    }
}