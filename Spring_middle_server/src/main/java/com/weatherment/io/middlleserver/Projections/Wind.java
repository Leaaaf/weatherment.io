package com.weatherment.io.middlleserver.Projections;

public class Wind {

    private long id;
    private float value;
    private String direction;
    private long emitted_at;


    public Wind(long id, float value, String direction, long emitted_at) {
        this.id = id;
        this.value = value;
        this.direction = direction;
        this.emitted_at = emitted_at;
    }

    public long getEmitted_at() {
        return emitted_at;
    }

    public void setEmitted_at(long emitted_at) {
        this.emitted_at = emitted_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getValue() {
        return (float) (Math.round(value * 10.0) / 10.0);
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
