package me.ezra.batch.job.writer;

import lombok.extern.slf4j.Slf4j;
import me.ezra.core.domain.email.SendMailBatchReq;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("!test")
@Slf4j
public class RealItemWriter {

    @Bean
    ItemWriter<SendMailBatchReq> sendEmailAlarmWriter() {
        return list -> {
            log.trace("Item writer list: {}",list);
            new RestTemplate().postForObject("http://localhost:8080/api" +
                    "/batch/send/mail", list, Object.class);

        };
    }
}
