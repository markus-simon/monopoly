package io.vertx.example;

import java.util.*;

import io.vertx.kafka.client.common.TopicPartition;
import org.json.JSONObject;

import io.vertx.kafka.client.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.kafka.client.common.PartitionInfo;


public class KafkaVerticle extends AbstractVerticle {


    @Override
    public void start() throws Exception {

        Properties consumerConfig = new Properties();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "my_group");
        consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerConfig.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, true);
        consumerConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

// use consumer for interacting with Apache Kafka
        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, consumerConfig);

        EventBus eb = vertx.eventBus();

        eb.consumer("topics.list", message -> {



            consumer.listTopics(ar -> {
                if (ar.succeeded()) {
                    Map<String, List<PartitionInfo>> map = ar.result();
                    Set<String> topics = new HashSet<>();
                    Map<String, Object> data = new HashMap<>();
                    map.forEach((topic, partitions) -> {
                        if (!topic.equals("__consumer_offsets")) {
                            topics.add(topic);
                            data.put(topic, map.get(topic));

                            Map<String, Object> partitionsHashMap = new HashMap<>();


/*
                            map.get(topic).forEach(ddd) -> {
*/
/*
                            consumer.position();
*/

                        }
                    });
                    JSONObject json = new JSONObject(data);
                    consumer.subscribe(topics);
                    message.reply(json.toString(4));



                    consumer.handler(record -> {
                        Map<String, Object> entry = new HashMap<>();
                        entry.put("topic", record.topic());
                        entry.put("value", record.value());
                        entry.put("partition", record.partition());
                        entry.put("offset", record.offset());
                        entry.put("key", record.key());
                        JSONObject jsonMessage = new JSONObject(entry);
                        eb.publish("kafka.producer.message", jsonMessage.toString(4));
                    });
                }
            });
        });
    }
}