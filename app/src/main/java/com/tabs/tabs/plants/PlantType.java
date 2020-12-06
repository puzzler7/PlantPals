package com.tabs.tabs.plants;

public enum PlantType {
    POPPY("POPPY"),
    CACTUS("CACTUS"),
    BAMBOO("BAMBOO"),
    BONSAI("BONSAI");

    private String plantType;

    PlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantType() {
        return plantType;
    }
}
