package ro.train_ticketing_siemens.service;

import ro.train_ticketing_siemens.dto.booking.BookingRequestDTO;
import ro.train_ticketing_siemens.dto.booking.BookingResponseDTO;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingResponseDTO create(BookingRequestDTO dto);

    List<BookingResponseDTO> getAll();

    List<BookingResponseDTO> getByScheduleId(UUID scheduleId);

    BookingResponseDTO getById(UUID id);

    void delete(UUID id);
}
