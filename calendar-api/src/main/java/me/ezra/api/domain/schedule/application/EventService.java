package me.ezra.api.domain.schedule.application;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.EmailService;
import me.ezra.api.domain.schedule.dao.InvitationRepository;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.CreateEventReq;
import me.ezra.core.domain.schedule.ScheduleRepository;
import me.ezra.core.domain.schedule.domain.Invitation;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final InvitationRepository invitationRepository;

    @Transactional
    public void create(CreateEventReq createEventReq, AuthUser authUser) {

        final List<Invitation> invitationList = invitationRepository.findAll();
        if (invitationList.stream().anyMatch(
                invitation ->
                        createEventReq.participantIds().contains(invitation.getParticipant().getId())
                                && invitation.getRequestStatus() == RequestStatus.ACCEPTED
                                && invitation.getEvent().isOverlapped(createEventReq.startAt(),
                                createEventReq.endAt())
        )) {
            throw new RuntimeException("cannot send an invitation. period overlaps");
        }

        final Schedule eventSchedule = Schedule.event(
                createEventReq.title(),
                createEventReq.description(),
                createEventReq.startAt(),
                createEventReq.endAt(),
                userService.findByUserId(authUser.getId()));

        scheduleRepository.save(eventSchedule);
        createEventReq.participantIds()
                .stream()
                .map(userService::findByUserId)
                .forEach(user -> {
                    final Invitation invitation =
                            invitationRepository.save(new Invitation(eventSchedule, user));
                    emailService.sendInvitation(invitation);
                });

    }
}
