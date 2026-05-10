package ro.train_ticketing_siemens.dto.connection;

import java.util.List;

public record ConnectionResponseDTO(
        boolean direct,
        List<ConnectionLegResponseDTO> legs
) {
}