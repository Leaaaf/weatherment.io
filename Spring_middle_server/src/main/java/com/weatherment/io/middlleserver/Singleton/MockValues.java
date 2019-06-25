package com.weatherment.io.middlleserver.Singleton;

import com.weatherment.io.middlleserver.Projections.BoardState;
import com.weatherment.io.middlleserver.Projections.Pressure;
import com.weatherment.io.middlleserver.Projections.Temperature;
import com.weatherment.io.middlleserver.Projections.Wind;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MockValues {

    protected static MockValues instance;
    protected final int MAX_TEMPERATURES = 40;
    protected final long START_TIMESTAMP = new Date().getTime() - MAX_TEMPERATURES * (1000 * 60);
    protected List<Temperature> temperatureList;
    protected Random rand;
    protected Temperature lastTemp;
    protected float lastTempValue;

    protected MockValues() {
        this.temperatureList = new ArrayList<>();
        rand = new Random();
        for (int i = 0; i < MAX_TEMPERATURES; i++) {
            Temperature temp = new Temperature();
            temp.setValue((25 + rand.nextFloat() * (30 - 20))); // Generate a random value form 25 to 30;
            temp.setId(i + 1L);
            temp.setEmitted_at(START_TIMESTAMP + (1000 * 60) * i + 1);
            temperatureList.add(temp);
        }
        System.out.println("Created temperature mocked values");
    }

    public static MockValues getInstance() {
        if (null == instance) instance = new MockValues();
        return instance;
    }

    public Temperature getTemperature() {
        if (lastTemp != null) {
            Temperature res;
            res = lastTemp;
            lastTemp = null;
            return res;
        }

        lastTemp = getTemperatureRandom();
        lastTempValue = lastTemp.getValue();
        return lastTemp;
    }

    public Temperature getTemperatureRandom() {
        Temperature temp = new Temperature();
        float max, min;
        if (1 < lastTempValue) {
            max = lastTempValue + 0.5F;
            min = lastTempValue - 0.5F;
        } else {
            min = 25;
            max = 35;
        }

        temp.setValue(min + rand.nextFloat() * (max - min)); // Generate a random value form 25 to 30;
        temp.setId(Long.valueOf(rand.nextInt(100)));
        temp.setEmitted_at(new Date().getTime());
        return temp;
    }

    public List<Temperature> getAllTemperatures() {
        return temperatureList;
    }

    public BoardState getBoardState() {
        BoardState res = new BoardState();
        res.setId(rand.nextInt(100));
        Pressure pressure = new Pressure(rand.nextInt(100), 10 + rand.nextFloat() * (30 - 10), new Date().getTime());
        Wind wind = new Wind(1, 5 + rand.nextFloat() * (25 - 5), "N/NE", new Date().getTime());
        res.setWind(wind);
        res.setTemperature(getTemperature());
        res.setPressure(pressure);
        return res;
    }

}
