package com.example.shdavido.tripplanner.Model;


import org.json.simple.JSONObject;

import java.io.Serializable;


public class TravelingSlot extends ActivityDescription implements Serializable {
    private String fromActivity;
    private String toActivity;

    public TravelingSlot(JSONObject travelJSON, String fromActivity, String toActivity) {
        JSONObject timesJSON = (JSONObject)travelJSON.get("TimeSlot");
        this.startHour = (double)timesJSON.get("StartTime");
        this.endHour = (double)timesJSON.get("EndTime");

        this.fromActivity = fromActivity;
        this.toActivity = toActivity;


        if(fromActivity == null) this.title = "Traveling to " + toActivity;
        else this.title = "Traveling from " + fromActivity + " to " + toActivity;
    }
}

