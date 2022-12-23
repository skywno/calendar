package me.ezra.api.domain.email;


import lombok.RequiredArgsConstructor;
import me.ezra.core.domain.email.MailBatchReqRepository;
import me.ezra.core.domain.email.SendMailBatchReq;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
@RequiredArgsConstructor
public class FakeEmailServiceImpl implements EmailService {

    private final MailBatchReqRepository mailBatchReqRepository;

    @Override
    public void sendInvitation(InvitationDetailsForEmail stuff) {
        System.out.println(stuff.getProps());
    }

    @Override
    public void sendAlarmMail(SendMailBatchReq sendMailBatchReq) {
        mailBatchReqRepository.save(sendMailBatchReq);
    }

}
