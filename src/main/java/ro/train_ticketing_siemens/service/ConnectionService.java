package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.connection.ConnectionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ConnectionService {

    List<ConnectionResponseDTO> findConnections(UUID originStationId, UUID destinationStationId);
}
