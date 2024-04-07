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

/**
 * {@code KafkaEngine}
 *
 * @author photowey
 * @date 2024/04/05
 * @since 1.0.0
 */
public interface KafkaEngine extends Engine {

    /**
     * Create {@link AdminService} instance.
     * <pre>
     * try (Admin admin = KafkaEngineHolder.INSTANCE.kafkaEngine().adminService().createAdmin()
     *        .boostrapServers("localhost:9092")
     *        .checkMap((x) -&gt; {})
     *        .create()) {
     *    // do something.
     * }
     * </pre>
     *
     * @return {@link AdminService}
     */
    AdminService adminService();

    /**
     * Create {@link ConsumerService} instance.
     *
     * @return {@link ConsumerService}
     */
    ConsumerService consumerService();

    /**
     * Create {@link ProducerService} instance.
     *
     * @return {@link ProducerService}
     */
    ProducerService producerService();
}
