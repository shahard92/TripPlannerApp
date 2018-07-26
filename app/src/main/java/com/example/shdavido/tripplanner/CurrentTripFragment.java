package com.example.shdavido.tripplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shdavido.tripplanner.Model.AllTripsInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTripFragment extends Fragment {




    public Button endTripButton;

    public CurrentTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_trip, container, false);
        endTripButton = rootView.findViewById(R.id.current_trip_end_trip);
        endTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllTripsInfo.pastTrips.put(AllTripsInfo.currentTrip, AllTripsInfo.all.get(AllTripsInfo.currentTrip));
                AllTripsInfo.currentTrip = null;
            }
        });

        if (AllTripsInfo.currentTrip != null) {

            ImageView cityImage = rootView.findViewById(R.id.current_trip_city_photo);
            TextView cityTitle = rootView.findViewById(R.id.current_trip_city_name);
            TextView cityDescription = rootView.findViewById(R.id.current_trip_city_description);
            cityImage.setImageResource(R.drawable.berlin);
            cityTitle.setText("Berlin");
            cityDescription.setText(FutureTripsFragment.cityDescriptionDummy[2]);
        }
        return inflater.inflate(R.layout.fragment_current_trip, container, false);

    }

}
