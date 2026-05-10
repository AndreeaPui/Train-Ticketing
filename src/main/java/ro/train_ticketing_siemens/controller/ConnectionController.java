package ro.train_ticketing_siemens.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.connection.ConnectionResponseDTO;
import ro.train_ticketing_siemens.service.ConnectionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping
    public List<ConnectionResponseDTO> findConnections(
            @RequestParam UUID originStationId,
            @RequestParam UUID destinationStationId
    ) {
        return connectionService.findConnections(originStationId, destinationStationId);
    }
}
