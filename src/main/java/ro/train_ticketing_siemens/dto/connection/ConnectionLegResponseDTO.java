package ro.train_ticketing_siemens.dto.connection;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConnectionLegResponseDTO(
        UUID scheduleId,
        String trainNumber,
        String trainName,
        String fromStation,
        String toStation,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime
) {
}
