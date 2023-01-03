package me.ezra.api.domain.schedule.application;


import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.email.EmailService;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.CreateShareReq;
import me.ezra.core.domain.schedule.ShareRepository;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.domain.Share;
import me.ezra.core.domain.schedule.dto.RequestReplyType;
import me.ezra.core.domain.user.User;
import me.ezra.core.domain.user.UserService;
import me.ezra.core.global.exception.CalendarException;
import me.ezra.core.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ShareService {

    private final UserService userService;
    private final ShareRepository shareRepository;
    private final EmailService emailService;

    @Transactional
    public void createShare(AuthUser authUser, CreateShareReq req) {
        final User fromUser = userService.findByUserIdOrThrow(authUser.getId());
        final User toUser = userService.findByUserIdOrThrow(req.getToUserId());
        shareRepository.save(Share.builder()
                .fromUserId(fromUser.getId())
                .toUserId(toUser.getId())
                .direction(req.getDirection())
                .build());
        emailService.sendShareRequestMail(toUser.getEmail(), fromUser.getName(),
                req.getDirection());
    }

    @Transactional
    public void replyToShareRequest(Long shareId, AuthUser toAuthUser,
                                    RequestReplyType type) {
        shareRepository.findById(shareId)
                .filter(share -> share.getToUserId().equals(toAuthUser.getId()))
                .filter(share -> share.getRequestStatus() == RequestStatus.REQUESTED)
                .map(share -> share.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST));
    }

    /*
     *  공유 대상 조회
     *
     * 자신과 양방향 공유관계인 상대방 (자신이 to, From 둘다 가능)
     * 내가 공유관계의 수신자(toUser)인 경우 & 단방향
     */
    @Transactional
    public List<Long> findSharedUserIdsByUser(AuthUser authUser) {

        final Stream<Long> biDirectionShares = shareRepository.findAllByDirection(
                authUser.getId(),
                RequestStatus.ACCEPTED,
                Share.Direction.BI_DIRECTION
        ).stream().map(s -> s.getToUserId().equals(authUser.getId()) ? s.getFromUserId() : s.getToUserId());

        final Stream<Long> uniDirectionShares =
                shareRepository.findAllByToUserIdAndRequestStatusAndDirection(
                        authUser.getId(),
                        RequestStatus.ACCEPTED,
                        Share.Direction.UNI_DIRECTION)
                        .stream()
                        .map(Share::getFromUserId);

        return Stream.concat(biDirectionShares, uniDirectionShares).collect(Collectors.toList());
    }
}
