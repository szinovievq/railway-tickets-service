package me.zinoviev.railway.mapper;

import me.zinoviev.railway.dto.TrainResponse;
import me.zinoviev.railway.train.Carriage;
import me.zinoviev.railway.train.Train;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainMapper {

    TrainResponse toResponse(Train train);

    @Mapping(
            target = "seatCount",
            expression = "java(carriage.getSeats().size())"
    )
    TrainResponse.CarriageResponse toResponse(Carriage carriage);

}
