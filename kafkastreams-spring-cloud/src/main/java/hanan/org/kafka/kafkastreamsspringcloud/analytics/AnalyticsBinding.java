package hanan.org.kafka.kafkastreamsspringcloud.analytics;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


//Bindings — a collection of interfaces that identify the input and output channels declaratively
public interface AnalyticsBinding {

    String PAGE_VIEWS_OUT ="pvout";

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();
}
