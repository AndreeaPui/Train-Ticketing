package ro.train_ticketing_siemens.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.station.StationRequestDTO;
import ro.train_ticketing_siemens.dto.station.StationResponseDTO;
import ro.train_ticketing_siemens.service.StationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping
    public StationResponseDTO create(@RequestBody @Valid StationRequestDTO dto) {
        return stationService.create(dto);
    }

    @GetMapping
    public List<StationResponseDTO> getAll() {
        return stationService.getAll();
    }

    @GetMapping("/{id}")
    public StationResponseDTO getById(@PathVariable UUID id) {
        return stationService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        stationService.delete(id);
    }

    @PutMapping("/{id}")
    public StationResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid StationRequestDTO dto
    ) {
        return stationService.update(id, dto);
    }
}
