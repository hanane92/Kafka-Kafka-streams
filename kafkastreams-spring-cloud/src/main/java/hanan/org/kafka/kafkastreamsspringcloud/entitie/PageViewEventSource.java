package hanan.org.kafka.kafkastreamsspringcloud.entitie;
import hanan.org.kafka.kafkastreamsspringcloud.analytics.AnalyticsBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Component
@Slf4j
public class PageViewEventSource implements ApplicationRunner {





    private MessageChannel pageViewOutChannel;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public PageViewEventSource(AnalyticsBinding analyticsBinding) {
        this.pageViewOutChannel = analyticsBinding.pageViewsOut();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("------------------------------");
        List<String> names = Arrays.asList("hanane","charaf","youness","salim");
        List<String> pages = Arrays.asList("blog","chat","profile","about","contact");
        Runnable runnable=()->{
            String rPages = pages.get(new Random().nextInt(pages.size()));
            String rNames= names.get(new Random().nextInt(names.size()));
            PageViewEvent pageViewEvent = new PageViewEvent(rPages,rNames, 100+(int) (Math.random()*1000));
            Message<PageViewEvent> pageViewEventMessage = MessageBuilder
                    .withPayload(pageViewEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY,pageViewEvent.getUserId().getBytes())
                    .build();

            try {

                this.pageViewOutChannel.send(pageViewEventMessage);
                log.info("sending "+pageViewEventMessage.toString());

            }catch (Exception e) {
                e.printStackTrace();
            }

            log.info("sending message"+pageViewEvent.toString());

        };
        System.out.println("----------------------------------------------------");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable,1000,1000, TimeUnit.MILLISECONDS);
    }
}
