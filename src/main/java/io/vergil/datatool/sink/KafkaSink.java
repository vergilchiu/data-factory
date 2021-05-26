package io.vergil.datatool.sink;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import io.vergil.datatool.data.BuildData;
import io.vergil.datatool.data.Car;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author: wei.zhao
 * @create: 2020-05-27 11:04
 **/
public class KafkaSink {

    public static void main(String[] args) throws Exception {

        Calendar start = Calendar.getInstance();
        start.set(2019, 0, 1);
        Calendar end = Calendar.getInstance();
        end.set(2019, 0, 2);
        BuildData buildData = new BuildData(start.getTime(), end.getTime(), 50, 50, 50);
        List<Car> next = null;
        int total = 0;


        Properties props = new Properties();
        props.put("bootstrap.servers", "10.12.6.59:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        while ((next = buildData.next(1000)).size() != 0) {
            for (int i = 0; i < next.size(); i++) {
                Car car = next.get(i);
                String str = JSONObject.toJSONString(car);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("kylin_streaming_topic", str);
                producer.send(record);
                total++;
            }
            System.out.println(new Date() + ",total = " + total);
            Thread.sleep(100);
        }
        System.out.println("total = " + total);
    }


    private void init() {

    }
}
