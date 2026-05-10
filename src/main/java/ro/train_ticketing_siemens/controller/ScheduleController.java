package ro.train_ticketing_siemens.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.request.DelayRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleResponseDTO;
import ro.train_ticketing_siemens.service.ScheduleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponseDTO create(@RequestBody @Valid ScheduleRequestDTO dto) {
        return scheduleService.create(dto);
    }

    @GetMapping
    public List<ScheduleResponseDTO> getAll() {
        return scheduleService.getAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDTO getById(@PathVariable UUID id) {
        return scheduleService.getById(id);
    }

    @GetMapping("/train/{trainId}")
    public List<ScheduleResponseDTO> getByTrainId(@PathVariable UUID trainId) {
        return scheduleService.getByTrainId(trainId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        scheduleService.delete(id);
    }

    @PutMapping("/{id}")
    public ScheduleResponseDTO update(@PathVariable UUID id, @RequestBody @Valid ScheduleRequestDTO dto) {
        return scheduleService.update(id, dto);
    }

    @PostMapping("/{id}/delay")
    public ScheduleResponseDTO reportDelay(@PathVariable UUID id, @RequestBody @Valid DelayRequestDTO dto) {
        return scheduleService.reportDelay(id, dto);
    }
}
