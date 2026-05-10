package ro.train_ticketing_siemens.mapper;

import org.springframework.stereotype.Component;
import ro.train_ticketing_siemens.domain.Booking;
import ro.train_ticketing_siemens.dto.booking.BookingResponseDTO;

@Component
public class BookingMapper {

    public BookingResponseDTO toDto(Booking booking) {
        return new BookingResponseDTO(
                booking.getId(),
                booking.getBookingReference(),
                booking.getSchedule().getId(),
                booking.getSchedule().getTrain().getTrainNumber(),
                booking.getCustomerName(),
                booking.getCustomerEmail(),
                booking.getOriginStation().getName(),
                booking.getDestinationStation().getName(),
                booking.getNumTickets(),
                booking.getTotalPrice(),
                booking.getCreatedAt()
        );
    }
}