package com.example.shdavido.tripplanner.Model;


import org.json.simple.JSONObject;

import java.io.Serializable;

public class Location implements Serializable{

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public JSONObject parseLocation() {
        JSONObject locationJSON = new JSONObject();
        locationJSON.put("latitude", this.latitude);
        locationJSON.put("longitude", this.longitude);

        return locationJSON;
    }
}
