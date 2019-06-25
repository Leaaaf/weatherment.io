package com.weatherment.io.middlleserver.Projections;

public class BoardState {

    private long id;

    private Temperature temperature;
    private Pressure pressure;
    private Wind wind;

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
}
