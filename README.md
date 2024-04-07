# `kafka-plus`

An extender of Apache Kafka (the "Kafka"), named `kafka-plus` (the "KP").



## Examples

- `Spring Boot`
  - [kafkaplus-spring-boot-starter-examples](https://github.com/photowey/kafkaplus-spring-boot-starter-examples)

- `Spring Boot v3`
  - [kafkaplus-spring-boot3-starter-examples](https://github.com/photowey/kafkaplus-spring-boot3-starter-examples)



## 1.`Usage`

Add this to your `pom.xml`

### 1.1.`Kafka-Clients`

```xml
<!-- ${kafka-plus.version} == ${latest.version} -->
<dependency>
    <groupId>io.github.photowey</groupId>
    <artifactId>kafka-plus-engine</artifactId>
    <version>${kafka-plus.version}</version>
</dependency>
```



### 1.2.`Spring Boot`

```xml
<!-- ${kafka-plus.version} == ${latest.version} -->
<dependency>
    <groupId>io.github.photowey</groupId>
    <artifactId>kafkaplus-spring-boot-starter</artifactId>
    <version>${kafka-plus.version}</version>
</dependency>
```



### 1.3.`Spring Boot V3`

```xml
<!-- ${kafka-plus.version} == ${latest.version} -->
<dependency>
    <groupId>io.github.photowey</groupId>
    <artifactId>kafkaplus-spring-boot3-starter</artifactId>
    <version>${kafka-plus.version}</version>
</dependency>
```



## 2.`APIs`

> `Base`: `io.github.photowey.kafka.plus.engine.LocalTest`

### 2.1.`KafkaEngine`

```java
KafkaEngine kafkaEngine = KafkaEngineHolder.INSTANCE.kafkaEngine()
```



### 2.1.`AdminService`

> `@see` `io.github.photowey.kafka.plus.core.enums.Kafka.Bootstrap.Server`

#### 2.1.1.`Admin`

```java
class AdminServiceTest extends LocalTest {

    @Test
    void testAdmin() {
        KafkaEngine kafkaEngine = super.kafkaEngine();

        try (Admin admin = kafkaEngine.adminService().createAdmin()
                .boostrapServers(this.defaultBoostrapServers())
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            Assertions.assertNotNull(admin);
        }
    }
}
```



#### 2.1.2.`Topic`

##### 2.1.2.1.`Create`

```java
class AdminServiceTest extends LocalTest {

    @Test
    void testCreateTopic() throws Exception {
        KafkaEngine kafkaEngine = super.kafkaEngine();

        try (Admin admin = kafkaEngine.adminService().createAdmin()
                .boostrapServers(this.defaultBoostrapServers())
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            NewTopic topic = kafkaEngine.adminService().createTopic()
                    .topic(this.defaultTopic())
                    .numPartitions(1)
                    .replicationFactor(1)
                    .build();

            CreateTopicsResult topicsResult = admin.createTopics(Collections.singleton(topic));
            
            // topicsResult...
        }
    }
}
```



##### 2.1.2.2.`Delete`

```java
class AdminServiceTest extends LocalTest {

    @Test
    void testDeleteTopic() throws Exception {
        KafkaEngine kafkaEngine = super.kafkaEngine();

        try (Admin admin = kafkaEngine.adminService().createAdmin()
                .boostrapServers(this.defaultBoostrapServers())
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            DeleteTopicsResult topicsResult = admin.deleteTopics(Collections.singleton(this.defaultTopic()));
            
            // topicsResult...
        }
    }
}
```



### 2.2.`ConsumerService`

> `@see` `io.github.photowey.kafka.plus.core.enums.Kafka.Bootstrap.Server`
>
> `@see` `io.github.photowey.kafka.plus.core.enums.Kafka.Consumer`

#### 2.2.1.`Without subscribe` 

```java
class ConsumerServiceTest extends LocalTest {

    @Test
    void testConsumer() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaConsumer<String, String> consumer = kafkaEngine.consumerService().createConsumer()
                .boostrapServers(this.defaultBoostrapServers())
                .keyDeserializer(StringDeserializer.class)
                .valueDeserializer(StringDeserializer.class)
                .autoOffsetReset(Kafka.Consumer.AutoOffsetReset.EARLIEST)
                .groupId(this.defaultGroup())
                .autoCommit(true)
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            // subscribe
            consumer.subscribe(Collections.singletonList(this.defaultTopic()));

            Assertions.assertNotNull(consumer);
        }
    }
}
```



#### 2.2.2.`With subscribe`

```java
class ConsumerServiceTest extends LocalTest {

    @Test
    void testConsumer_with_subscribe() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaConsumer<String, String> consumer = kafkaEngine.consumerService().createConsumer()
                .boostrapServers(this.defaultBoostrapServers())
                .keyDeserializer(StringDeserializer.class)
                .valueDeserializer(StringDeserializer.class)
                .autoOffsetReset(Kafka.Consumer.AutoOffsetReset.EARLIEST)
                .groupId(this.defaultGroup())
                .autoCommit(true)
             	// subscribe
                .subscribe(this.defaultTopic())
                .checkConfigs(super::testBoostrapServers)
                .build()) {

            Assertions.assertNotNull(consumer);
        }
    }
}
```



### 2.3.`ProducerService`

> `@see` `io.github.photowey.kafka.plus.core.enums.Kafka.Bootstrap.Server`
>
> `@see` `io.github.photowey.kafka.plus.core.enums.Kafka.Producer`



#### 2.3.1.`KafkaProducer`

```java
class ProducerServiceTest extends LocalTest {

    @Test
    void testProducer() {
        StringSerializer keySerializer = new StringSerializer();
        StringSerializer valueSerializer = new StringSerializer();

        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, String> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(keySerializer)
                .valueSerializer(valueSerializer)
                .build()) {

            Assertions.assertNotNull(producer);
        }
    }
}
```



#### 2.3.2.`ProducerRecord`

```java
class ProducerServiceTest extends LocalTest {

    @Test
    void testProducerRecord() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        ProducerRecord<String, String> record = kafkaEngine.producerService().createProducerRecord()
                .topic(this.defaultTopic())
                .key("key-9527") // nullable
                .value("value-9527")
                .build();

        Assertions.assertNotNull(record);
    }
}
```



#### 2.3.3.`Jackson`

```java
class ProducerServiceTest extends LocalTest {

    @Test
    void testProducer_serializer_custom_jackson() {
        KafkaEngine kafkaEngine = this.kafkaEngine();

        try (KafkaProducer<String, Person> producer = kafkaEngine.producerService().createProducer()
                .boostrapServers(this.defaultBoostrapServers())
                .keySerializer(StringSerializer.class.getName())
             	// JacksonSerializer
                .valueSerializer(JacksonSerializer.class.getName())
                .build()) {

            // producer...
        }

        sleep(1_000L);
    }
}
```



## 3.`Spring`

### 3.1.`KafkaEngine`

```java
public class KafkaPlusConfigure {

    @Configuration
    static class EngineConfigure {

        @Bean
        public KafkaEngine kafkaEngine() {
            // KafkaEngine Spring impl.
            return new SpringKafkaEngineImpl();
        }
    }
}
```



### 3.2.`KafkaEngineSpringAware`

```java
// io.github.photowey.kafka.plus.autoconfigure.engine.processor.KafkaEngineSpringAware

public interface KafkaEngineSpringAware extends KafkaEngineAware, Aware {}
```



### 3.3.`KafkaPlusProperties`

```java
// io.github.photowey.kafka.plus.autoconfigure.core.property.KafkaPlusProperties

public class KafkaPlusProperties implements Serializable {

    private static final long serialVersionUID = 8550578442514111961L;

    private Mode mode = new Mode();
    private Bootstrap bootstrap = new Bootstrap();
    private Admin admin = new Admin();
    private Consumer consumer = new Consumer();
    private Producer producer = new Producer();
 
    // ...
}
```



### 3.4.`BeanFactoryGetter`

```java
// io.github.photowey.kafka.plus.autoconfigure.core.getter.BeanFactoryGetter

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

```



## 4.`Next`

### 4.1.`Autoconfigure`

```java
// Autoconfigure KafkaProducer<K,V>
```

