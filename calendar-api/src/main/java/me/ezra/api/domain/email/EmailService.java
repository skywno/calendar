package me.ezra.api.domain.email;

import me.ezra.core.domain.email.SendMailBatchReq;
import me.ezra.core.domain.schedule.domain.Share;

public interface EmailService {
    public void sendInvitation(InvitationDetailsForEmail invitationDetailsForEmail);

    public void sendAlarmMail(SendMailBatchReq sendMailBatchReq);

    void sendShareRequestMail(String email, String name, Share.Direction direction);
}
