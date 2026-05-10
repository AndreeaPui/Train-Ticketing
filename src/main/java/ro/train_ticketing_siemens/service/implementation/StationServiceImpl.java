package ro.train_ticketing_siemens.service.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.train_ticketing_siemens.domain.Station;
import ro.train_ticketing_siemens.dto.station.StationRequestDTO;
import ro.train_ticketing_siemens.dto.station.StationResponseDTO;
import ro.train_ticketing_siemens.mapper.StationMapper;
import ro.train_ticketing_siemens.repository.StationRepository;
import ro.train_ticketing_siemens.service.StationService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    @Override
    public StationResponseDTO create(StationRequestDTO dto) {

        Station station = stationMapper.toEntity(dto);

        Station savedStation = stationRepository.save(station);

        return stationMapper.toDto(savedStation);
    }

    @Override
    public List<StationResponseDTO> getAll() {
        return stationRepository.findByDeletedFalse()
                .stream()
                .map(stationMapper::toDto)
                .toList();
    }

    @Override
    public StationResponseDTO getById(UUID id) {

        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        return stationMapper.toDto(station);
    }

    @Override
    public void delete(UUID id) {
        Station station = stationRepository.findById(id)
                .filter(s -> !s.getDeleted())
                .orElseThrow(() -> new RuntimeException("Station not found"));

        station.setDeleted(true);
        stationRepository.save(station);
    }

    @Override
    public StationResponseDTO update(UUID id, StationRequestDTO dto) {
        Station station = stationRepository.findById(id)
                .filter(s -> !s.getDeleted())
                .orElseThrow(() -> new RuntimeException("Station not found"));

        station.setName(dto.name());
        station.setCity(dto.city());
        station.setCode(dto.code());

        return stationMapper.toDto(stationRepository.save(station));
    }
}
