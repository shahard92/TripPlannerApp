package com.example.shdavido.tripplanner.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shdavido.tripplanner.Model.AllTripsInfo;
import com.example.shdavido.tripplanner.Model.CommunicationWithBackeendHandler;
import com.example.shdavido.tripplanner.Model.GoogleMapsHandler;
import com.example.shdavido.tripplanner.Model.TripPlan;
import com.example.shdavido.tripplanner.Model.TripPlanRequest;
import com.example.shdavido.tripplanner.Model.VacationDetails;
import com.example.shdavido.tripplanner.R;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TripQuestionnaireActivity extends Activity {

    public static final String url = "https://tripplanner20180723014509.azurewebsites.net/api/trip";

    int numClicks = 0;
    private TripPlanRequest tripPlanRequest = new TripPlanRequest(LoginActivity.user, new VacationDetails(), new HashMap<String, Integer>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_questionnaire);

        final Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.cities_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        final TextView shortTextView = (TextView) findViewById(R.id.question);
        final TextView longTextView = (TextView) findViewById(R.id.longQuestion);
        final SeekBar bar = (SeekBar) findViewById(R.id.seekBar);
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setTextColor(Color.BLACK);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                numClicks = (numClicks % 12) + 1;
                switch (numClicks) {
                    case 1:
                        tripPlanRequest.getVacationDetails().setCity(staticSpinner.getSelectedItem().toString());
                        shortTextView.setText("How old are you?");
                        staticSpinner.setVisibility(View.GONE);
                        editText.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        LoginActivity.user.setAge(Integer.parseInt(editText.getText().toString()));
                        editText.setText("");
                        shortTextView.setText("For how many days you are planning to travel?");
                        break;
                    case 3:
                        tripPlanRequest.getVacationDetails().setDays(Integer.parseInt(editText.getText().toString()));
                        shortTextView.setText("Choose a hotel");
                        editText.setVisibility(View.GONE);
                        staticSpinner.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        tripPlanRequest.getVacationDetails()
                                .setSleepLocation(GoogleMapsHandler.extractLocationFromAPlace(staticSpinner.getSelectedItem().toString()));
                        longTextView.setText(R.string.preferenceTamplate);
                        staticSpinner.setVisibility(View.GONE);
                        bar.setVisibility(View.VISIBLE);
                        shortTextView.setText("Culinary Experiences");
                        break;
                    case 5:
                        tripPlanRequest.getPreferences().put("Culinary", bar.getProgress() + 1);
                        bar.setProgress(0);
                        shortTextView.setText("Parties");
                        break;
                    case 6:
                        tripPlanRequest.getPreferences().put("Party", bar.getProgress() + 1);
                        bar.setProgress(0);
                        shortTextView.setText("Historical Sites");
                        break;
                    case 7:
                        tripPlanRequest.getPreferences().put("History", bar.getProgress() + 1);
                        bar.setProgress(0);
                        shortTextView.setText("Museums");
                        break;
                    case 8:
                        tripPlanRequest.getPreferences().put("Museum", bar.getProgress() + 1);
                        bar.setProgress(0);
                        longTextView.setText("On a scale of 1-5 how physically capable are you?");
                        shortTextView.setVisibility(View.GONE);
                        break;
                    case 9:
                        tripPlanRequest.getPreferences().put("Walking", bar.getProgress() + 1);
                        bar.setProgress(0);
                        longTextView.setText("On a scale of 1-5 how interested are you in:");
                        shortTextView.setVisibility(View.VISIBLE);
                        shortTextView.setText("Beaches");

                        break;
                    case 10:
                        tripPlanRequest.getPreferences().put("Beach", bar.getProgress() + 1);
                        longTextView.setText("On a scale of 1-5 how demanding would you like the trip to be?");
                        shortTextView.setVisibility(View.GONE);
                        break;
                    case 11:
                        tripPlanRequest.getPreferences().put("Intencity", bar.getProgress() + 1);
                        shortTextView.setText("Shopping");
                        shortTextView.setVisibility(View.VISIBLE);
                        longTextView.setText("On a scale of 1-5 how interested are you in:");
                        break;
                    case 12:
                        tripPlanRequest.getPreferences().put("shopping", bar.getProgress() + 1);
                        finishQuestionnaire();
                        break;

                }

            }
        });
    }

    private void finishQuestionnaire() {

        TripPlanRequest tripPlanRequest = new TripPlanRequest(this.tripPlanRequest.getUser(), this.tripPlanRequest.getVacationDetails(), this.tripPlanRequest.getPreferences());

        JSONObject tripJSON = tripPlanRequest.getJsonRequestForTripPlan();
        String tripStr = tripJSON.toJSONString();

        CommunicationWithBackeendHandler communication = new CommunicationWithBackeendHandler(url,tripStr);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletionService<String> completionService =
                new ExecutorCompletionService<String>(executor);
        completionService.submit(communication);


        JSONParser parser = new JSONParser();
        JSONObject tripPlanJSON;
        TripPlan tripPlanResponse = null;
        try {
            tripPlanJSON = (JSONObject) parser.parse(completionService.take().get());
            tripPlanResponse = new TripPlan(tripPlanJSON);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (tripPlanResponse != null) {
            Intent intent = new Intent(TripQuestionnaireActivity.this, TripPlanActivity.class);
            intent.putExtra("TripPlan", tripPlanResponse);
            AllTripsInfo.futureTrips.put(tripPlanResponse, tripPlanRequest);
            AllTripsInfo.all.put(tripPlanResponse, tripPlanRequest);
            executor.shutdown();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Something went wrong with parsing response of backend", Toast.LENGTH_LONG).show();
        }
    }
}
