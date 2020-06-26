package hanan.kafka.springcloudkafkastream2.source;

import hanan.kafka.springcloudkafkastream2.dao.AnalyticsBinding;
import hanan.kafka.springcloudkafkastream2.entities.PageViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class PageViewEventSource implements ApplicationRunner {

    private MessageChannel pageViewsOutChanel;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public PageViewEventSource(AnalyticsBinding analyticsBinding) {
        this.pageViewsOutChanel = analyticsBinding.pageViewsOut();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("------------------------------");
        List<String> names = Arrays.asList("hanane","charaf","youness","salim","gggg");
        List<String> pages = Arrays.asList("blog","chat","profile","about","contact");
        Runnable runnable=()-> {
            String rNames = names.get(new Random().nextInt(names.size()));
            String rPages = pages.get(new Random().nextInt(pages.size()));
            PageViewEvent pageViewEvent = new PageViewEvent(rNames,rPages, 100+(int) (Math.random()*1000));
            Message<PageViewEvent> eventMessage = MessageBuilder
                    .withPayload(pageViewEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY,pageViewEvent.getUserId())
                    .build();

            try {

                this.pageViewsOutChanel.send(eventMessage);
                log.info("sending "+eventMessage.toString());

            }catch (Exception e) {
                e.printStackTrace();
            }



        };
        System.out.println("----------------------------------------------------");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable,1000,1000, TimeUnit.MILLISECONDS);
    }
}
