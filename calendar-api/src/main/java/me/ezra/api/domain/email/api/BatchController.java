package me.ezra.api.domain.email.api;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.email.EmailService;
import me.ezra.core.domain.email.SendMailBatchReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BatchController {
    private final EmailService emailService;

    @PostMapping("/api/batch/send/mail")
    public ResponseEntity<Void> sendMail(@RequestBody List<SendMailBatchReq> reqs) {
        reqs.forEach(sendMailBatchReq -> emailService.sendAlarmMail(sendMailBatchReq));
        return ResponseEntity.ok().build();
    }
}
