package me.ezra.batch.job.writer;


import me.ezra.core.domain.email.MailBatchReqRepository;
import me.ezra.core.domain.email.SendMailBatchReq;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestItemWriter {

    @Bean
    @StepScope
    public RepositoryItemWriter<SendMailBatchReq> sendEmailAlarmItemWriter(
            MailBatchReqRepository mailBatchReqRepository
    ) {
        return new RepositoryItemWriterBuilder<SendMailBatchReq>()
                .repository(mailBatchReqRepository)
                .methodName("save")
                .build();
    }
}
