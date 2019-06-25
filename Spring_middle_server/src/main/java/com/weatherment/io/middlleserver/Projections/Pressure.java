package com.weatherment.io.middlleserver.Projections;

public class Pressure {
    private long id;
    private float value;
    private long emitted_at;

    public Pressure(long id, float value, long emitted_at) {
        this.id = id;
        this.value = value;
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
}
