package me.ezra.api.domain.schedule.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.ezra.core.domain.schedule.dto.RequestReplyType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ReplyReq {

    @NotNull
    private RequestReplyType type;
}
