package me.zinoviev.railway.dto;

import me.zinoviev.railway.trip.TripStatus;

import java.time.LocalDate;
import java.util.List;

public record TripResponse(
        Long id,
        String trainNumber,
        LocalDate departureDate,
        TripStatus status,
        List<StopResponse> stops
) {

    public record StopResponse(
            Long stationId,
            String stationName,
            int stopOrder
    ){}

}
