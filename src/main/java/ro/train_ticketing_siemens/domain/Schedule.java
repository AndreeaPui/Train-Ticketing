package ro.train_ticketing_siemens.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "delay_minutes", nullable = false)
    private Integer delayMinutes;
}
