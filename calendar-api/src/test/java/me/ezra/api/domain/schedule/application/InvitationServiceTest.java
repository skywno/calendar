package me.ezra.api.domain.schedule.application;

import me.ezra.core.domain.schedule.InvitationRepository;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.core.domain.schedule.domain.Invitation;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.schedule.domain.ScheduleType;
import me.ezra.core.domain.schedule.dto.RequestReplyType;
import me.ezra.core.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class InvitationServiceTest {

    @InjectMocks
    InvitationService sut;

    @Mock
    InvitationRepository invitationRepository;

    @Test
    void givenAccepted_whenUpdatingInvitationStatus_thenResultsInAcceptedStatus() {
        // Given
        Long invitationId = 1L;

        User writer = createUser(1L);
        User participant = createUser(2L);
        Invitation invitation = createInvitation(invitationId, writer, participant);

        given(invitationRepository.findById(invitationId))
                .willReturn(Optional.ofNullable(invitation));

        AuthUser authUser = AuthUser.of(participant.getId());
        RequestReplyType type = RequestReplyType.ACCEPTED;

        // When
        RequestStatus requestStatus =
                sut.updateInvitationStatus(authUser, invitationId, type);
        // Then
        then(requestStatus).isEqualTo(RequestStatus.ACCEPTED);
    }

    @Test
    @DisplayName("유저가 초대를 거절하였을 때 DB 수정 후 거절 리턴")
    void givenRejected_whenUpdatingInvitationStatus_thenResultsInRejectedStatus() {
        // Given
        Long invitationId = 1L;

        User writer = createUser(1L);
        User participant = createUser(2L);

        Invitation invitation = createInvitation(invitationId, writer, participant);

        given(invitationRepository.findById(invitationId))
                .willReturn(Optional.ofNullable(invitation));

        AuthUser authUser = AuthUser.of(participant.getId());
        RequestReplyType type = RequestReplyType.REJECTED;

        // When
        RequestStatus requestStatus =
                sut.updateInvitationStatus(authUser, invitationId, type);
        // Then
        then(requestStatus).isEqualTo(RequestStatus.REJECTED);
    }

    public User createUser(Long userid) {
        User user =  User.builder()
                .email("email")
                .password("password")
                .birthday(LocalDate.now())
                .name("name")
                .build();
        ReflectionTestUtils.setField(user, "id", userid);
        return user;
    }

    public Invitation createInvitation(Long invitationId, User writer, User participant) {
        Invitation invitation = new Invitation(new Schedule(
                "schedule title",
                "schedule description",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                writer,
                ScheduleType.EVENT
        ), participant);

        ReflectionTestUtils.setField(invitation, "id", invitationId);
        return invitation;
    }
}