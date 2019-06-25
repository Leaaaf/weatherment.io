package com.weatherment.io.middlleserver.Projections;

public class BoardState {

    private long id;

    private Temperature temperature;
    private Pressure pressure;
    private Wind wind;
    private Humidity humidity;
    private Pollution pollution;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }
}
