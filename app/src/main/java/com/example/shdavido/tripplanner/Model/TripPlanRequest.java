package com.example.shdavido.tripplanner.Model;


import org.json.simple.JSONObject;

import java.util.HashMap;


public class TripPlanRequest {

    private User user;
    private VacationDetails vacationDetails;
    private HashMap<String, Integer> preferences;

    public TripPlanRequest(User user, VacationDetails vacationDetails, HashMap<String, Integer> preferences) {
        this.user = user;
        this.vacationDetails = vacationDetails;
        this.preferences = preferences;
    }

    public JSONObject getJsonRequestForTripPlan() {
        JSONObject tripPlanRequestJSON = new JSONObject();

        tripPlanRequestJSON.put("VacationDetails", this.vacationDetails.parseVacationDetails());
        tripPlanRequestJSON.put("UserInformation", this.user.parseUser());
        tripPlanRequestJSON.put("Preferences", this.parsePreferences());

        return tripPlanRequestJSON;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VacationDetails getVacationDetails() {
        return vacationDetails;
    }

    public void setVacationDetails(VacationDetails vacationDetails) {
        this.vacationDetails = vacationDetails;
    }

    public HashMap<String, Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(HashMap<String, Integer> preferences) {
        this.preferences = preferences;
    }

    public JSONObject parsePreferences() {
        JSONObject preferencesJSON = new JSONObject(preferences);

        preferencesJSON.putAll(this.preferences);

        return preferencesJSON;
    }
}


