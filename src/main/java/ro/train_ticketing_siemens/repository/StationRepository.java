package ro.train_ticketing_siemens.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ro.train_ticketing_siemens.domain.Station;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StationRepository extends JpaRepository<Station, UUID> {

    Optional<Station> findByCode(String code);

    boolean existsByCode(String code);

    List<Station> findByDeletedFalse();
}
