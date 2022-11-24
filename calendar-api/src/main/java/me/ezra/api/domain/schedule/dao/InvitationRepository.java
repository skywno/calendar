package me.ezra.api.domain.schedule.dao;

import me.ezra.core.domain.schedule.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {


    List<Invitation> findAllByParticipant_Id(long id);
}
