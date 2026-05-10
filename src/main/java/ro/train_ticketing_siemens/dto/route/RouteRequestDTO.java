package ro.train_ticketing_siemens.dto.route;

import jakarta.validation.constraints.NotBlank;

public record RouteRequestDTO(
        @NotBlank
        String name
) {
}