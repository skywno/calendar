package me.ezra.api.domain.schedule.application;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.CreateNotificationReq;
import me.ezra.core.domain.schedule.ScheduleRepository;
import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.User;
import me.ezra.core.domain.user.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class NotificationService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(CreateNotificationReq req, AuthUser authUser) {
        final User user = userService.findByUserIdOrThrow(authUser.getId());

        List<LocalDateTime> notifyAtList = req.getNotificationDates();

        notifyAtList.forEach(notifyAt -> {
            Schedule notificationSchedule = Schedule.notification(
                    req.title(), notifyAt, user
            );
            scheduleRepository.save(notificationSchedule);
        });


    }
}
