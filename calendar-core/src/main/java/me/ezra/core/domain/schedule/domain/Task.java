package me.ezra.core.domain.schedule.domain;

import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;

public class Task {
    private Schedule schedule;

    public Task (Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
