package ro.train_ticketing_siemens.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.route.RouteRequestDTO;
import ro.train_ticketing_siemens.dto.route.RouteResponseDTO;
import ro.train_ticketing_siemens.service.RouteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public RouteResponseDTO create(@RequestBody @Valid RouteRequestDTO dto) {
        return routeService.create(dto);
    }

    @GetMapping
    public List<RouteResponseDTO> getAll() {
        return routeService.getAll();
    }

    @GetMapping("/{id}")
    public RouteResponseDTO getById(@PathVariable UUID id) {
        return routeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        routeService.delete(id);
    }

    @PutMapping("/{id}")
    public RouteResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid RouteRequestDTO dto
    ) {
        return routeService.update(id, dto);
    }
}