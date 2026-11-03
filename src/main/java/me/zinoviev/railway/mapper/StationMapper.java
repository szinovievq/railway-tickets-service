package me.zinoviev.railway.mapper;

import me.zinoviev.railway.dto.StationRequest;
import me.zinoviev.railway.dto.StationResponse;
import me.zinoviev.railway.station.Station;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StationMapper {

    StationResponse toResponse(Station station);
    Station toEntity(StationRequest request);

}
