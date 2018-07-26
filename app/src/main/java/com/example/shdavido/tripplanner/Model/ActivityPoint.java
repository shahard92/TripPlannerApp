package com.example.shdavido.tripplanner.Model;


import org.json.simple.JSONObject;

import java.io.Serializable;

public class ActivityPoint extends ActivityDescription implements Serializable {

    private long Id;
    private String description;
    private String type;
    private Location location;
    private long rank;
    private String opinions;
    private boolean accessible;
    private long minAge;
    private long maxAge;
    private double avgSpendingTime;
    private String website;
    private double cost;
    private long openingTime;
    private long closingTime;


    public ActivityPoint(JSONObject activitiesJSON) {

        JSONObject activityDetailsJSON = null;

            activityDetailsJSON = (JSONObject)activitiesJSON.get("Activity");
            this.Id = (long) activityDetailsJSON.get("Id");
            this.title = (String)activityDetailsJSON.get("Name");
            this.description = (String)activityDetailsJSON.get("Description");
            this.type = (String)activityDetailsJSON.get("Type");
            this.location = new Location((double)activityDetailsJSON.get("GEPSLatitude"), (double)activityDetailsJSON.get("GEPSLongtitude"));
            this.rank = (long)activityDetailsJSON.get("Rank");
            this.opinions = (String)activityDetailsJSON.get("Opinions");
            this.accessible = (boolean)activityDetailsJSON.get("Accessibility");
            this.minAge = (long)activityDetailsJSON.get("FromAge");
            this.maxAge = (long)activityDetailsJSON.get("ToAge");
            this.avgSpendingTime = (double)activityDetailsJSON.get("AvgSpendingTimeInHours");
            this.website = (String)activityDetailsJSON.get("WebsiteLink");
            this.cost = (double)activityDetailsJSON.get("Cost");
            this.openingTime = (long)activityDetailsJSON.get("OpeningTime");
            this.closingTime = (long)activityDetailsJSON.get("ClosingTime");



        JSONObject timesJSON = (JSONObject)activitiesJSON.get("TimeSlot");
        this.startHour = (double)timesJSON.get("StartTime");
        this.endHour = (double)timesJSON.get("EndTime");
    }
}

