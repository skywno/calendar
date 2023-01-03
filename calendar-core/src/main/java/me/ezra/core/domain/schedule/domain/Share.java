package me.ezra.core.domain.schedule.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ezra.core.domain.model.BaseEntity;
import me.ezra.core.domain.schedule.dto.RequestReplyType;
import me.ezra.core.domain.user.User;
import me.ezra.core.global.util.Period;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "share")
public class Share extends BaseEntity {

    private Long  fromUserId;
    private Long  toUserId;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Builder
    public Share(Long fromUserId, Long toUserId, Direction direction) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.requestStatus = RequestStatus.REQUESTED;
        this.direction = direction;
    }

    public Share reply(RequestReplyType type) {
        switch (type) {
            case ACCEPTED -> this.requestStatus = RequestStatus.ACCEPTED;
            case REJECTED -> this.requestStatus = RequestStatus.REJECTED;
        }

        return this;
    }

    public enum Direction {
        BI_DIRECTION, UNI_DIRECTION
    }
}
