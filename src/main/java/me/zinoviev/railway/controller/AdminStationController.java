package me.zinoviev.railway.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.dto.StationRequest;
import me.zinoviev.railway.dto.StationResponse;
import me.zinoviev.railway.station.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/stations")
@RequiredArgsConstructor
public class AdminStationController {

    private final StationService stationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StationResponse create(@Valid @RequestBody StationRequest request) {
        return stationService.create(request);
    }

    @PutMapping("/{id}")
    public StationResponse update(
            @PathVariable Long id,
            @Valid @RequestBody StationRequest request){
        return stationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stationService.delete(id);
    }

}
