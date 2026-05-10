package ro.train_ticketing_siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.train_ticketing_siemens.domain.Train;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainRepository extends JpaRepository<Train, UUID> {
    Optional<Train> findByTrainNumber(String trainNumber);

    boolean existsByTrainNumber(String trainNumber);

    List<Train> findByDeletedFalse();
}
