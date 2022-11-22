package me.ezra.api.domain.schedule.dao;

import me.ezra.core.domain.schedule.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
