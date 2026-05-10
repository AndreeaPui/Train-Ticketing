package ro.train_ticketing_siemens.dto.route_station;

import java.util.UUID;

public record RouteStationResponseDTO(
        UUID id,
        UUID routeId,
        String routeName,
        UUID stationId,
        String stationName,
        String stationCode,
        Integer stopOrder,
        Integer arrivalOffsetMinutes,
        Integer departureOffsetMinutes
) {
}
