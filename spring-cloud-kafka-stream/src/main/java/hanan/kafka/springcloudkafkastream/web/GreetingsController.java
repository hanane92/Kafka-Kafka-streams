package hanan.kafka.springcloudkafkastream.web;

import hanan.kafka.springcloudkafkastream.entities.Greetings;
import hanan.kafka.springcloudkafkastream.service.GreetingService;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    private final GreetingService greetingService;

    public GreetingsController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greetings")
    public void greetings(@RequestParam("message") String message){

        Greetings greetings = Greetings.builder()
                .message(message)
                .timesTamp(System.currentTimeMillis())
                .build();
        greetingService.sendGreeting(greetings);

    }
}
