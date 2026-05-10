package ro.train_ticketing_siemens.dto.route_station;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RouteStationRequestDTO(

        @NotNull
        UUID routeId,

        @NotNull
        UUID stationId,

        @NotNull
        Integer stopOrder,

        @NotNull
        Integer arrivalOffsetMinutes,

        @NotNull
        Integer departureOffsetMinutes

) {
}