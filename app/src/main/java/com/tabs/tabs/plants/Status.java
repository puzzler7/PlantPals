package com.tabs.tabs.plants;

public enum Status {

    SEED("SEED", "HEALTHY"),
    SPROUT("SPROUT", "HEALTHY"),
    SPROUT_WILT("SPROUT", "WILT"),
    SAPLING("SAPLING", "HEALTHY"),
    SAPLING_WILT("SAPLING", "WILT"),
    BUD("BUD", "HEALTHY"),
    BUD_WILT("BUD", "WILT"),
    BUD_SAD("BUD", "SAD"),
    FLOWER("FLOWER", "HEALTHY"),
    FLOWER_WILT("FLOWER", "WILT"),
    FLOWER_SAD("FLOWER", "SAD");

    private Stage stage;
    private Health health;

    Status(String stage, String health) {
        this.stage = Stage.valueOf(stage);
        this.health = Health.valueOf(health);
    }

    public String getFilename() {
        return stage.getStage() + "_" + health.getHealth();
    }

    public Health getHealth() {
        return health;
    }

    public Stage getStage() {
        return stage;
    }

    public void setHealth(Health h) {
        health = h;
    }

    public void setStage(Stage s) {
        stage = s;
    }

    /*
    SEED("seed"),
    SPROUT("sprout"),
    SPROUT_WILT("sprout_wilt"),
    SAPLING("sapling"),
    SAPLING_WILT("sapling_wilt"),
    BUD("bud"),
    BUD_WILT("bud_wilt"),
    BUD_SAD("bud_sad"),
    FLOWER("flower"),
    FLOWER_WILT("flower_wilt"),
    FLOWER_SAD("flower_sad");

    private String filename;

    Status(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public Health getHealth() {
        if(filename.contains("wilt")) return Health.WILT;
        else if(filename.contains("sad")) return Health.SAD;
        else return Health.HEALTHY;
    }
     */
}
