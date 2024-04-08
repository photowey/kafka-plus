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
package io.github.photowey.kafka.plus.autoconfigure.core.getter;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * {@code BeanFactoryGetter}
 *
 * @author photowey
 * @since 2024/04/06
 * @version 1.0.0
 */
public interface BeanFactoryGetter {

    BeanFactory beanFactory();

    default ListableBeanFactory listableBeanFactory() {
        return (ListableBeanFactory) this.beanFactory();
    }

    default ConfigurableBeanFactory configurableBeanFactory() {
        return (ConfigurableBeanFactory) this.beanFactory();
    }

    default ConfigurableListableBeanFactory configurableListableBeanFactory() {
        return (ConfigurableListableBeanFactory) this.beanFactory();
    }
}
