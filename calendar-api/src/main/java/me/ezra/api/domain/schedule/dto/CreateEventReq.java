package me.ezra.api.domain.schedule.dto;


import me.ezra.core.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventReq(
        String title,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        List<Long> participantIds
) {}
