package com.weatherment.io.middlleserver;

import com.weatherment.io.middlleserver.Projections.Temperature;
import com.weatherment.io.middlleserver.Repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MockController {

    @Autowired
    TemperatureRepository temperatureRepository;

    @GetMapping("/mock/temperature/one")
    public Temperature getTemp() {
        return MockValues.getInstance().getTemperatureRandom();
    }

    @GetMapping("/mock/temperature/all")
    public List<Temperature> getAll() {
        return MockValues.getInstance().getAllTemperatures();
    }

    @GetMapping("/db/temperature/all")
    public List<Temperature> getFromDb() {
        return temperatureRepository.findAll();
    }
}
