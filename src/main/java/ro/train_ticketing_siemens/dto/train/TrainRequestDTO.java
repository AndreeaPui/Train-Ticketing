package ro.train_ticketing_siemens.dto.train;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TrainRequestDTO(
        @NotBlank
        String trainNumber,

        @NotBlank
        String name,

        @NotNull
        Integer capacity,

        @NotNull
        UUID routeId
) {
}