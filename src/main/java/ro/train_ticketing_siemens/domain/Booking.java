package ro.train_ticketing_siemens.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {

    @Column(name = "booking_reference", nullable = false, unique = true, length = 50)
    private String bookingReference;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_station_id")
    private Station originStation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_station_id")
    private Station destinationStation;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "customer_email", nullable = false, length = 150)
    private String customerEmail;

    @Column(name = "num_tickets", nullable = false)
    private Integer numTickets;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
