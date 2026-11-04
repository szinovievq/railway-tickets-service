package me.zinoviev.railway.controller;

import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.common.NotFoundException;
import me.zinoviev.railway.dto.TripSearchResponse;
import me.zinoviev.railway.repository.TripRepository;
import me.zinoviev.railway.trip.Trip;
import me.zinoviev.railway.trip.TripSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripSearchService tripSearchService;
    private final TripRepository tripRepository;

    @GetMapping("/search")
    public List<TripSearchResponse> search(
            @RequestParam Long from,
            @RequestParam Long to,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return tripSearchService.search(from, to, date);
    }

    @GetMapping("/{id}")
    public Trip getById(@PathVariable Long id){
        return tripRepository.findWithStopsById(id).orElseThrow(() -> new NotFoundException("Trip"));
    }
}
