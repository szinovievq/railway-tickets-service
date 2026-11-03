package me.zinoviev.railway.station;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.common.ConflictException;
import me.zinoviev.railway.common.NotFoundException;
import me.zinoviev.railway.dto.StationRequest;
import me.zinoviev.railway.dto.StationResponse;
import me.zinoviev.railway.mapper.StationMapper;
import me.zinoviev.railway.repository.StationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    public List<StationResponse> findAll() {
        return stationRepository.findAll().stream().map(stationMapper::toResponse).toList();
    }

    public StationResponse getById(Long id){
        Station station = stationRepository.findById(id).orElseThrow(() -> new NotFoundException("Station"));

        return stationMapper.toResponse(station);
    }

    @Transactional
    public StationResponse create(StationRequest request){
        if (stationRepository.existsByCode(request.code())) {
            throw new ConflictException("Station code already exists");
        }

        Station station = stationMapper.toEntity(request);
        Station saved = stationRepository.save(station);

        return stationMapper.toResponse(saved);
    }

    @Transactional
    public StationResponse update(Long id, StationRequest request){
        Station station = stationRepository.findById(id).orElseThrow(() -> new NotFoundException("Station"));
        boolean codeUsedByAnotherStation = stationRepository.existsByCode(request.code())
                && !station.getCode().equals(request.code());

        if (codeUsedByAnotherStation){
            throw new ConflictException("Station code already exists");
        }

        station.setCode(request.code());
        station.setName(request.name());
        station.setCity(request.city());

        return stationMapper.toResponse(station);
    }

    public void delete(Long id){
        Station station = stationRepository.findById(id).orElseThrow(() -> new NotFoundException("Station"));
        stationRepository.delete(station);
    }

}
