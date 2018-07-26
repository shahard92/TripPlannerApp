package com.example.shdavido.tripplanner.Model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shdavido.tripplanner.R;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAdapterForCities extends RecyclerView.Adapter<AdapterForPastCities.CityViewHolder> {


    protected List<City> cities;

    public AbstractAdapterForCities(List<City> givenCities) {
        cities = givenCities;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CityViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCardView;
        TextView cityName;
        TextView cityDescription;
        ImageView cityPhoto;
        public CityViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.card_view);
            cityName = (TextView)itemView.findViewById(R.id.city_name);
            cityDescription = (TextView)itemView.findViewById(R.id.city_description);
            cityDescription.setMovementMethod(new ScrollingMovementMethod());
            cityPhoto = (ImageView)itemView.findViewById(R.id.city_photo);
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        onBindViewHolder(holder, position, cities);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cities.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterForPastCities.CityViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                                  int viewType) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);
        AdapterForPastCities.CityViewHolder pvh = new AdapterForPastCities.CityViewHolder(v);
        return pvh;
    }

    protected void onBindViewHolder(AdapterForPastCities.CityViewHolder holder, int position, List<City> cities) {

        City relevantCity = cities.get(position);
        holder.cityPhoto.setImageResource(relevantCity.photoId);
        holder.cityDescription.setText(relevantCity.description);
        holder.cityName.setText(relevantCity.name);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
