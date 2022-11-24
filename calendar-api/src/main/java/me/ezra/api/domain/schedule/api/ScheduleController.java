package me.ezra.api.domain.schedule.api;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.schedule.application.EventService;
import me.ezra.api.domain.schedule.application.NotificationService;
import me.ezra.api.domain.schedule.application.ScheduleQueryService;
import me.ezra.api.domain.schedule.application.TaskService;
import me.ezra.api.domain.schedule.dto.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleQueryService scheduleQueryService;
    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;

    @PostMapping("/task")
    public ResponseEntity<Void> createTask(
            @Valid @RequestBody CreateTaskReq createTaskReq,
            AuthUser authUser
    ) {
        taskService.create(createTaskReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/event")
    public ResponseEntity<Void> createEvent(
            @Valid @RequestBody CreateEventReq createEventReq,
            AuthUser authUser
    ) {
        eventService.create(createEventReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notification")
    public ResponseEntity<Void> createNotification(
            @Valid @RequestBody CreateNotificationReq createNotificationReq,
            AuthUser authUser
    ) {
        notificationService.create(createNotificationReq, authUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/day")
    public List<ScheduleDto> getScheduleByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            AuthUser authUser
    ) {
        return scheduleQueryService.getScheduleByDay(date == null ? LocalDate.now() : date, authUser);
    }

}
