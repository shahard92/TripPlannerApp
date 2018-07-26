package com.example.shdavido.tripplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.shdavido.tripplanner.DayTripFragment;
import com.example.shdavido.tripplanner.Model.ActivityDescription;
import com.example.shdavido.tripplanner.Model.AllTripsInfo;
import com.example.shdavido.tripplanner.Model.DayTrip;
import com.example.shdavido.tripplanner.Model.OnSwipeTouchListener;
import com.example.shdavido.tripplanner.Model.TripPlan;
import com.example.shdavido.tripplanner.R;

import java.util.ArrayList;
import java.util.List;


public class TripPlanActivity extends AppCompatActivity {

    private BottomNavigationView mainNav;
    private int currentDay = 1;
    private TripPlan plannedTrip;
    private FrameLayout mainFrame;
    private List<DayTripFragment> dayTripFragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_plan);

        mainNav = findViewById(R.id.day_items);
        mainFrame = findViewById(R.id.main_trip_plan_frame);

        mainNav.setSelectedItemId(R.id.current_day);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.previous_day :
                        previousDay();
                        return true;
                    case R.id.current_day:
                        return true;
                    case R.id.next_day:
                        nextDay();
                        return true;
                    case R.id.start_trip:
                        startTrip();
                        return true;
                    default:
                        return false;
                }
            }
        });


        mainFrame.setOnTouchListener(new OnSwipeTouchListener(TripPlanActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(TripPlanActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                previousDay();
            }
            public void onSwipeLeft() {
                nextDay();
            }
            public void onSwipeBottom() {
                Toast.makeText(TripPlanActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        Intent intent = getIntent();
        plannedTrip = (TripPlan) intent.getSerializableExtra("TripPlan");
        for (int i = 0; i < plannedTrip.dayTrips.size(); i++) {
            DayTripFragment dayTripFragment = new DayTripFragment();
            dayTripFragment.setActivities(plannedTrip.dayTrips.get(i).activitiesList);
            dayTripFragmentList.add(dayTripFragment);
            if (i == 0) {
                setFragment(dayTripFragmentList.get(0));
            }
        }

    }

    private void startTrip() {
        AllTripsInfo.futureTrips.remove(plannedTrip);
        AllTripsInfo.currentTrip = plannedTrip;
    }


    private void previousDay() {

        if (currentDay == 1) {
            return;
        }
        currentDay--;
        mainNav.getMenu().getItem(1).setTitle("Day" + currentDay);
        setFragment(dayTripFragmentList.get(currentDay - 1));
    }

    private void nextDay() {

        if (currentDay == plannedTrip.dayTrips.size()) {
            return;
        }
        currentDay++;
        mainNav.getMenu().getItem(1).setTitle("Day" + currentDay);
        setFragment(dayTripFragmentList.get(currentDay - 1));
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_trip_plan_frame, fragment);
        fragmentTransaction.commit();
    }
}
