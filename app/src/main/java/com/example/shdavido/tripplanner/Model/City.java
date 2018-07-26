package com.example.shdavido.tripplanner.Model;

import com.example.shdavido.tripplanner.R;

import java.util.ArrayList;
import java.util.List;

public class City {

    public String name;
    public String description;
    public int photoId;

    public City(String name, String description, int photoId) {
        this.name = name;
        this.description = description;
        this.photoId = photoId;
    }

    private static List<City> pastCities = null;
    private static List<City>futureCities = null;

}
