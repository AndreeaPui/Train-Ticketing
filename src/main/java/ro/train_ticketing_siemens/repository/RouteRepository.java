package ro.train_ticketing_siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.train_ticketing_siemens.domain.Route;

import java.util.List;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
    List<Route> findByDeletedFalse();

}
