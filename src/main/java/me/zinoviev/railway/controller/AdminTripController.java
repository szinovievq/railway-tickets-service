package me.zinoviev.railway.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.zinoviev.railway.dto.CreateTripRequest;
import me.zinoviev.railway.dto.TripResponse;
import me.zinoviev.railway.trip.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/trips")
@RequiredArgsConstructor
public class AdminTripController {

    private final TripService tripService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponse create(@Valid @RequestBody CreateTripRequest request){
        return tripService.create(request);
    }

    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id){
        tripService.cancel(id);
    }

}
