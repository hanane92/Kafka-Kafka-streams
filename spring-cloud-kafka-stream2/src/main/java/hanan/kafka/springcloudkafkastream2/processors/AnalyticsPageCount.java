package hanan.kafka.springcloudkafkastream2.processors;

import hanan.kafka.springcloudkafkastream2.dao.AnalyticsBinding;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnalyticsPageCount {

    @StreamListener
    public void processData(@Input(AnalyticsBinding.PAGE_VIEWS_COUNT_IN) KTable<String,Long> counts){
        counts.toStream().foreach((k,v)->{
            log.info(k+" => "+v );
        });
    }
}
