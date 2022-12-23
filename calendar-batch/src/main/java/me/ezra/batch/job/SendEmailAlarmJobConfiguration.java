package me.ezra.batch.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendEmailAlarmJobConfiguration {

    @Bean
    public Job sendEmailAlarmJob(
            JobBuilderFactory jobBuilderFactory,
            Step sendInvitationAlarmStep,
            Step sendScheduleAlarmStep
    ) {
        return jobBuilderFactory.get("sendEmailAlarmJob")
                .start(sendInvitationAlarmStep)
                .next(sendScheduleAlarmStep)
                .build();
    }
}
