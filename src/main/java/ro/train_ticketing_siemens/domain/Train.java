package ro.train_ticketing_siemens.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "trains")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train extends BaseEntity {

    @Column(name = "train_number", nullable = false, unique = true, length = 50)
    private String trainNumber;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id")
    private Route route;
}
