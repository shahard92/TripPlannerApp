package com.example.shdavido.tripplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shdavido.tripplanner.Model.AdapterForFutureCities;
import com.example.shdavido.tripplanner.Model.AllTripsInfo;
import com.example.shdavido.tripplanner.Model.City;
import com.example.shdavido.tripplanner.Model.TripPlan;
import com.example.shdavido.tripplanner.Model.TripPlanRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FutureTripsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static String[] cityDescriptionDummy = { "This is a great city, a lot of things to do, great culinary experience",
    "This city has a lot of historical places and have a great culture, in the west part there are many pubs and bars during the evening",
    "Very romantic city, great place for a honey moon or a romantic vacation, great restaurants, good beaches"};

    public FutureTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_future_trips, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_of_future_trips);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AdapterForFutureCities(buildCitiesFromVacations(AllTripsInfo.futureTrips));
        mRecyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }

    public static List<City> buildCitiesFromVacations(Map<TripPlan, TripPlanRequest> trips) {

        List<City> result = new ArrayList<>();
        Iterator it = trips.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TripPlan tripPlan = (TripPlan) pair.getKey();
            TripPlanRequest tripPlanRequest = (TripPlanRequest) pair.getValue();
            result.add(new City(tripPlanRequest.getVacationDetails().getCity(), peakDummyDescription(), peakDummyCity()));
            it.remove(); // avoids a ConcurrentModificationException
        }
        return result;
    }

    private static int peakDummyCity() {

        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        switch (randomNum) {
            case 0:
                return R.drawable.berlin;
            case 1:
                return R.drawable.telaviv;
            case 2:
                return R.drawable.newyork;
        }
        return R.drawable.newyork;
    }

    private static String peakDummyDescription() {

        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        return cityDescriptionDummy[randomNum];
    }


}
