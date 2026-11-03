package me.zinoviev.railway.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.dto.CreateTrainRequest;
import me.zinoviev.railway.dto.TrainResponse;
import me.zinoviev.railway.train.TrainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;

    @GetMapping("/trains")
    public Page<TrainResponse> findAll(Pageable pageable) {
        return trainService.findAll(pageable);
    }

    @GetMapping("/trains/{id}")
    public TrainResponse getById(@PathVariable Long id) {
        return trainService.getById(id);
    }

    @PostMapping("/admin/trains")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainResponse create(@Valid @RequestBody CreateTrainRequest request) {
        return trainService.create(request);
    }
}
