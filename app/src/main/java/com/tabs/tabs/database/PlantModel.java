package com.tabs.tabs.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "plants")
public class PlantModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "type", defaultValue = "POPPY")
    public String type;

    @ColumnInfo(name = "stage", defaultValue = "SEED")
    public String stage;

    @ColumnInfo(name = "health", defaultValue = "HEALTHY")
    public String health;

    @ColumnInfo(name = "droplets")//, defaultValue = 0)
    public int droplets;

    @ColumnInfo(name = "notes", defaultValue = "")
    public String notes;

    @ColumnInfo(name = "creation_datetime")
    public long creation;

    @ColumnInfo(name = "last_watered_datetime")
    public long last_watered;

    @ColumnInfo(name = "days_since_last_watered")
    public int last_watered_in_days;
}

