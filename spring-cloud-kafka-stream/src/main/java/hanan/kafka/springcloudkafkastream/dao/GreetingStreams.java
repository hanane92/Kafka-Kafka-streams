package hanan.kafka.springcloudkafkastream.dao;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface GreetingStreams {

    String INPUT = "greeting-in";
    String OUTPUT = "greeting-out";

    @Input(INPUT)
    SubscribableChannel inBoundGreeting();

    @Output(OUTPUT)
    MessageChannel outBoundGreeting();

}
