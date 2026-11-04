package me.zinoviev.railway.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record CreateTripRequest(

        @NotNull
        Long trainId,

        @NotNull
        @FutureOrPresent
        LocalDate departureDate,

        @NotEmpty
        List<@Valid StopSec> stops

) {

    public record StopSec(

            @NotNull
            Long stationId,

            OffsetDateTime arrival,

            OffsetDateTime departure,

            @NotNull
            @PositiveOrZero
            BigDecimal priceFromStart
    ){}

}
