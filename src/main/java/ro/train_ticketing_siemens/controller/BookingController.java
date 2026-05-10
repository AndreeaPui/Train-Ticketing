package ro.train_ticketing_siemens.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.train_ticketing_siemens.dto.booking.BookingRequestDTO;
import ro.train_ticketing_siemens.dto.booking.BookingResponseDTO;
import ro.train_ticketing_siemens.service.BookingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDTO create(@RequestBody @Valid BookingRequestDTO dto) {
        return bookingService.create(dto);
    }

    @GetMapping
    public List<BookingResponseDTO> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/{id}")
    public BookingResponseDTO getById(@PathVariable UUID id) {
        return bookingService.getById(id);
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<BookingResponseDTO> getByScheduleId(@PathVariable UUID scheduleId) {
        return bookingService.getByScheduleId(scheduleId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookingService.delete(id);
    }
}