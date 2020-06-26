package hanan.org.kafka.kafkastreamsspringcloud.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PageViewEvent {

    private String userId;
    private String page;
    private int duration;
}
