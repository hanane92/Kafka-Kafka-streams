package hanan.kafka.springcloudkafkastream2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageViewEvent {

    private String userId;
    private String page;
    private int duration;
}
