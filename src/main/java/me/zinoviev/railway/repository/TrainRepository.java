package me.zinoviev.railway.repository;

import me.zinoviev.railway.train.Train;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {

    boolean existsByNumber(String number);

    @EntityGraph(attributePaths = "carriages")
    Optional<Train> findWithCarriagesById(Long id);

}
