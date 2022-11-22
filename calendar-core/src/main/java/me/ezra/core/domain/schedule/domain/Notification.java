package me.ezra.core.domain.schedule.domain;

import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;

public class Notification {
    private Schedule schedule;

    public Notification(Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
