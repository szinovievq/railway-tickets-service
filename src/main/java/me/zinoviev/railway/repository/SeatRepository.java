package me.zinoviev.railway.repository;

import me.zinoviev.railway.train.Seat;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByCarriageTrainId(Long trainId);

}
