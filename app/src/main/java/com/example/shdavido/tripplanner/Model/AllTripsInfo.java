package com.example.shdavido.tripplanner.Model;

import java.util.HashMap;
import java.util.Map;

public class AllTripsInfo {

    public static Map<TripPlan, TripPlanRequest> futureTrips = new HashMap<TripPlan, TripPlanRequest>();
    public static Map<TripPlan, TripPlanRequest> pastTrips = new HashMap<TripPlan, TripPlanRequest>();
    public static TripPlan currentTrip = null;

    public static Map<TripPlan, TripPlanRequest> all = new HashMap<TripPlan, TripPlanRequest>();
}
