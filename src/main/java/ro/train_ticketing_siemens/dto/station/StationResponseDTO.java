package ro.train_ticketing_siemens.dto.station;

import java.util.UUID;

public record StationResponseDTO(
        UUID id,
        String name,
        String city,
        String code
) {
}
