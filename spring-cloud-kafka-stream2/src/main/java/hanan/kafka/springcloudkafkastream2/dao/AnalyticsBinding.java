package hanan.kafka.springcloudkafkastream2.dao;

import hanan.kafka.springcloudkafkastream2.entities.PageViewEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AnalyticsBinding {

    String PAGE_VIEWS_OUT ="pvout";
    String PAGE_VIEWS_IN ="pvin";
    String PAGE_VIEWS_COUNT_OUT ="pcout";
    String PAGE_VIEWS_COUNT_IN ="pcin";
    String PAGE_COUNT_MV ="PAGE_COUNT";

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();

    @Input(PAGE_VIEWS_IN)
    KStream<String, PageViewEvent> pageViewIn();

    @Output(PAGE_VIEWS_COUNT_OUT)
    KStream<String,Long> pageCountOutPut();

    @Input(PAGE_VIEWS_COUNT_IN)
    KTable<String,Long> pageCountInput();
}
