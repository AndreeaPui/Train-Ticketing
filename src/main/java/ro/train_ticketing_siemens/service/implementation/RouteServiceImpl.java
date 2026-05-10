package ro.train_ticketing_siemens.service.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.train_ticketing_siemens.domain.Route;
import ro.train_ticketing_siemens.dto.route.RouteRequestDTO;
import ro.train_ticketing_siemens.dto.route.RouteResponseDTO;
import ro.train_ticketing_siemens.errorhandling.enums.ErrorCode;
import ro.train_ticketing_siemens.errorhandling.exceptions.ResourceNotFoundException;
import ro.train_ticketing_siemens.mapper.RouteMapper;
import ro.train_ticketing_siemens.repository.RouteRepository;
import ro.train_ticketing_siemens.service.RouteService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Override
    public RouteResponseDTO create(RouteRequestDTO dto) {
        Route route = routeMapper.toEntity(dto);
        Route savedRoute = routeRepository.save(route);
        return routeMapper.toDto(savedRoute);
    }

    @Override
    public List<RouteResponseDTO> getAll() {
        return routeRepository.findByDeletedFalse()
                .stream()
                .map(routeMapper::toDto)
                .toList();
    }

    @Override
    public RouteResponseDTO getById(UUID id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        return routeMapper.toDto(route);
    }

    @Override
    public void delete(UUID id) {

        Route route = routeRepository.findById(id)
                .filter(r -> !r.getDeleted())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        route.setDeleted(true);

        routeRepository.save(route);
    }

    @Override
    public RouteResponseDTO update(UUID id, RouteRequestDTO dto) {
        Route route = routeRepository.findById(id)
                .filter(r -> !r.getDeleted())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode._1001_ROUTE_NOT_FOUND,
                        id
                ));
        route.setName(dto.name());

        return routeMapper.toDto(routeRepository.save(route));
    }
}
