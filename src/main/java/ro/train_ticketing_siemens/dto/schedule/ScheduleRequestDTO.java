package ro.train_ticketing_siemens.dto.schedule;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScheduleRequestDTO(

        @NotNull
        UUID trainId,

        @NotNull
        LocalDateTime departureTime,

        @NotNull
        Integer delayMinutes

) {
}
