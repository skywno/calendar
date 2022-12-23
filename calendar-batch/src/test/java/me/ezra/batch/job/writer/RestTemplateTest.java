package me.ezra.batch.job.writer;

import me.ezra.core.domain.email.SendMailBatchReq;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestTemplateTest {

    @Test
    void test1() {
        List<SendMailBatchReq> list = List.of(new SendMailBatchReq(1L, "title", LocalDateTime.now(), "gyeha.lim@aalto.fi"));
        new RestTemplate().postForObject("http://localhost:8080/api/batch/send/mail", list, Object.class);
    }

    @Test
    void test2() {
        List<SendMailBatchReq> list = List.of(new SendMailBatchReq(1L, "title", LocalDateTime.now(), "test@test.com"));
        new RestTemplate().postForObject("http://localhost:8080/api/batch/send/mail", list, Object.class);
    }


}