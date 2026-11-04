package me.zinoviev.railway.trip;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.common.ConflictException;
import me.zinoviev.railway.common.NotFoundException;
import me.zinoviev.railway.dto.CreateTripRequest;
import me.zinoviev.railway.dto.TripResponse;
import me.zinoviev.railway.dto.TripSearchResponse;
import me.zinoviev.railway.repository.StationRepository;
import me.zinoviev.railway.repository.TrainRepository;
import me.zinoviev.railway.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TripService {

    private final TripRepository tripRepository;
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;

    @Transactional
    public TripResponse create(CreateTripRequest request){
        var train = trainRepository.findById(request.trainId()).orElseThrow(() -> new NotFoundException("Train"));

        Trip trip = new Trip();
        trip.setTrain(train);
        trip.setDepartureDate(request.departureDate());

        List<CreateTripRequest.StopSec> stops = request.stops();

        OffsetDateTime previousTime = null;
        for (int i = 0; i < stops.size(); i++){
            CreateTripRequest.StopSec spec = stops.get(i);

            var station = stationRepository.findById(spec.stationId()).orElseThrow(() -> new NotFoundException("Station"));

            if (spec.arrival() != null && spec.departure() != null &&
            spec.arrival().isAfter(spec.departure())){
                throw new ConflictException("Arrival time cannot be after departure time");
            }

            if (spec.arrival() != null){
                if (previousTime != null && !spec.arrival().isAfter(previousTime)){
                    throw new ConflictException("Stop times must be in ascending order");
                }

                previousTime = spec.arrival();
            }

            if (spec.departure() != null ){
                if (previousTime != null && spec.departure().isBefore(previousTime)){
                    throw new ConflictException("Stop times must be in ascending order");
                }

                previousTime = spec.departure();
            }

            TripStop stop = new TripStop();
            stop.setStation(station);
            stop.setStopOrder(i);
            stop.setArrivalTime(spec.arrival());
            stop.setDepartureTime(spec.departure());
            stop.setPriceFromStart(spec.priceFromStart());

            trip.addStop(stop);
        }

        Trip saved = tripRepository.save(trip);

        return toResponse(saved);
    }

    public void cancel(Long tripId){
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new NotFoundException("Trip"));
        trip.setStatus(TripStatus.CANCELLED);
        tripRepository.save(trip);
    }

    private TripResponse toResponse(Trip trip){
        return new TripResponse(
                trip.getId(),
                trip.getTrain().getNumber(),
                trip.getDepartureDate(),
                trip.getStatus(),
                trip.getStops().stream().map(
                        stop -> new TripResponse.StopResponse(
                                stop.getStation().getId(),
                                stop.getStation().getName(),
                                stop.getStopOrder()
                        )).toList()
        );
    }

}
