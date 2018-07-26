package com.example.shdavido.tripplanner.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.shdavido.tripplanner.CurrentTripFragment;
import com.example.shdavido.tripplanner.FutureTripsFragment;
import com.example.shdavido.tripplanner.PastTripsFragment;
import com.example.shdavido.tripplanner.R;

public class AllTripsActivity extends AppCompatActivity {

    public static final int START_NEW_TRIP_REQUEST_CODE = 9876;

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private PastTripsFragment pastTripsFragment;
    private CurrentTripFragment currentTripFragment;
    private FutureTripsFragment futureTripFragment;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);

        mainFrame = findViewById(R.id.main_frame);
        mainNav = findViewById(R.id.nav);
        floatingActionButton = findViewById(R.id.add_new_trip);


        pastTripsFragment = new PastTripsFragment();
        currentTripFragment = new CurrentTripFragment();
        futureTripFragment = new FutureTripsFragment();

        setFragment(currentTripFragment);
        mainNav.setSelectedItemId(R.id.current_trip);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.past_trips :
                        setFragment(pastTripsFragment);
                        return true;
                    case R.id.future_trips:
                        setFragment(futureTripFragment);
                        return true;
                    case R.id.current_trip:
                        setFragment(currentTripFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllTripsActivity.this, TripQuestionnaireActivity.class));
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
