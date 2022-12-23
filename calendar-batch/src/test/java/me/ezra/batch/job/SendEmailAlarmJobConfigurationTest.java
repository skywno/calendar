package me.ezra.batch.job;

import me.ezra.core.domain.email.MailBatchReqRepository;
import me.ezra.core.domain.email.SendMailBatchReq;
import me.ezra.core.domain.schedule.InvitationRepository;
import me.ezra.core.domain.schedule.ScheduleRepository;
import me.ezra.core.domain.schedule.domain.Invitation;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;
import me.ezra.core.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest
@ComponentScan("me.ezra")
@ActiveProfiles("test")
class SendEmailAlarmJobConfigurationTest {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    Job SendEmailAlarmJob;
    @Autowired
    MailBatchReqRepository mailBatchReqRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    InvitationRepository invitationRepository;

    protected JobExecution launchJob(Job job, JobParameters jobParameters) throws Exception {
        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJob(job);
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);

        return jobLauncherTestUtils.launchJob(jobParameters);
    }

    @BeforeEach
    void deleteAll() {
        mailBatchReqRepository.deleteAll();
        invitationRepository.deleteAll();
        scheduleRepository.deleteAll();
        userRepository.deleteAll();
    }


    @DisplayName("10분-11분 후에 시작하는 스케쥴 1개 ")
    @Test
    void sendEmailAlarmJob() throws Exception {
//        // Given
//        User user1 = new User("name1", "email1@email.com", "password", LocalDate.now());
//        userRepository.save(user1);
//
//        Schedule schedule1 = Schedule.notification("타이틀1", LocalDateTime.now().plusMinutes(10), user1);
//        Schedule schedule2 = Schedule.notification("타이틀2", LocalDateTime.now().plusMinutes(11), user1);
//        Schedule schedule3 = Schedule.notification("타이틀3", LocalDateTime.now().plusMinutes(12), user1);
//        Schedule schedule4 = Schedule.notification("타이틀4", LocalDateTime.now().plusMinutes(13), user1);
//        Schedule schedule5 = Schedule.notification("타이틀5", LocalDateTime.now().plusMinutes(14), user1);
//        scheduleRepository.saveAll(List.of(schedule1, schedule2, schedule3, schedule4, schedule5));
//
//        // When
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addDate("date", new Date())
//                .toJobParameters();
//        JobExecution jobExecution = launchJob(SendEmailAlarmJob, jobParameters);
//
//        // Then
//        then(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
//        then(userRepository.findAll()).hasSize(1);
//        then(scheduleRepository.findAll()).hasSize(5);
//
//        List<SendMailBatchReq> sentMails = mailBatchReqRepository.findAll();
//        then(sentMails).hasSize(1);
//        then(sentMails.get(0).getTitle()).isEqualTo("타이틀2");
    }

    @DisplayName("일치하는 스케쥴 1개 + invitation 1개")
    @Test
    void sendEmailAlarmJob2() throws Exception {
//        // Given
//        User user1 = new User("name1", "email1@email.com", "password", LocalDate.now());
//        User user2 = new User("name2", "email2@email.com", "password", LocalDate.now());
//        userRepository.saveAll(List.of(user1, user2));
//
//        Schedule eventSchedule = Schedule.event("타이틀1", "설명", LocalDateTime.now().plusMinutes(11), LocalDateTime.now().plusDays(5),user1);
//        scheduleRepository.save(eventSchedule);
//
//        Invitation invitation1 = new Invitation(eventSchedule, user1);
//        Invitation invitation2 = new Invitation(eventSchedule, user2);
//        ReflectionTestUtils.setField(invitation1, "requestStatus", RequestStatus.ACCEPTED);
//        invitationRepository.saveAll(List.of(invitation1, invitation2));
//
//
//        // When
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addDate("date", new Date())
//                .toJobParameters();
//        JobExecution jobExecution = launchJob(SendEmailAlarmJob, jobParameters);
//
//        // Then
//        then(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
//        List<SendMailBatchReq> sentMails = mailBatchReqRepository.findAll();
//        then(sentMails.size()).isEqualTo(2);

    }
}