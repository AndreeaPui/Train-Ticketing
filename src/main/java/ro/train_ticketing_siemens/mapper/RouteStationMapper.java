package ro.train_ticketing_siemens.mapper;

import org.springframework.stereotype.Component;
import ro.train_ticketing_siemens.domain.Route;
import ro.train_ticketing_siemens.domain.RouteStation;
import ro.train_ticketing_siemens.domain.Station;
import ro.train_ticketing_siemens.dto.route_station.RouteStationRequestDTO;
import ro.train_ticketing_siemens.dto.route_station.RouteStationResponseDTO;


@Component
public class RouteStationMapper {

    public RouteStation toEntity(RouteStationRequestDTO dto, Route route, Station station) {
        return RouteStation.builder()
                .route(route)
                .station(station)
                .stopOrder(dto.stopOrder())
                .arrivalOffsetMinutes(dto.arrivalOffsetMinutes())
                .departureOffsetMinutes(dto.departureOffsetMinutes())
                .build();
    }

    public RouteStationResponseDTO toDto(RouteStation routeStation) {
        return new RouteStationResponseDTO(
                routeStation.getId(),
                routeStation.getRoute().getId(),
                routeStation.getRoute().getName(),
                routeStation.getStation().getId(),
                routeStation.getStation().getName(),
                routeStation.getStation().getCode(),
                routeStation.getStopOrder(),
                routeStation.getArrivalOffsetMinutes(),
                routeStation.getDepartureOffsetMinutes()
        );
    }
}
