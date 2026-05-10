package ro.train_ticketing_siemens.dto.booking;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookingRequestDTO(

        @NotNull
        UUID scheduleId,

        @NotNull
        UUID originStationId,

        @NotNull
        UUID destinationStationId,

        @NotBlank
        String customerName,

        @Email
        @NotBlank
        String customerEmail,

        @NotNull
        @Min(1)
        Integer numTickets
) {
}
