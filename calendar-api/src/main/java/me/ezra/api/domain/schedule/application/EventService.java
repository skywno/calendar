package me.ezra.api.domain.schedule.application;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.email.EmailService;
import me.ezra.api.domain.email.InvitationDetailsForEmail;
import me.ezra.core.domain.schedule.InvitationRepository;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.CreateEventReq;
import me.ezra.core.domain.schedule.ScheduleRepository;
import me.ezra.core.domain.schedule.domain.Invitation;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;
import me.ezra.core.domain.user.UserService;
import me.ezra.core.global.exception.CalendarException;
import me.ezra.core.global.exception.ErrorCode;
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
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }

        final Schedule eventSchedule = Schedule.event(
                createEventReq.title(),
                createEventReq.description(),
                createEventReq.startAt(),
                createEventReq.endAt(),
                userService.findByUserIdOrThrow(authUser.getId()));
        scheduleRepository.save(eventSchedule);

        final List<User> participants =
                createEventReq.participantIds()
                        .stream()
                        .map(userService::findByUserIdOrThrow)
                        .toList();

        participants.forEach(participant -> {
                    Invitation invitation = new Invitation(eventSchedule, participant);
                    invitationRepository.save(invitation);
                    emailService.sendInvitation(new InvitationDetailsForEmail(
                            invitation.getId(),
                            invitation.getParticipant().getEmail(),
                            participants.stream().map(User::getEmail).toList(),
                            invitation.getEvent().getTitle(),
                            invitation.getEvent().getPeriod()));
                });

    }
}
