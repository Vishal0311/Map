package com.vishal.newmap;

public class Metrics {
    public Metrics(){

    }
    private int calories;
    private Double duration;
    private Double distance;
    private Float speed;

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Metrics(int calories, Double duration, Double distance, Float speed) {
        this.calories = calories;
        this.duration = duration;
        this.distance = distance;
        this.speed=speed;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
