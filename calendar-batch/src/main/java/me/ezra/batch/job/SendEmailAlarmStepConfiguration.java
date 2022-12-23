package me.ezra.batch.job;

import me.ezra.batch.job.listener.DebuggingItemReadListener;
import me.ezra.core.domain.email.SendMailBatchReq;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class SendEmailAlarmStepConfiguration {
    private final int CHUCK_SIZE = 10;

    @Bean
    @JobScope
    Step sendInvitationAlarmStep(
            StepBuilderFactory stepBuilderFactory,
            DebuggingItemReadListener<SendMailBatchReq> itemReadListener,
            ItemReader<SendMailBatchReq> sendInvitationAlarmItemReader,
            ItemWriter<SendMailBatchReq> sendEmailAlarmItemWriter
    ) {
        return stepBuilderFactory.get("sendInvitationAlarmStep")
                .listener(itemReadListener)
                .<SendMailBatchReq, SendMailBatchReq>chunk(CHUCK_SIZE)
                .reader(sendInvitationAlarmItemReader)
                .writer(sendEmailAlarmItemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    @JobScope
    Step sendScheduleAlarmStep(
            StepBuilderFactory stepBuilderFactory,
            ItemReader<SendMailBatchReq> sendScheduleAlarmItemReader,
            ItemWriter<SendMailBatchReq> sendEmailAlarmItemWriter
    ) {
        return stepBuilderFactory.get("sendScheduleAlarmStep")
                .<SendMailBatchReq, SendMailBatchReq>chunk(CHUCK_SIZE)
                .reader(sendScheduleAlarmItemReader)
                .writer(sendEmailAlarmItemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    @StepScope
    public JdbcCursorItemReader<SendMailBatchReq> sendInvitationAlarmItemReader(
            DataSource dataSource
    ) {
        return new JdbcCursorItemReaderBuilder<SendMailBatchReq>()
                .name("sendInvitationAlarmItemReader")
                .fetchSize(CHUCK_SIZE)
                .dataSource(dataSource)
                .verifyCursorPosition(false)
                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
                .sql(
                        "SELECT s.id, s.title, s.start_at, u.email AS user_email\n" +
                                "FROM invitation i\n" +
                                "    INNER JOIN schedule s on i.schedule_id = s.id\n" +
                                "    INNER JOIN user u on s.writer_id = u.id\n" +
                                "WHERE\n" +
                                "    start_at > now() + INTERVAL 10 MINUTE\n" +
                                "AND\n" +
                                "    start_at <= now() + INTERVAL 11 MINUTE\n" +
                                "AND\n" +
                                "    request_status = 'ACCEPTED';")
                .build();
    }


    @Bean
    @StepScope
    public JdbcCursorItemReader<SendMailBatchReq> sendScheduleAlarmItemReader(
            DataSource dataSource
    ) {
        return new JdbcCursorItemReaderBuilder<SendMailBatchReq>()
                .name("sendScheduleAlarmItemReader")
                .fetchSize(CHUCK_SIZE)
                .dataSource(dataSource)
                .verifyCursorPosition(false)
                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
                .sql(
                        "SELECT s.id, s.title, s.start_at, u.email AS user_email\n" +
                                "FROM schedule s \n" +
                                "    INNER JOIN user u on s.writer_id = u.id\n" +
                                "WHERE\n" +
                                "        start_at > now() + INTERVAL 10 MINUTE\n" +
                                "   AND\n" +
                                "        start_at <= now() + INTERVAL 11 MINUTE;"
                )
                .build();
    }
}
