package hanan.kafka.springcloudkafkastream.service;

import com.sun.deploy.security.ValidationState;
import hanan.kafka.springcloudkafkastream.dao.GreetingStreams;
import hanan.kafka.springcloudkafkastream.entities.Greetings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class GreetingService {

    private final GreetingStreams greetingStreams;

    public GreetingService(GreetingStreams greetingStreams) {
        this.greetingStreams = greetingStreams;
    }

    public void sendGreeting(final Greetings greetings){
        log.info("sending greetins ", greetings.toString());
        MessageChannel messageChannel = greetingStreams.outBoundGreeting();
        messageChannel.send(MessageBuilder
                .withPayload(greetings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

    }


}
