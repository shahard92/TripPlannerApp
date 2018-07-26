package com.example.shdavido.tripplanner.Model;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TripPlan implements Serializable {
    public List<DayTrip> dayTrips;

    public TripPlan(JSONObject tripJSON) {
        this.dayTrips = new ArrayList<DayTrip>();
        parseRes(tripJSON);
    }

    public void parseRes(JSONObject tripJSON) {
        //parse days
        JSONArray daysJSON = null;

        daysJSON = (JSONArray) tripJSON.get("DayTrips");
        for(int i = 0; i < daysJSON.size(); i++) {
            DayTrip dayTrip = new DayTrip((JSONObject)daysJSON.get(i));
            this.dayTrips.add(dayTrip);
        }



    }
}
