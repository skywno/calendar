package me.ezra.core.domain.schedule.domain;

import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;
import me.ezra.core.global.util.Period;

import java.time.LocalDateTime;

public class Event {
    private final Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return this.schedule.getId();
    }

    public String getTitle() {
        return schedule.getTitle();
    }

    public Period getPeriod() {
        return Period.of(schedule.getStartAt(), schedule.getEndAt());
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return getPeriod().isOverlapped(startAt, endAt);
    }

}
