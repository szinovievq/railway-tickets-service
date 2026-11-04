package me.zinoviev.railway.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TripSearchResponse(
        Long tripId,
        String trainNumber,
        String trainName,
        String fromStation,
        String toStation,
        OffsetDateTime departure,
        OffsetDateTime arrival,
        BigDecimal basePrice,
        long freeSeats
) {
}
