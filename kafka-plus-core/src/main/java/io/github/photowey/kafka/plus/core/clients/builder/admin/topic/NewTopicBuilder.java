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
package io.github.photowey.kafka.plus.core.clients.builder.admin.topic;

import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Map;

/**
 * {@code NewTopicBuilder}
 *
 * @author photowey
 * @since 2024/04/05
 * @version 3.7.0.1.0
 */
public interface NewTopicBuilder {

    /**
     * The topic name.
     *
     * @param topic the topic name
     * @return {@link NewTopicBuilder}
     */
    NewTopicBuilder topic(String topic);

    /**
     * The numPartitions.
     *
     * @param numPartitions the num of partitions.
     * @return {@link NewTopicBuilder}
     */
    NewTopicBuilder numPartitions(Integer numPartitions);

    /**
     * The replicationFactor.
     *
     * @param replicationFactor the replication factor.
     * @return {@link NewTopicBuilder}
     */
    NewTopicBuilder replicationFactor(Integer replicationFactor);

    /**
     * The replicasAssignments.
     *
     * @param replicasAssignments the replicas assignments.
     * @return {@link NewTopicBuilder}
     */
    NewTopicBuilder replicasAssignments(Map<Integer, List<Integer>> replicasAssignments);

    /**
     * The custom configs.
     *
     * @param configs the custom configs.
     * @return {@link NewTopicBuilder}
     */
    NewTopicBuilder configs(Map<String, String> configs);

    /**
     * Build {@link NewTopic} instance.
     *
     * @return {@link NewTopic}
     */
    NewTopic build();
}
