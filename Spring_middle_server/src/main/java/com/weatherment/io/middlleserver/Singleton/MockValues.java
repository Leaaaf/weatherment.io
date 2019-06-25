package com.weatherment.io.middlleserver.Singleton;

import com.weatherment.io.middlleserver.Projections.Temperature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MockValues {

    protected static MockValues instance;
    protected List<Temperature> temperatureList;
    protected final int MAX_TEMPERATURES = 40;
    protected final long START_TIMESTAMP = new Date().getTime() - MAX_TEMPERATURES*(1000*60);
    protected Random rand;

    public static MockValues getInstance() {
        if (null == instance) instance = new MockValues();
        return instance;
    }

    protected MockValues() {
        this.temperatureList = new ArrayList<>();
        rand = new Random();
        for (int i = 0; i < MAX_TEMPERATURES; i++) {
            Temperature temp = new Temperature();
            temp.setValue(25 + rand.nextFloat() * (30 - 20)); // Generate a random value form 25 to 30;
            temp.setId(i + 1L);
            temp.setEmitted_at(START_TIMESTAMP + (1000 * 60) * i + 1);
            temperatureList.add(temp);
        }
        System.out.println("Created temperature mocked values");
    }

    public Temperature getTemperatureRandom() {
        Temperature temp = new Temperature();
        temp.setValue(25 + rand.nextFloat() * (30 - 25)); // Generate a random value form 25 to 30;
        temp.setId(1L);
        temp.setEmitted_at( new Date().getTime());
        return temp;
    }

    public List<Temperature> getAllTemperatures() {
        return temperatureList;
    }
}
