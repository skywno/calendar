package me.ezra.api.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.ezra.core.domain.schedule.domain.ScheduleType;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Builder
public class NotificationDto implements ScheduleDto{
    private final Long scheduleId;
    private final String title;
    private final Long writerId;
    private final LocalDateTime notifyAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NotificationDto)) return false;
        final NotificationDto other = (NotificationDto) o;
        if (!other.canEqual(this)) return false;

        if (!Objects.equals(this.getScheduleId(), other.getScheduleId()))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NotificationDto;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $scheduleId = this.getScheduleId();
        result = result * PRIME + ($scheduleId == null ? 43 : $scheduleId.hashCode());
        return result;
    }
}
