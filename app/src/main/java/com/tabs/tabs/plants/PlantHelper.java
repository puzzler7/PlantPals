package com.tabs.tabs.plants;

import com.tabs.tabs.database.PlantModel;

import java.util.Arrays;

public class PlantHelper {

    /*
    public Plant makePlant(PlantModel pm) {

        uid = pm.id;
        myStatus = new Status(pm.status, pm.stage);

        myProfile = makeProfile(pm.name, pm.nickname, pm.notes);
        lastWater = pm.last_watered;
        whenCreated = pm.creation;
        numberOfWaters = pm.droplets;

        return this;
    }
     */

    public static Plant makePlant(PlantModel pm) {
        return new Plant(pm.id, pm.last_watered_in_days, PlantType.valueOf(pm.type), new Status(pm.stage, pm.health), makeProfile(pm.name, pm.nickname, pm.notes), pm.creation, pm.last_watered, pm.droplets);
    }

    private static Profile makeProfile(String name, String subtitle, String notes) {
        return new Profile(name, subtitle, Arrays.asList(notes.split("___")));
    }
}
