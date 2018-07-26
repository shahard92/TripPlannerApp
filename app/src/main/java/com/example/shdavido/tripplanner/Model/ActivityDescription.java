package com.example.shdavido.tripplanner.Model;


import java.io.Serializable;
import java.util.List;

public class ActivityDescription implements Serializable {

    public String title;
    public double startHour;
    public double endHour;

    public ActivityDescription() {

    }
    public ActivityDescription(String title, double startHour, double endHour) {
        this.title = title;
        this.startHour = startHour;
        this.endHour = endHour;
    }
    private static List<ActivityDescription> dummyActivities = null;


//    public static List<ActivityDescription> initializeActivities() {
//
//        if (dummyActivities == null) {
//            dummyActivities = new ArrayList<>();
//            dummyActivities.add(new ActivityDescription("breakfast at la-caffe", "08:30", "09:30"));
//            dummyActivities.add(new ActivityDescription("Transport to Louver moseum", "9:30", "10:00"));
//            dummyActivities.add(new ActivityDescription("Louver moseum", "10:00", "11:00"));
//            dummyActivities.add(new ActivityDescription("Transport to eiffel tower", "11:00", "11:30"));
//            dummyActivities.add(new ActivityDescription("eiffel tower", "11:30", "13:30"));
//            dummyActivities.add(new ActivityDescription("Transport to El-patio restaurant", "13:30", "14:00"));
//            dummyActivities.add(new ActivityDescription("lunch at El-patio restaurant", "14:00", "15:30"));
//        }
//        return dummyActivities;
//    }
}
