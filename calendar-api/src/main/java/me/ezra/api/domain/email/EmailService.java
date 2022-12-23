package me.ezra.api.domain.email;

import me.ezra.core.domain.email.SendMailBatchReq;

public interface EmailService {
    public void sendInvitation(InvitationDetailsForEmail invitationDetailsForEmail);

    public void sendAlarmMail(SendMailBatchReq sendMailBatchReq);
}
