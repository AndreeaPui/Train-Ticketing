package ro.train_ticketing_siemens.dto.train;


import java.util.UUID;

public record TrainResponseDTO(
        UUID id,
        String trainNumber,
        String name,
        Integer capacity,
        UUID routeId,
        String routeName
) {
}
