package me.ezra.core.domain.schedule.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ezra.core.domain.model.BaseEntity;
import me.ezra.core.domain.user.User;
import me.ezra.core.global.util.Period;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private User participant;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Invitation(Schedule eventSchedule, User participant) {
        assert eventSchedule.getScheduleType() == ScheduleType.EVENT;
        this.schedule = eventSchedule;
        this.requestStatus = RequestStatus.REQUESTED;
        this.participant = participant;
    }

    public Event getEvent() {
        return schedule.toEvent();
    }

    public boolean isOverlapped(LocalDate date) {
        return this.schedule.isOverlapped(date);
    }

    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }
}
