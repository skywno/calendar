package me.ezra.api.domain.schedule;

import lombok.RequiredArgsConstructor;
import me.ezra.core.domain.schedule.ScheduleRepository;
import me.ezra.core.domain.schedule.domain.Schedule;
import me.ezra.core.domain.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    public void create(CreateTaskReq req, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(
                req.title(),
                req.description(),
                req.taskAt(),
                userService.findByUserId(authUser.getId()));

        scheduleRepository.save(taskSchedule);


    }

}
