package com.example.shdavido.tripplanner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shdavido.tripplanner.Model.AdapterForPastCities;
import com.example.shdavido.tripplanner.Model.AllTripsInfo;
import com.example.shdavido.tripplanner.Model.City;

import java.util.List;

import static com.example.shdavido.tripplanner.FutureTripsFragment.buildCitiesFromVacations;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastTripsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PastTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_past_trips, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_of_past_trips);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AdapterForPastCities(buildCitiesFromVacations(AllTripsInfo.pastTrips));
        mRecyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }



}
