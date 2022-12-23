package me.ezra.api.domain.schedule.api;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.schedule.application.*;
import me.ezra.api.domain.schedule.dto.*;
import me.ezra.core.domain.schedule.domain.RequestStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleQueryService scheduleQueryService;
    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;
    private final InvitationService invitationService;
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

    @GetMapping(value = "/day")
    public List<ScheduleDto> getScheduleByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            AuthUser authUser
    ) {
        return scheduleQueryService.getScheduleByDay(date == null ? LocalDate.now() :
                date, authUser);
    }

    @GetMapping("/week")
    public List<ScheduleDto> getScheduleByWeek(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek,
            AuthUser authUser
    ) {
        return scheduleQueryService.getScheduleByWeek(startOfWeek == null ?
                LocalDate.now() : startOfWeek, authUser);
    }

    @GetMapping("/month")
    public List<ScheduleDto> getScheduleByMonth(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth,
            AuthUser authUser
    ) {
        return scheduleQueryService.getScheduleByMonth(yearMonth == null ?
                YearMonth.now() : yearMonth, authUser);
    }

    @PutMapping("/events/invitations/{invitationId}")
    public RequestStatus updateInvitationStatus(
            AuthUser authUser,
            @PathVariable Long invitationId,
            @RequestBody @Valid InvitationResponse invitationResponse
    ) {
        return invitationService.updateInvitationStatus(authUser, invitationId, invitationResponse.getType());
    }
}
