package me.zinoviev.railway.dto;

import me.zinoviev.railway.train.CarriageType;

import java.util.List;

public record TrainResponse(

        Long id,
        String number,
        String name,
        List<CarriageResponse> carriages

) {

    public record CarriageResponse(

            Long id,
            int number,
            CarriageType type,
            int seatCount

    ){}

}
