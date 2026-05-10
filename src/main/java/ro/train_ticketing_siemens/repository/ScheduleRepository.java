package ro.train_ticketing_siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.train_ticketing_siemens.domain.Schedule;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByDeletedFalse();

    List<Schedule> findByTrainIdAndDeletedFalse(UUID trainId);
}
