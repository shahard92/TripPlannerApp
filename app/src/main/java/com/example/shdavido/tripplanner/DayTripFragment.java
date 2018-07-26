package com.example.shdavido.tripplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shdavido.tripplanner.Model.ActivityDescription;
import com.example.shdavido.tripplanner.Model.AdapterForDayTrip;

import java.util.ArrayList;
import java.util.List;

public class DayTripFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ActivityDescription> activityDescriptions;
    List<ActivityDescription> activitiesForTheDay = new ArrayList<>();

    public void setActivities(List<ActivityDescription> activitiesForTheDay) {
        this.activitiesForTheDay = activitiesForTheDay;
    }



    public DayTripFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_day_trip, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.day_trip_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AdapterForDayTrip(activitiesForTheDay, getContext());
        mRecyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }
}
