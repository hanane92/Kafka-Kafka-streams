package hanan.kafka.springcloudkafkastream2.processors;

import hanan.kafka.springcloudkafkastream2.dao.AnalyticsBinding;
import hanan.kafka.springcloudkafkastream2.entities.PageViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

@Slf4j
@Component

public class PageViewEventProcessor {

   //key value version
   /* @StreamListener
    @SendTo(AnalyticsBinding.PAGE_VIEWS_COUNT_OUT)
    public KStream<String, Long> process(@Input(AnalyticsBinding.PAGE_VIEWS_IN)KStream<String,PageViewEvent> events){

        return events
                .filter((k,v)->(v.getDuration()>5))
                .map((k,v)->new KeyValue<>(v.getPage(),"0"))
                .groupByKey()
                .count(Materialized.as(AnalyticsBinding.PAGE_COUNT_MV)).toStream();

    }*/

    //windowed version
    @StreamListener
    @SendTo(AnalyticsBinding.PAGE_VIEWS_COUNT_OUT)
    public KStream<String, Long> process(@Input(AnalyticsBinding.PAGE_VIEWS_IN)KStream<String,PageViewEvent> events){

        return events
                .filter((k,v)->(v.getDuration()>5))
                .map((k,v)->new KeyValue<>(v.getUserId(),"0"))
                .groupByKey()
                .windowedBy(TimeWindows.of(1000))
                .count(Materialized.as(AnalyticsBinding.PAGE_COUNT_MV)).toStream()
                .map((k,v)-> new KeyValue<>(k.key(),v));

    }


















}
