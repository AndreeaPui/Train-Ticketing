package ro.train_ticketing_siemens.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.route_station.RouteStationRequestDTO;
import ro.train_ticketing_siemens.dto.route_station.RouteStationResponseDTO;
import ro.train_ticketing_siemens.service.RouteStationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/route-stations")
@RequiredArgsConstructor
public class RouteStationController {

    private final RouteStationService routeStationService;

    @PostMapping
    public RouteStationResponseDTO create(@RequestBody @Valid RouteStationRequestDTO dto) {
        return routeStationService.create(dto);
    }

    @GetMapping
    public List<RouteStationResponseDTO> getAll() {
        return routeStationService.getAll();
    }

    @GetMapping("/{id}")
    public RouteStationResponseDTO getById(@PathVariable UUID id) {
        return routeStationService.getById(id);
    }

    @GetMapping("/route/{routeId}")
    public List<RouteStationResponseDTO> getByRouteId(@PathVariable UUID routeId) {
        return routeStationService.getByRouteId(routeId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        routeStationService.delete(id);
    }

    @PutMapping("/{id}")
    public RouteStationResponseDTO update(@PathVariable UUID id, @RequestBody @Valid RouteStationRequestDTO dto) {
        return routeStationService.update(id, dto);
    }
}