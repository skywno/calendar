package me.ezra.api.domain.schedule.api;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.schedule.application.EventService;
import me.ezra.api.domain.schedule.application.TaskService;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.CreateEventReq;
import me.ezra.api.domain.schedule.dto.CreateTaskReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final TaskService taskService;
    private final EventService eventService;

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

}
