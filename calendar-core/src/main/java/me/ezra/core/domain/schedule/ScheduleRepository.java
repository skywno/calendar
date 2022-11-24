package me.ezra.core.domain.schedule;


import me.ezra.core.domain.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByWriter_Id(long id);
}
