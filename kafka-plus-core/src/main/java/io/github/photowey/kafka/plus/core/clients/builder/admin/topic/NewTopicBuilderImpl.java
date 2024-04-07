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

import io.github.photowey.kafka.plus.core.exception.KafkaPlusRuntimeException;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code NewTopicBuilderImpl}
 *
 * @author photowey
 * @since 2024/04/05
 * @version 1.0.0
 */
public class NewTopicBuilderImpl implements NewTopicBuilder {

    private String name;
    private Integer numPartitions;
    private Short replicationFactor;
    private Map<Integer, List<Integer>> replicasAssignments;
    private Map<String, String> configs = null;

    @Override
    public NewTopicBuilder topic(String topic) {
        this.name = topic;

        return this;
    }

    @Override
    public NewTopicBuilder numPartitions(Integer numPartitions) {
        this.numPartitions = numPartitions;

        return this;
    }

    @Override
    public NewTopicBuilder replicationFactor(Integer replicationFactor) {
        if (null != replicationFactor) {
            this.replicationFactor = replicationFactor.shortValue();
        }

        return this;
    }

    @Override
    public NewTopicBuilder replicasAssignments(Map<Integer, List<Integer>> replicasAssignments) {
        this.replicasAssignments = replicasAssignments;

        return this;
    }

    @Override
    public NewTopicBuilder configs(Map<String, String> configs) {
        this.configs = configs;

        return this;
    }

    @Override
    public NewTopic build() {
        this.check();

        if (null != this.replicasAssignments) {
            this.checkReplicasAssignmentsIfNecessary();

            NewTopic newTopic = new NewTopic(this.name, this.replicasAssignments);
            this.configs(newTopic, this.configs);

            return newTopic;
        }

        NewTopic newTopic = new NewTopic(this.name, Optional.ofNullable(this.numPartitions), Optional.ofNullable(this.replicationFactor));
        this.configs(newTopic, this.configs);

        return newTopic;
    }

    // ----------------------------------------------------------------

    private void check() {
        this.checkTopic();
    }

    // ----------------------------------------------------------------

    private void checkTopic() {
        if (null == this.name || this.name.isEmpty()) {
            throw new KafkaPlusRuntimeException("The topic name can't be null/empty.");
        }
    }

    protected void checkReplicasAssignmentsIfNecessary() {
        if (this.replicasAssignments.isEmpty()) {
            throw new KafkaPlusRuntimeException("The replicasAssignments can't be empty");
        }
    }

    // ----------------------------------------------------------------

    private void configs(NewTopic topic, Map<String, String> configs) {
        if (null != configs) {
            topic.configs(configs);
        }
    }
}