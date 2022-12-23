package me.ezra.api.domain.schedule.application;


import lombok.RequiredArgsConstructor;
import me.ezra.core.domain.schedule.InvitationRepository;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.core.domain.schedule.domain.Invitation;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.dto.RequestReplyType;
import me.ezra.core.global.exception.CalendarException;
import me.ezra.core.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;

    @Transactional
    public RequestStatus updateInvitationStatus(AuthUser user, Long invitationId, RequestReplyType type) {
       Invitation updatedInvitation = invitationRepository.findById(invitationId)
                .filter(invitation -> invitation.getParticipant().getId().equals(user.getId()))
                .filter(invitation -> invitation.getRequestStatus() == RequestStatus.REQUESTED)
                .map(invitation -> invitation.reply(type))
                .orElseThrow( () -> new CalendarException(ErrorCode.BAD_REQUEST));

       invitationRepository.save(updatedInvitation);
       return updatedInvitation.getRequestStatus();

    }
}
