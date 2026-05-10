package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.route_station.RouteStationRequestDTO;
import ro.train_ticketing_siemens.dto.route_station.RouteStationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RouteStationService {

    RouteStationResponseDTO create(RouteStationRequestDTO dto);

    List<RouteStationResponseDTO> getAll();

    List<RouteStationResponseDTO> getByRouteId(UUID routeId);

    RouteStationResponseDTO getById(UUID id);

    void delete(UUID id);

    RouteStationResponseDTO update(UUID id, RouteStationRequestDTO dto);
}
