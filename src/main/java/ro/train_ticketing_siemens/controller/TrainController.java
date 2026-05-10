package ro.train_ticketing_siemens.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.train.TrainRequestDTO;
import ro.train_ticketing_siemens.dto.train.TrainResponseDTO;
import ro.train_ticketing_siemens.service.TrainService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trains")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;

    @PostMapping
    public TrainResponseDTO create(@RequestBody @Valid TrainRequestDTO dto) {
        return trainService.create(dto);
    }

    @GetMapping
    public List<TrainResponseDTO> getAll() {
        return trainService.getAll();
    }

    @GetMapping("/{id}")
    public TrainResponseDTO getById(@PathVariable UUID id) {
        return trainService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        trainService.delete(id);
    }

    @PutMapping("/{id}")
    public TrainResponseDTO update(@PathVariable UUID id, @RequestBody @Valid TrainRequestDTO dto) {
        return trainService.update(id, dto);
    }
}
