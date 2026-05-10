package ro.train_ticketing_siemens.service.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.train_ticketing_siemens.domain.Route;
import ro.train_ticketing_siemens.domain.Train;
import ro.train_ticketing_siemens.dto.train.TrainRequestDTO;
import ro.train_ticketing_siemens.dto.train.TrainResponseDTO;
import ro.train_ticketing_siemens.mapper.TrainMapper;
import ro.train_ticketing_siemens.repository.RouteRepository;
import ro.train_ticketing_siemens.repository.TrainRepository;
import ro.train_ticketing_siemens.service.TrainService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;
    private final TrainMapper trainMapper;

    @Override
    public TrainResponseDTO create(TrainRequestDTO dto) {
        Route route = routeRepository.findById(dto.routeId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Train train = trainMapper.toEntity(dto, route);
        Train savedTrain = trainRepository.save(train);

        return trainMapper.toDto(savedTrain);
    }

    @Override
    public List<TrainResponseDTO> getAll() {
        return trainRepository.findByDeletedFalse()
                .stream()
                .map(trainMapper::toDto)
                .toList();
    }

    @Override
    public TrainResponseDTO getById(UUID id) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found"));

        return trainMapper.toDto(train);
    }

    @Override
    public void delete(UUID id) {

        Train train = trainRepository.findById(id)
                .filter(t -> !t.getDeleted())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        train.setDeleted(true);

        trainRepository.save(train);
    }

    @Override
    public TrainResponseDTO update(UUID id, TrainRequestDTO dto) {
        Train train = trainRepository.findById(id)
                .filter(t -> !t.getDeleted())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        Route route = routeRepository.findById(dto.routeId())
                .filter(r -> !r.getDeleted())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        train.setTrainNumber(dto.trainNumber());
        train.setName(dto.name());
        train.setCapacity(dto.capacity());
        train.setRoute(route);

        return trainMapper.toDto(trainRepository.save(train));
    }
}