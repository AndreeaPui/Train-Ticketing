package ro.train_ticketing_siemens.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.train_ticketing_siemens.domain.RouteStation;
import ro.train_ticketing_siemens.domain.Schedule;
import ro.train_ticketing_siemens.dto.connection.ConnectionLegResponseDTO;
import ro.train_ticketing_siemens.dto.connection.ConnectionResponseDTO;
import ro.train_ticketing_siemens.repository.RouteStationRepository;
import ro.train_ticketing_siemens.repository.ScheduleRepository;
import ro.train_ticketing_siemens.service.ConnectionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    private final ScheduleRepository scheduleRepository;
    private final RouteStationRepository routeStationRepository;

    @Override
    public List<ConnectionResponseDTO> findConnections(UUID originStationId, UUID destinationStationId) {
        List<ConnectionResponseDTO> results = new ArrayList<>();

        List<Schedule> schedules = scheduleRepository.findByDeletedFalse();

        for (Schedule schedule : schedules) {
            RouteStation origin = routeStationRepository
                    .findByRouteIdAndStationIdAndDeletedFalse(
                            schedule.getTrain().getRoute().getId(),
                            originStationId
                    )
                    .orElse(null);

            RouteStation destination = routeStationRepository
                    .findByRouteIdAndStationIdAndDeletedFalse(
                            schedule.getTrain().getRoute().getId(),
                            destinationStationId
                    )
                    .orElse(null);

            if (origin != null && destination != null
                    && origin.getStopOrder() < destination.getStopOrder()) {

                ConnectionLegResponseDTO leg = buildLeg(schedule, origin, destination);

                results.add(new ConnectionResponseDTO(
                        true,
                        List.of(leg)
                ));
            }
        }

        for (Schedule firstSchedule : schedules) {
            for (Schedule secondSchedule : schedules) {

                if (firstSchedule.getId().equals(secondSchedule.getId())) {
                    continue;
                }

                List<RouteStation> firstRouteStations =
                        routeStationRepository.findByRouteIdAndDeletedFalseOrderByStopOrderAsc(
                                firstSchedule.getTrain().getRoute().getId()
                        );

                List<RouteStation> secondRouteStations =
                        routeStationRepository.findByRouteIdAndDeletedFalseOrderByStopOrderAsc(
                                secondSchedule.getTrain().getRoute().getId()
                        );

                RouteStation originOnFirst = findStation(firstRouteStations, originStationId);
                RouteStation destinationOnSecond = findStation(secondRouteStations, destinationStationId);

                if (originOnFirst == null || destinationOnSecond == null) {
                    continue;
                }

                for (RouteStation changeStationFirst : firstRouteStations) {
                    RouteStation changeStationSecond = findStation(
                            secondRouteStations,
                            changeStationFirst.getStation().getId()
                    );

                    if (changeStationSecond == null) {
                        continue;
                    }

                    boolean validFirstLeg =
                            originOnFirst.getStopOrder() < changeStationFirst.getStopOrder();

                    boolean validSecondLeg =
                            changeStationSecond.getStopOrder() < destinationOnSecond.getStopOrder();

                    if (!validFirstLeg || !validSecondLeg) {
                        continue;
                    }

                    LocalDateTime firstArrivalAtChange =
                            firstSchedule.getDepartureTime()
                                    .plusMinutes(changeStationFirst.getArrivalOffsetMinutes())
                                    .plusMinutes(firstSchedule.getDelayMinutes());

                    LocalDateTime secondDepartureFromChange =
                            secondSchedule.getDepartureTime()
                                    .plusMinutes(changeStationSecond.getDepartureOffsetMinutes())
                                    .plusMinutes(secondSchedule.getDelayMinutes());

                    if (secondDepartureFromChange.isBefore(firstArrivalAtChange)) {
                        continue;
                    }

                    ConnectionLegResponseDTO firstLeg =
                            buildLeg(firstSchedule, originOnFirst, changeStationFirst);

                    ConnectionLegResponseDTO secondLeg =
                            buildLeg(secondSchedule, changeStationSecond, destinationOnSecond);

                    results.add(new ConnectionResponseDTO(
                            false,
                            List.of(firstLeg, secondLeg)
                    ));
                }
            }
        }

        if (results.isEmpty()) {
            throw new RuntimeException("No connections found between the selected stations");
        }

        return results;
    }

    private RouteStation findStation(List<RouteStation> routeStations, UUID stationId) {
        return routeStations.stream()
                .filter(rs -> rs.getStation().getId().equals(stationId))
                .findFirst()
                .orElse(null);
    }

    private ConnectionLegResponseDTO buildLeg(
            Schedule schedule,
            RouteStation origin,
            RouteStation destination
    ) {
        LocalDateTime departureTime = schedule.getDepartureTime()
                .plusMinutes(origin.getDepartureOffsetMinutes())
                .plusMinutes(schedule.getDelayMinutes());

        LocalDateTime arrivalTime = schedule.getDepartureTime()
                .plusMinutes(destination.getArrivalOffsetMinutes())
                .plusMinutes(schedule.getDelayMinutes());

        return new ConnectionLegResponseDTO(
                schedule.getId(),
                schedule.getTrain().getTrainNumber(),
                schedule.getTrain().getName(),
                origin.getStation().getName(),
                destination.getStation().getName(),
                departureTime,
                arrivalTime
        );
    }
}
