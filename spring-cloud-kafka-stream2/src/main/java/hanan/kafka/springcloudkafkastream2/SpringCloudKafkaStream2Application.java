package hanan.kafka.springcloudkafkastream2;

import hanan.kafka.springcloudkafkastream2.dao.AnalyticsBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication(exclude = JmxAutoConfiguration.class)
@EnableBinding(AnalyticsBinding.class)
public class SpringCloudKafkaStream2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKafkaStream2Application.class, args);
	}

}
