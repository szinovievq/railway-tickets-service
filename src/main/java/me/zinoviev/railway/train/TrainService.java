package me.zinoviev.railway.train;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.common.ConflictException;
import me.zinoviev.railway.common.NotFoundException;
import me.zinoviev.railway.dto.CreateTrainRequest;
import me.zinoviev.railway.dto.TrainResponse;
import me.zinoviev.railway.mapper.TrainMapper;
import me.zinoviev.railway.repository.TrainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrainService {

    private final TrainRepository trainRepository;
    private final TrainMapper trainMapper;

    @Transactional
    public TrainResponse create(CreateTrainRequest request){
        if (trainRepository.existsByNumber(request.number())){
            throw new ConflictException("Train number already exists");
        }

        Train train = new Train();
        train.setNumber(request.number());
        train.setName(request.name());

        for (CreateTrainRequest.CarriageSpec spec : request.carriages()) {
            Carriage carriage = new Carriage();
            carriage.setNumber(spec.number());
            carriage.setType(spec.type());

            carriage.generateSeats();
            train.addCarriage(carriage);
        }

        Train saved = trainRepository.save(train);

        return trainMapper.toResponse(saved);
    }

    public TrainResponse getById(Long id){
        Train train = trainRepository.findWithCarriagesById(id).orElseThrow(() -> new NotFoundException("Train"));

        return trainMapper.toResponse(train);
    }

    public Page<TrainResponse> findAll(Pageable pageable){
        return trainRepository.findAll(pageable).map(trainMapper::toResponse);
    }
}
