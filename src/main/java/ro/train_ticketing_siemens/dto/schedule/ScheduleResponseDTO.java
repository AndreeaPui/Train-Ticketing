package ro.train_ticketing_siemens.dto.schedule;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleResponseDTO(
        UUID id,
        UUID trainId,
        String trainNumber,
        String trainName,
        UUID routeId,
        String routeName,
        LocalDateTime departureTime,
        Integer delayMinutes
) {
}
