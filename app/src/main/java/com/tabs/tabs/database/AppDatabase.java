package com.tabs.tabs.database;


// method to set Plant notes
// method to increment droplets by N
// method to decide status
// method to calc days since last water
// method to init new plant (e.g. set notes as "" by default)

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PlantModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlantDao PlantDao();
}
/*
    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "plant_db").build();
*/