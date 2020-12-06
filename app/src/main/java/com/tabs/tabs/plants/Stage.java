package com.tabs.tabs.plants;

public enum Stage {
    SEED("SEED"),
    SPROUT("SPROUT"),
    SAPLING("SAPLING"),
    BUD("BUD"),
    FLOWER("FLOWER");

    private String stage;

    Stage(String stage) {
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }
}
