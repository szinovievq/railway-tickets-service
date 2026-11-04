package me.zinoviev.railway.dto;

import me.zinoviev.railway.train.CarriageType;

public record SeatView(
        Long seatId,
        int carriageNumber,
        CarriageType type,
        int seatNumber,
        boolean free
) {
}
