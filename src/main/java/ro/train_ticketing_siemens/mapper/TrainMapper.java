package ro.train_ticketing_siemens.mapper;

import org.springframework.stereotype.Component;
import ro.train_ticketing_siemens.domain.Route;
import ro.train_ticketing_siemens.domain.Train;
import ro.train_ticketing_siemens.dto.train.TrainRequestDTO;
import ro.train_ticketing_siemens.dto.train.TrainResponseDTO;

@Component
public class TrainMapper {

    public Train toEntity(TrainRequestDTO dto, Route route) {
        return Train.builder()
                .trainNumber(dto.trainNumber())
                .name(dto.name())
                .capacity(dto.capacity())
                .route(route)
                .build();
    }

    public TrainResponseDTO toDto(Train train) {
        return new TrainResponseDTO(
                train.getId(),
                train.getTrainNumber(),
                train.getName(),
                train.getCapacity(),
                train.getRoute().getId(),
                train.getRoute().getName()
        );
    }
}
