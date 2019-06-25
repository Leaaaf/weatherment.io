package com.weatherment.io.middlleserver.Controller;

import com.weatherment.io.middlleserver.Projections.BoardState;
import com.weatherment.io.middlleserver.Projections.Temperature;
import com.weatherment.io.middlleserver.Repository.TemperatureRepository;
import com.weatherment.io.middlleserver.Singleton.MockValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MockController {

    @Autowired
    TemperatureRepository temperatureRepository;

    @GetMapping("/mock/temperature/one")
    public Temperature getTemp() {
        Temperature res = MockValues.getInstance().getTemperature();
        System.out.println(new Date().getTime() + new Date().toString() + " - Serve temperature to client \n" + res);
        return res;
    }

    @GetMapping("/mock/boardstate")
    public BoardState getBoardState() {
        BoardState res = MockValues.getInstance().getBoardState();
        System.out.println("Print board state\n" + res);
        return res;
    }

    @GetMapping("/mock/temperature/all")
    public List<Temperature> getAll() {
        System.out.println("Serve all temperatures to client");
        return MockValues.getInstance().getAllTemperatures();
    }

    @GetMapping("/db/temperature/all")
    public List<Temperature> getFromDb() {
        System.out.println("Serve all temperature from DB");
        return temperatureRepository.findAll();
    }
}
