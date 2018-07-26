package com.example.shdavido.tripplanner.Model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DayTrip implements Serializable {
    public List<ActivityDescription> activitiesList;

    public DayTrip(JSONObject dayJSON) {
        this.activitiesList = new ArrayList<ActivityDescription>();

        JSONArray activitiesArr = (JSONArray)dayJSON.get("SortedActivities");

        for(int i = 0; i < activitiesArr.size(); i++) {

            if ((boolean)((JSONObject) activitiesArr.get(i)).get("IsRestaurant")) {

            } else {
                if((boolean)((JSONObject) activitiesArr.get(i)).get("IsTraveling")) {
                    String fromActivity;

                    if(i == 0) fromActivity = null;
                    else fromActivity = (String)((JSONObject)((JSONObject)activitiesArr.get(i - 1)).get("Activity")).get("Name");

                    String toActivity = (String)((JSONObject)((JSONObject)activitiesArr.get(i + 1)).get("Activity")).get("Name");
                    TravelingSlot travel = new TravelingSlot((JSONObject)activitiesArr.get(i), fromActivity, toActivity);

                    this.activitiesList.add(travel);
                }
                else {
                    ActivityPoint activity = new ActivityPoint((JSONObject)activitiesArr.get(i));
                    this.activitiesList.add(activity);
                }
            }

        }
    }


}
