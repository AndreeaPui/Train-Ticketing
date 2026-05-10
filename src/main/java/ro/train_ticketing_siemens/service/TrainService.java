package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.train.TrainRequestDTO;
import ro.train_ticketing_siemens.dto.train.TrainResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TrainService {
    TrainResponseDTO create(TrainRequestDTO dto);

    List<TrainResponseDTO> getAll();

    TrainResponseDTO getById(UUID id);

    void delete(UUID id);

    TrainResponseDTO update(UUID id, TrainRequestDTO dto);

}
