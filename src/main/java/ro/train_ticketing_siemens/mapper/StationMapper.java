package ro.train_ticketing_siemens.mapper;


import org.springframework.stereotype.Component;
import ro.train_ticketing_siemens.domain.Station;
import ro.train_ticketing_siemens.dto.station.StationResponseDTO;
import ro.train_ticketing_siemens.dto.station.StationRequestDTO;

@Component
public class StationMapper {

    public Station toEntity(StationRequestDTO dto) {
        return Station.builder()
                .name(dto.name())
                .city(dto.city())
                .code(dto.code())
                .build();
    }

    public StationResponseDTO toDto(Station station) {
        return new StationResponseDTO(
                station.getId(),
                station.getName(),
                station.getCity(),
                station.getCode()
        );
    }
}