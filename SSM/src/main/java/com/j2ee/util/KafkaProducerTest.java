package com.j2ee.util;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerTest implements Runnable {

    private final KafkaProducer<String, String> producer;
    private final String topic;
    private String message;
    public KafkaProducerTest(String topicName, String message) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topicName;
        this.message=message;
    }

    @Override
    public void run() {
        try {
            producer.send(new ProducerRecord<String, String>(topic, "Message", message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}