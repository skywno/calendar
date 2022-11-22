package me.ezra.core.domain.schedule.domain;

import lombok.NoArgsConstructor;
import me.ezra.core.domain.model.BaseEntity;
import me.ezra.core.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "invitation")
public class Invitation extends BaseEntity {

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "participant_id")
    @ManyToOne
    private User participant;

    private RequestStatus requestStatus;
}
