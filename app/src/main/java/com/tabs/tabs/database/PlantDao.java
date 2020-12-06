package com.tabs.tabs.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlantDao {
    @Query("SELECT * FROM plants")
    List<PlantModel> getAll();

    @Query("DELETE FROM plants")
    void deleteAll();

    // TODO: maybe needs to be an int
    @Insert
    void insert(PlantModel plant);

    @Delete
    void delete(PlantModel plant);

    @Update
    void update(PlantModel plant);
}
