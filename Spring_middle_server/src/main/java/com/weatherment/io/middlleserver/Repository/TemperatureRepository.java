package com.weatherment.io.middlleserver.Repository;

import com.weatherment.io.middlleserver.Projections.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature,Long> {
}
