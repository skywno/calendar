package me.ezra.api.domain.schedule.dto;

import lombok.Data;
import me.ezra.core.domain.schedule.domain.Share;

@Data
public class CreateShareReq {
    private final Long toUserId;
    private final Share.Direction direction;
}
