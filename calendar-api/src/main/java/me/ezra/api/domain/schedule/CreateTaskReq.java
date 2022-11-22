package me.ezra.api.domain.schedule;

import java.time.LocalDateTime;

public record CreateTaskReq(
        String title,
        String description,
        LocalDateTime taskAt
) {
}
