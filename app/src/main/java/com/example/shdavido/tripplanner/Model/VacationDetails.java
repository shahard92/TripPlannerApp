package com.example.shdavido.tripplanner.Model;


import org.json.simple.JSONObject;

public class VacationDetails {

    private String city = null;
    private Integer days = null;
    private Location sleepLocation = null;

    public VacationDetails(String city, int days, Location sleepLocation) {
        this.city = city;
        this.days = days;
        this.sleepLocation = sleepLocation;
    }

    public VacationDetails() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Location getSleepLocation() {
        return sleepLocation;
    }

    public void setSleepLocation(Location sleepLocation) {
        this.sleepLocation = sleepLocation;
    }


    public JSONObject parseVacationDetails() {
        JSONObject vacationDetailsJSON = new JSONObject();

        vacationDetailsJSON.put("City", this.city);
        vacationDetailsJSON.put("Days", this.days);
        vacationDetailsJSON.put("SleepLocation", this.sleepLocation.parseLocation());

        return vacationDetailsJSON;
    }
}

