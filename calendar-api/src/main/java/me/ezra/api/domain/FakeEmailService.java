package me.ezra.api.domain;

import me.ezra.core.domain.schedule.domain.Invitation;
import org.springframework.stereotype.Service;

@Service
public class FakeEmailService implements EmailService{
    @Override
    public void sendInvitation(Invitation e) {
        System.out.println("메일발송 - attendee: " + e.getParticipant().getEmail() + ", scheduleId: " + e.getEvent().getId());
    }
}
