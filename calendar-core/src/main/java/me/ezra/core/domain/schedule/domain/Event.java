package me.ezra.core.domain.schedule.domain;

import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;

import java.time.LocalDateTime;
import java.time.Period;

public class Event {
    private final Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return this.schedule.getId();
    }

    public LocalDateTime getStartAt() {
        return schedule.getStartAt();
    }

    public LocalDateTime getEndAt() {
        return schedule.getEndAt();
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }

    public String getTitle() {
        return schedule.getTitle();
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && this.getEndAt().isAfter(startAt);
    }

}
