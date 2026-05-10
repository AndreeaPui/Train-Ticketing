package ro.train_ticketing_siemens.dto.route;

import java.util.UUID;

public record RouteResponseDTO(
        UUID id,
        String name
) {
}
