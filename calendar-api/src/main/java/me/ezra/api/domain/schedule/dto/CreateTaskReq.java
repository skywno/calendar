package me.ezra.api.domain.schedule.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateTaskReq(

        @NotBlank
        String title,
        String description,
        @NotNull
        LocalDateTime taskAt
) {
}
