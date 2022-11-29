package me.ezra.core.domain.schedule.domain;

import lombok.*;
import me.ezra.core.domain.model.BaseEntity;
import me.ezra.core.domain.user.User;
import me.ezra.core.global.util.Period;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder(access=AccessLevel.PRIVATE)
public class Schedule extends BaseEntity {

    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private User writer;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    public static Schedule event(
            String title,
            String description,
            LocalDateTime startAt,
            LocalDateTime endAt,
            User writer
    ) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    public static Schedule task(
            String title,
            String description,
            LocalDateTime taskAt,
            User writer
    ) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(taskAt)
                .writer(writer)
                .scheduleType(ScheduleType.TASK)
                .build();
    }
    public static Schedule notification(
            String title,
            LocalDateTime notifyAt,
            User writer
    ) {
        return Schedule.builder()
                .title(title)
                .startAt(notifyAt)
                .writer(writer)
                .scheduleType(ScheduleType.NOTIFICATION)
                .build();
    }

    public Task toTask() {
        return new Task(this);
    }

    public Event toEvent() {
        return new Event(this);
    }

    public Notification toNotification() {
        return new Notification(this);
    }

    public boolean isOverlapped(LocalDate date) {
        Period period = Period.of(startAt, endAt);
        return period.isOverlapped(date);
   }

   public boolean isOverlapped(Period otherPeriod) {
        Period thisPeriod = Period.of(startAt, endAt);
        return thisPeriod.isOverlapped(otherPeriod);
   }
}
