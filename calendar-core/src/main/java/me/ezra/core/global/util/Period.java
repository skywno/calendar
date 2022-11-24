package me.ezra.core.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Period {

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    private Period(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static Period of(LocalDateTime startAt, LocalDateTime endAt) {
        return new Period(startAt, endAt == null ? startAt : endAt);
    }

    public static Period of(LocalDate startAt, LocalDate endAt) {
        return new Period(
                startAt.atStartOfDay(),
                endAt == null ? startAt.atStartOfDay() : endAt.atStartOfDay()
        );
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.startAt.isBefore(endAt) && startAt.isBefore(this.endAt);
    }

    public boolean isOverlapped(LocalDate date) {
        return isOverlapped(date.atStartOfDay(), LocalDateTime.of(date, LocalTime.MAX));
    }
}
