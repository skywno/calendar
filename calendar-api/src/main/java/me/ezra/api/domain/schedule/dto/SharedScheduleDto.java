package me.ezra.api.domain.schedule.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SharedScheduleDto {
    private final Long userId;
    private final String name;
    private final boolean me;
    private final List<ScheduleDto> schedules;
}
