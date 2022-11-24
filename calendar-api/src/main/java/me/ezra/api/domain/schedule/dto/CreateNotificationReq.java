package me.ezra.api.domain.schedule.dto;

import me.ezra.core.global.util.TimeUnit;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public record CreateNotificationReq(
        String title,
        LocalDateTime notifyAt,
        RepeatInfo repeatInfo
) {

    public List<LocalDateTime> getNotificationDates() {
        if (repeatInfo == null) {
            return Collections.singletonList(notifyAt);
        }
        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                    long increment = (long) repeatInfo.interval.intervalValue * i;
                    return switch (repeatInfo.interval.timeUnit) {
                        case DAY   -> notifyAt.plusDays(increment);
                        case WEEK  -> notifyAt.plusWeeks(increment);
                        case MONTH -> notifyAt.plusMonths(increment);
                        case YEAR  -> notifyAt.plusYears(increment);
                    };
                })
                .collect(Collectors.toList());
    }

    public record RepeatInfo(Interval interval, Integer times) {
    }

    public record Interval(Integer intervalValue, TimeUnit timeUnit) {
    }
}
