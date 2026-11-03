package me.zinoviev.railway.controller;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.dto.StationResponse;
import me.zinoviev.railway.station.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @GetMapping
    public List<StationResponse> findAll() {
        return stationService.findAll();
    }

    @GetMapping("/{id}")
    public StationResponse getById(@PathVariable Long id){
        return stationService.getById(id);
    }

}
