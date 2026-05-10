package ro.train_ticketing_siemens.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.train_ticketing_siemens.domain.Route;
import ro.train_ticketing_siemens.domain.RouteStation;
import ro.train_ticketing_siemens.domain.Station;
import ro.train_ticketing_siemens.dto.route_station.RouteStationRequestDTO;
import ro.train_ticketing_siemens.dto.route_station.RouteStationResponseDTO;
import ro.train_ticketing_siemens.mapper.RouteStationMapper;
import ro.train_ticketing_siemens.repository.RouteRepository;
import ro.train_ticketing_siemens.repository.RouteStationRepository;
import ro.train_ticketing_siemens.repository.StationRepository;
import ro.train_ticketing_siemens.service.RouteStationService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteStationServiceImpl implements RouteStationService {

    private final RouteStationRepository routeStationRepository;
    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;
    private final RouteStationMapper routeStationMapper;

    @Override
    public RouteStationResponseDTO create(RouteStationRequestDTO dto) {
        Route route = routeRepository.findById(dto.routeId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Station station = stationRepository.findById(dto.stationId())
                .orElseThrow(() -> new RuntimeException("Station not found"));

        RouteStation routeStation = routeStationMapper.toEntity(dto, route, station);

        RouteStation savedRouteStation = routeStationRepository.save(routeStation);

        return routeStationMapper.toDto(savedRouteStation);
    }

    @Override
    public List<RouteStationResponseDTO> getAll() {
        return routeStationRepository.findByDeletedFalse()
                .stream()
                .map(routeStationMapper::toDto)
                .toList();
    }

    @Override
    public List<RouteStationResponseDTO> getByRouteId(UUID routeId) {
        return routeStationRepository.findByRouteIdAndDeletedFalseOrderByStopOrderAsc(routeId)
                .stream()
                .map(routeStationMapper::toDto)
                .toList();
    }

    @Override
    public RouteStationResponseDTO getById(UUID id) {
        RouteStation routeStation = routeStationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route station not found"));

        return routeStationMapper.toDto(routeStation);
    }

    @Override
    public void delete(UUID id) {

        RouteStation routeStation = routeStationRepository.findById(id)
                .filter(rs -> !rs.getDeleted())
                .orElseThrow(() -> new RuntimeException("Route station not found"));

        routeStation.setDeleted(true);

        routeStationRepository.save(routeStation);
    }

    @Override
    public RouteStationResponseDTO update(UUID id, RouteStationRequestDTO dto) {
        RouteStation routeStation = routeStationRepository.findById(id)
                .filter(rs -> !rs.getDeleted())
                .orElseThrow(() -> new RuntimeException("Route station not found"));

        Route route = routeRepository.findById(dto.routeId())
                .filter(r -> !r.getDeleted())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Station station = stationRepository.findById(dto.stationId())
                .filter(s -> !s.getDeleted())
                .orElseThrow(() -> new RuntimeException("Station not found"));

        routeStation.setRoute(route);
        routeStation.setStation(station);
        routeStation.setStopOrder(dto.stopOrder());
        routeStation.setArrivalOffsetMinutes(dto.arrivalOffsetMinutes());
        routeStation.setDepartureOffsetMinutes(dto.departureOffsetMinutes());

        return routeStationMapper.toDto(routeStationRepository.save(routeStation));
    }
}
