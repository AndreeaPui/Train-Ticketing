package ro.train_ticketing_siemens.service.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.train_ticketing_siemens.domain.Booking;
import ro.train_ticketing_siemens.domain.Schedule;
import ro.train_ticketing_siemens.domain.Station;
import ro.train_ticketing_siemens.dto.booking.BookingRequestDTO;
import ro.train_ticketing_siemens.dto.booking.BookingResponseDTO;
import ro.train_ticketing_siemens.mapper.BookingMapper;
import ro.train_ticketing_siemens.repository.BookingRepository;
import ro.train_ticketing_siemens.repository.ScheduleRepository;
import ro.train_ticketing_siemens.repository.StationRepository;
import ro.train_ticketing_siemens.service.BookingService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static final BigDecimal PRICE_PER_TICKET = BigDecimal.valueOf(50);

    private final BookingRepository bookingRepository;
    private final ScheduleRepository scheduleRepository;
    private final StationRepository stationRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponseDTO create(BookingRequestDTO dto) {
        Schedule schedule = scheduleRepository.findById(dto.scheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Station origin = stationRepository.findById(dto.originStationId())
                .orElseThrow(() -> new RuntimeException("Origin station not found"));

        Station destination = stationRepository.findById(dto.destinationStationId())
                .orElseThrow(() -> new RuntimeException("Destination station not found"));

        int alreadyBooked = bookingRepository.findByScheduleIdAndDeletedFalse(schedule.getId())
                .stream()
                .mapToInt(Booking::getNumTickets)
                .sum();

        int capacity = schedule.getTrain().getCapacity();

        if (alreadyBooked + dto.numTickets() > capacity) {
            throw new RuntimeException(
                    "Not enough seats. Available seats: " + (capacity - alreadyBooked)
            );
        }

        Booking booking = Booking.builder()
                .bookingReference("BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .schedule(schedule)
                .originStation(origin)
                .destinationStation(destination)
                .customerName(dto.customerName())
                .customerEmail(dto.customerEmail())
                .numTickets(dto.numTickets())
                .totalPrice(PRICE_PER_TICKET.multiply(BigDecimal.valueOf(dto.numTickets())))
                .createdAt(LocalDateTime.now())
                .build();

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public List<BookingResponseDTO> getAll() {
        return bookingRepository.findByDeletedFalse()
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Override
    public List<BookingResponseDTO> getByScheduleId(UUID scheduleId) {
        return bookingRepository.findByScheduleIdAndDeletedFalse(scheduleId)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Override
    public BookingResponseDTO getById(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return bookingMapper.toDto(booking);
    }

    @Override
    public void delete(UUID id) {

        Booking booking = bookingRepository.findById(id)
                .filter(b -> !b.getDeleted())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setDeleted(true);

        bookingRepository.save(booking);
    }
}
