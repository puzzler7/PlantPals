package com.tabs.tabs.database;

import com.tabs.tabs.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase {

    private static List<Plant> globalPlants = new ArrayList<>();

    public static List<Plant> getPlants() {
        return globalPlants;
    }
}
