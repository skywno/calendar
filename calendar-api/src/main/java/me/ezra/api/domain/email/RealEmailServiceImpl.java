package me.ezra.api.domain.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ezra.core.domain.email.SendMailBatchReq;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static me.ezra.api.domain.email.InvitationDetailsForEmail.MAIL_TIME_FORMAT;

@Service
@RequiredArgsConstructor
@Profile("!test")
@Slf4j
public class RealEmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendInvitation(InvitationDetailsForEmail invitationDetails) {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(invitationDetails.getToEmail());
            helper.setSubject(invitationDetails.getSubject());
            helper.setText(
                    templateEngine.process("invitation-email",
                            new Context(Locale.KOREA, invitationDetails.getProps())),
                    true);
        };
        emailSender.send(preparator);
        log.info("Email has been sent to {}", invitationDetails.getToEmail());
    }

    @Override
    public void sendAlarmMail(SendMailBatchReq sendMailBatchReq) {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(sendMailBatchReq.getUserEmail());
            helper.setSubject(sendMailBatchReq.getTitle());
            helper.setText(String.format(
                            "[%s] %s",
                            sendMailBatchReq.getStartAt().format(DateTimeFormatter.ofPattern(MAIL_TIME_FORMAT)),
                            sendMailBatchReq.getTitle()));
        };
        emailSender.send(preparator);
    }
}
