package me.zinoviev.railway.repository;

import me.zinoviev.railway.trip.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("""
            select distinct t from Trip t
            join fetch t.train
            join t.stops a
            join t.stops b
            where a.station.id = :fromId
                and b.station.id = :toId
                and a.stopOrder < b.stopOrder
                and t.departureDate = :date
                and t.status = me.zinoviev.railway.trip.TripStatus.SCHEDULED
            order by t.id
            """)
    List<Trip> search(
            @Param("fromId") Long fromId,
            @Param("toId") Long toId,
            @Param("date")LocalDate date
            );

    @EntityGraph(attributePaths = {
            "stops",
            "stops.station",
            "train"
    })
    Optional<Trip> findWithStopsById(Long id);

}
