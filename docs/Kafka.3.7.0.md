# `Kafka`

## 1.Mode

### 1.1.`Zookeeper`

#### 1.1.1.`Node`

- /admin
    - delete_topics
- /brokers
    - ids
        - 0
        - …
    - seqid
    - topics
        - `io.github.photowey.kafka.hello.topic`
            - partition
                - 0
                - 1
                - 2
        - …
- cluster
    - id
- config
    - brokers
    - **changes**
    - clients
    - ips
    - topics
        - `io.github.photowey.kafka.hello.topic`
            - `{"version":1,"config":{}}`
    - users
- consumers
- <font style="color:#FFCC66">controller</font>
- `controller_eppch`
- feature
- **isr_change_notification**
- latest_producer_id_block
- **log_dir_event_notification**

### 1.2.`KRaft`

## 2.Producer

### 2.1.Send

1. Interceptors
    1. main.thread
2. Cluster Metadata
    1. main.thread
3. Serializer
    1. main.thread
4. Partitioner
    1. main.thread
5. RecordAccumulator
    1. main.thread
    2. sender.thread
6. Sender
    1. sender.thread
7. NetworkClient
    1. sender.thread