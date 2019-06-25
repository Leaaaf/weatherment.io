package com.weatherment.io.middlleserver.Projections;

public class Wind {

    private long id;
    private float speed;
    private String direction;
    private long emitted_at;


    public Wind(long id, float speed, String direction, long emitted_at) {
        this.id = id;
        this.speed = speed;
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

    public float getSpeed() {
        return (float) (Math.round(speed * 10.0) / 10.0);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
