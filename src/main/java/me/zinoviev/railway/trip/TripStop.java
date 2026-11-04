package me.zinoviev.railway.trip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zinoviev.railway.station.Station;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "trip_stops")
@Getter
@Setter
@NoArgsConstructor
public class TripStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(nullable = false)
    private int stopOrder;

    private OffsetDateTime arrivalTime;

    private OffsetDateTime departureTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal priceFromStart;

}
