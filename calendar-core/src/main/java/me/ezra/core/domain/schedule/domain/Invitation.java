package me.ezra.core.domain.schedule.domain;

import lombok.*;
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
@Table(name = "invitation")
public class Invitation extends BaseEntity {

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "participant_id")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User participant;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;


    @Builder
    public Invitation(Schedule eventSchedule, User participant) {
        assert eventSchedule.getScheduleType() == ScheduleType.EVENT;
        this.schedule = eventSchedule;
        this.requestStatus = RequestStatus.REQUESTED;
        this.participant = participant;
    }

    public Event getEvent() {
        return schedule.toEvent();
    }

    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }

    public Invitation reply(RequestReplyType type) {
        switch (type) {
            case ACCEPTED -> this.requestStatus = RequestStatus.ACCEPTED;
            case REJECTED -> this.requestStatus = RequestStatus.REJECTED;
        }

        return this;
    }
}
