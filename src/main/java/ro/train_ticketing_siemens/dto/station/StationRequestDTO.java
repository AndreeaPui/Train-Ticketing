package ro.train_ticketing_siemens.dto.station;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StationRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String city,

        @NotBlank
        String code

) {
}