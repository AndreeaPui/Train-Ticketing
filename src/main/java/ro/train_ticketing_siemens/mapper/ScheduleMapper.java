package ro.train_ticketing_siemens.mapper;

import org.springframework.stereotype.Component;
import ro.train_ticketing_siemens.domain.Schedule;
import ro.train_ticketing_siemens.domain.Train;
import ro.train_ticketing_siemens.dto.schedule.ScheduleRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleResponseDTO;

@Component
public class ScheduleMapper {

    public Schedule toEntity(ScheduleRequestDTO dto, Train train) {
        return Schedule.builder()
                .train(train)
                .departureTime(dto.departureTime())
                .delayMinutes(dto.delayMinutes())
                .build();
    }

    public ScheduleResponseDTO toDto(Schedule schedule) {
        return new ScheduleResponseDTO(
                schedule.getId(),
                schedule.getTrain().getId(),
                schedule.getTrain().getTrainNumber(),
                schedule.getTrain().getName(),
                schedule.getTrain().getRoute().getId(),
                schedule.getTrain().getRoute().getName(),
                schedule.getDepartureTime(),
                schedule.getDelayMinutes()
        );
    }
}
