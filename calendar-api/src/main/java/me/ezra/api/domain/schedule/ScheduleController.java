package me.ezra.api.domain.schedule;

import lombok.RequiredArgsConstructor;
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

    @PostMapping("/task")
    public ResponseEntity<Void> createTask(
            @Valid @RequestBody CreateTaskReq createTaskReq,
            AuthUser authUser
    ) {
        taskService.create(createTaskReq, authUser);
        return ResponseEntity.ok().build();
    }
}
