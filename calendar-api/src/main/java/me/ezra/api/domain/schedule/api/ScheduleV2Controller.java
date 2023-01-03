package me.ezra.api.domain.schedule.api;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.schedule.application.ScheduleQueryService;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.SharedScheduleDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequestMapping("/api/v2/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleV2Controller {
    private final ScheduleQueryService scheduleQueryService;

    @GetMapping(value = "/day")
    public List<SharedScheduleDto> getSharedScheduleByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            AuthUser authUser
    ) {
        return scheduleQueryService.getSharedScheduleByDay(
                date == null ? LocalDate.now() : date, authUser);
    }

    @GetMapping("/week")
    public List<SharedScheduleDto> getSharedScheduleByWeek(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek,
            AuthUser authUser
    ) {
        return scheduleQueryService.getSharedScheduleByWeek(startOfWeek == null ?
                LocalDate.now() : startOfWeek, authUser);
    }

    @GetMapping("/month")
    public List<SharedScheduleDto> getSharedScheduleByMonth(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth,
            AuthUser authUser
    ) {
        return scheduleQueryService.getSharedScheduleByMonth(yearMonth == null ?
                YearMonth.now() : yearMonth, authUser);
    }

}
