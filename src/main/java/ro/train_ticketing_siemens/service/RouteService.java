package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.route.RouteRequestDTO;
import ro.train_ticketing_siemens.dto.route.RouteResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RouteService {
    RouteResponseDTO create(RouteRequestDTO dto);

    List<RouteResponseDTO> getAll();

    RouteResponseDTO getById(UUID id);

    void delete(UUID id);

    RouteResponseDTO update(UUID id, RouteRequestDTO dto);

}
