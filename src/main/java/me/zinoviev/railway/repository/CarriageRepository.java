package me.zinoviev.railway.repository;

import me.zinoviev.railway.train.Carriage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarriageRepository extends JpaRepository<Carriage, Long> {
}
