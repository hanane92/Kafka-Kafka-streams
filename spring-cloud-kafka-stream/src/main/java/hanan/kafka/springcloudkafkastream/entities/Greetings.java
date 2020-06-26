package hanan.kafka.springcloudkafkastream.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Greetings {

    private long timesTamp;
    private String message;
}
