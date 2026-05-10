package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.request.DelayRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleRequestDTO;
import ro.train_ticketing_siemens.dto.schedule.ScheduleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    ScheduleResponseDTO create(ScheduleRequestDTO dto);

    List<ScheduleResponseDTO> getAll();

    ScheduleResponseDTO getById(UUID id);

    List<ScheduleResponseDTO> getByTrainId(UUID trainId);

    void delete(UUID id);

    ScheduleResponseDTO update(UUID id, ScheduleRequestDTO dto);

    ScheduleResponseDTO reportDelay(UUID scheduleId, DelayRequestDTO dto);
}
