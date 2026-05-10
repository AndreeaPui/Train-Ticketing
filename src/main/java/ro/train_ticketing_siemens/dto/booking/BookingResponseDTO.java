package ro.train_ticketing_siemens.dto.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDTO(
        UUID id,
        String bookingReference,
        UUID scheduleId,
        String trainNumber,
        String customerName,
        String customerEmail,
        String originStation,
        String destinationStation,
        Integer numTickets,
        BigDecimal totalPrice,
        LocalDateTime createdAt
) {
}
