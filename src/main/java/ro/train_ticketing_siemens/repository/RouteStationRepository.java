package ro.train_ticketing_siemens.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ro.train_ticketing_siemens.domain.RouteStation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RouteStationRepository extends JpaRepository<RouteStation, UUID> {
    List<RouteStation> findByDeletedFalse();

    List<RouteStation> findByRouteIdAndDeletedFalseOrderByStopOrderAsc(UUID routeId);

    Optional<RouteStation> findByRouteIdAndStationIdAndDeletedFalse(UUID routeId, UUID stationId);

}
