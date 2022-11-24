package me.ezra.api.global.util;

import me.ezra.api.domain.schedule.dto.EventDto;
import me.ezra.api.domain.schedule.dto.NotificationDto;
import me.ezra.api.domain.schedule.dto.ScheduleDto;
import me.ezra.api.domain.schedule.dto.TaskDto;
import me.ezra.core.domain.schedule.domain.Invitation;
import me.ezra.core.domain.schedule.domain.Schedule;

public class DtoConverter {

    public static ScheduleDto toScheduleDto(Schedule schedule) {
        return switch (schedule.getScheduleType()) {
            case EVENT -> EventDto.builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .description(schedule.getDescription())
                    .writerId(schedule.getWriter().getId())
                    .startAt(schedule.getStartAt())
                    .endAt(schedule.getEndAt())
                    .build();
            case TASK -> TaskDto.builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .description(schedule.getDescription())
                    .writerId(schedule.getWriter().getId())
                    .taskAt(schedule.getStartAt())
                    .build();
            case NOTIFICATION -> NotificationDto.builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .writerId(schedule.getWriter().getId())
                    .notifyAt(schedule.getStartAt())
                    .build();
        };
    }

    public static ScheduleDto toScheduleDto(Invitation invitation) {
        return toScheduleDto(invitation.getSchedule());

    }
}
