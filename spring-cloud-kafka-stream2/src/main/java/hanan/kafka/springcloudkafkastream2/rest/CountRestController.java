package hanan.kafka.springcloudkafkastream2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanan.kafka.springcloudkafkastream2.dao.AnalyticsBinding;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.*;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class CountRestController {

    private InteractiveQueryService interactiveQueryService;


    public CountRestController(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    //querying with key-value stores
/*    @GetMapping("/counts")
    public Map<String,Long> counts(){
        Map<String,Long> results = new HashMap<>();
        ReadOnlyKeyValueStore<String,Long> queryTableStoreType =
                this.interactiveQueryService.getQueryableStore(AnalyticsBinding.PAGE_COUNT_MV, QueryableStoreTypes.keyValueStore());
                KeyValueIterator<String,Long> all = queryTableStoreType.all();
                while(all.hasNext()){
                    KeyValue<String,Long> item = all.next();
                    results.put(item.key,item.value);
                }
        return results;
    }*/

    //with windowStore /
    /*@GetMapping("/counts")
    public Map<String,Long> counts(){
        Map<String,Long> results = new HashMap<>();
        ReadOnlyWindowStore<String,Long> queryTableStoreType =
                this.interactiveQueryService.getQueryableStore(AnalyticsBinding.PAGE_COUNT_MV,
                        QueryableStoreTypes.windowStore());
        KeyValueIterator<Windowed<String>,Long> all = queryTableStoreType.all();
        while(all.hasNext()){
            KeyValue<Windowed<String>,Long> item = all.next();
            results.put(item.key.key(),item.value);
        }
        return results;
    }*/

    //windowed version + server Sent Event
    @GetMapping(value = "/counts",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public SseEmitter counts()throws Exception{
        SseEmitter sseEmitter = new SseEmitter();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            try {
                Map<String,Long> results = new HashMap<>();
                ReadOnlyWindowStore<String,Long> queryableStoreTypes =
                        this.interactiveQueryService.getQueryableStore(AnalyticsBinding.PAGE_COUNT_MV,QueryableStoreTypes.windowStore());
                KeyValueIterator<Windowed<String>,Long> all = queryableStoreTypes.all();
                while (all.hasNext()){
                    KeyValue<Windowed<String>,Long> item = all.next();
                    results.put(item.key.key(),item.value);
                }
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(new ObjectMapper().writeValueAsString(results))
                        .id("->"+System.currentTimeMillis())
                        .name("table count");
                        sseEmitter.send(event);


            }catch (Exception ex){
                sseEmitter.completeWithError(ex);
            }
        },1000,1000, TimeUnit.MILLISECONDS);
        return sseEmitter;

    }




}
