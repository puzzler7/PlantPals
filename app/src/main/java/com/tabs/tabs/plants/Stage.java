package com.tabs.tabs.plants;

public enum Stage {
    SEED("seed"),
    SPROUT("sprout"),
    SAPLING("sapling"),
    BUD("bud"),
    FLOWER("flower");

    private String stage;

    Stage(String stage) {
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }
}
