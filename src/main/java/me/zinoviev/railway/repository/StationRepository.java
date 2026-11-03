package me.zinoviev.railway.repository;

import me.zinoviev.railway.station.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findByCode(String code);
    boolean existsByCode(String code);

}
