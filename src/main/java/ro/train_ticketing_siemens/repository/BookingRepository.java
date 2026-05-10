package ro.train_ticketing_siemens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.train_ticketing_siemens.domain.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByDeletedFalse();

    List<Booking> findByScheduleIdAndDeletedFalse(UUID scheduleId);

    List<Booking> findByCustomerEmailAndDeletedFalse(String customerEmail);
}
