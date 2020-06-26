package hanan.org.kafka.kafkastreamsspringcloud;

import hanan.org.kafka.kafkastreamsspringcloud.analytics.AnalyticsBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.web.PathMappedEndpoints;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@SpringBootApplication(exclude =JmxAutoConfiguration.class)
@EnableBinding(AnalyticsBinding.class)//binds the Kafka topic to its input or an output (or both)./get immediate connectivity to a message broker
@Slf4j
public class KafkaSpringCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringCloudApplication.class, args);
    }

}
