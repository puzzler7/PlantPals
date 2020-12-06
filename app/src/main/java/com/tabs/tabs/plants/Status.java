package com.tabs.tabs.plants;

public class Status {

//    EMPTY_HEALTHY("EMPTY", "HEALTHY"),
//    SEED_HEALTHY("SEED", "HEALTHY"),
//    SPROUT_HEALTHY("SPROUT", "HEALTHY"),
//    SPROUT_WILT("SPROUT", "WILT"),
//    SAPLING_HEALTHY("SAPLING", "HEALTHY"),
//    SAPLING_WILT("SAPLING", "WILT"),
//    BUD_HEALTHY("BUD", "HEALTHY"),
//    BUD_WILT("BUD", "WILT"),
//    BUD_SAD("BUD", "SAD"),
//    FLOWER_HEALTHY("FLOWER", "HEALTHY"),
//    FLOWER_WILT("FLOWER", "WILT"),
//    FLOWER_SAD("FLOWER", "SAD");

    private Stage stage;
    private Health health;

    public Status(String stage, String health) {
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
