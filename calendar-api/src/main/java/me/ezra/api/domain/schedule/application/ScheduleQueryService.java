package me.ezra.api.domain.schedule.application;

import lombok.RequiredArgsConstructor;
import me.ezra.api.domain.schedule.dao.InvitationRepository;
import me.ezra.api.domain.schedule.dto.AuthUser;
import me.ezra.api.domain.schedule.dto.ScheduleDto;
import me.ezra.api.global.util.DtoConverter;
import me.ezra.core.domain.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final InvitationRepository invitationRepository;

    public List<ScheduleDto> getScheduleByDay(LocalDate date, AuthUser user) {
        return Stream.concat(
                        scheduleRepository.findAllByWriter_Id(user.getId())
                                .stream()
                                .filter(schedule -> schedule.isOverlapped(date))
                                .map(DtoConverter::toScheduleDto),
                        invitationRepository.findAllByParticipant_Id(user.getId())
                                .stream()
                                .filter(invitation -> invitation.isOverlapped(date))
                                .map(DtoConverter::toScheduleDto)
                ).distinct()
                .collect(Collectors.toList());

    }
}
