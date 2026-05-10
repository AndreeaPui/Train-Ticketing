package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.station.StationRequestDTO;
import ro.train_ticketing_siemens.dto.station.StationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StationService {

    StationResponseDTO create(StationRequestDTO dto);

    List<StationResponseDTO> getAll();

    StationResponseDTO getById(UUID id);

    void delete(UUID id);

    StationResponseDTO update(UUID id, StationRequestDTO dto);
}
