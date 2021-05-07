package com.vishal.newmap;

public class SimpleLocation {
    public SimpleLocation(){

    }
    Double latitude;
    Double longitude;
    Double altitude;
    Double ts;
    Float speed;
    Float accuracy;
    Float bearing;

    public SimpleLocation(Double latitude, Double longitude, Double altitude, Double ts, Float speed, Float accuracy, Float bearing) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.ts = ts;
        this.speed = speed;
        this.accuracy = accuracy;
        this.bearing = bearing;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getTs() {
        return ts;
    }

    public void setTs(Double ts) {
        this.ts = ts;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Float getBearing() {
        return bearing;
    }

    public void setBearing(Float bearing) {
        this.bearing = bearing;
    }
}
