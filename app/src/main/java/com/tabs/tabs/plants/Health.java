package com.tabs.tabs.plants;

public enum Health {
    WILT("WILT"),
    SAD("SAD"),
    HEALTHY("HEALTHY");

    private String health;

    Health(String health) {
        this.health = health;
    }

    public String getHealth() {
        return health;
    }
}
