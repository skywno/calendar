package me.ezra.api.domain;

import me.ezra.core.domain.schedule.domain.Invitation;

public interface EmailService {
    void sendInvitation(Invitation e);
}
