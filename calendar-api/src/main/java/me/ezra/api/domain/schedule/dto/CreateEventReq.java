package me.ezra.api.domain.schedule.dto;


import me.ezra.core.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record CreateEventReq(
        @NotBlank String title,
        String description,
        @NotNull LocalDateTime startAt,
        @NotNull LocalDateTime endAt,
        @NotNull
        List<Long> participantIds

) {}
