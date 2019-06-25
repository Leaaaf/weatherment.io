package com.weatherment.io.middlleserver.Repository;

import com.weatherment.io.middlleserver.Projections.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature,Long> {
    List<Temperature> findAll();
}
