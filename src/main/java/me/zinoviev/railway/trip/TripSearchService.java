package me.zinoviev.railway.trip;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.common.BadRequestException;
import me.zinoviev.railway.dto.TripSearchResponse;
import me.zinoviev.railway.repository.SeatRepository;
import me.zinoviev.railway.repository.TripRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripSearchService {

    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;

    public List<TripSearchResponse> search(Long fromId, Long toId, LocalDate date) {

        if (fromId.equals(toId)) {
            throw new BadRequestException("Departure and arrival stations must be different");
        }

        List<Trip> trips = tripRepository.search(fromId, toId, date);

        return trips.stream().map(trip -> {
           TripStop fromStop = trip.getStops().stream().filter(
                   stop -> stop.getStation().getId().equals(fromId)).findFirst().orElseThrow();

            TripStop toStop = trip.getStops().stream().filter(
                    stop -> stop.getStation().getId().equals(toId)).findFirst().orElseThrow();

            BigDecimal basePrice = toStop.getPriceFromStart().subtract(fromStop.getPriceFromStart());
            long freeSeats = seatRepository.findByCarriageTrainId(trip.getTrain().getId()).size();

            return new TripSearchResponse(
                    trip.getId(),
                    trip.getTrain().getNumber(),
                    trip.getTrain().getName(),
                    fromStop.getStation().getName(),
                    toStop.getStation().getName(),
                    fromStop.getDepartureTime(),
                    toStop.getArrivalTime(),
                    basePrice,
                    freeSeats
            );
        }).toList();

    }

}
