package com.example.shdavido.tripplanner.Model;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shdavido.tripplanner.R;
import com.github.vipulasri.timelineview.TimelineView;
import java.util.List;

public class AdapterForDayTrip extends RecyclerView.Adapter<AdapterForDayTrip.DayTripViewHolder> {


    private List<ActivityDescription> activitiesForTheDay;
    Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterForDayTrip(List<ActivityDescription> activitiesForTheDay, Context context) {
        this.activitiesForTheDay = activitiesForTheDay;
        this.context = context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class DayTripViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TimelineView timelineView;
        CardView cardView;
        AppCompatTextView hours;
        TextView activityTitle;
        ImageView checkMark;
        public DayTripViewHolder(View itemView) {
            super(itemView);
            timelineView = (TimelineView)itemView.findViewById(R.id.time_marker);
            cardView = (CardView)itemView.findViewById(R.id.card_of_activity);
            hours = (AppCompatTextView)itemView.findViewById(R.id.time_gap);
            activityTitle = (TextView)itemView.findViewById(R.id.text_timeline_title);
            checkMark = (ImageView)itemView.findViewById(R.id.completed_step);
        }

    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final AdapterForDayTrip.DayTripViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ActivityDescription relevantActivity = activitiesForTheDay.get(position);
        holder.activityTitle.setText(relevantActivity.title);
        String timeSlot = (relevantActivity.startHour + " - " + relevantActivity.endHour);
        holder.hours.setText(timeSlot);
        holder.checkMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.checkMark.setImageResource(R.drawable.baseline_check_circle_black_18dp);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return activitiesForTheDay.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterForDayTrip.DayTripViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                                  int viewType) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_timeline, viewGroup, false);
        AdapterForDayTrip.DayTripViewHolder pvh = new AdapterForDayTrip.DayTripViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
