package com.j2ee.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import kafka.utils.Json;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;



public class KafkaConsumerTest{

    private final KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private final String topic;
    public KafkaConsumerTest(String topicName,String groupName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupName);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(props);
        this.topic = topicName;
        this.consumer.subscribe(Arrays.asList(topic));
    }

    public List<String> consume(){
        List<String> list=new ArrayList<>();
        msgList=consumer.poll(1000);
        if(msgList!=null&&msgList.count()>0){
            for(ConsumerRecord<String,String> record:msgList){
                list.add(record.value());
            }
        }
        consumer.close();
        return list;
    }

}