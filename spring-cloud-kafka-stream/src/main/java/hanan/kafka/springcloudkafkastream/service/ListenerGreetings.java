package hanan.kafka.springcloudkafkastream.service;

import hanan.kafka.springcloudkafkastream.dao.GreetingStreams;
import hanan.kafka.springcloudkafkastream.entities.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerGreetings {

    @StreamListener(GreetingStreams.INPUT)
    public void handelGreetings(@Payload Greetings greetings){
        log.info("received greetings : "+greetings);
    }
}
