package com.tabs.tabs.plants;

public enum PlantType {
    POPPY("poppy"),
    CACTUS("cactus"),
    BAMBOO("bamboo"),
    BONSAI("bonsai");

    private String plantType;

    PlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantType() {
        return plantType;
    }
}
