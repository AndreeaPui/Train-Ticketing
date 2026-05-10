package ro.train_ticketing_siemens.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "route_stations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStation extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(optional = false)
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(name = "stop_order", nullable = false)
    private Integer stopOrder;

    @Column(name = "arrival_offset_minutes", nullable = false)
    private Integer arrivalOffsetMinutes;

    @Column(name = "departure_offset_minutes", nullable = false)
    private Integer departureOffsetMinutes;
}
