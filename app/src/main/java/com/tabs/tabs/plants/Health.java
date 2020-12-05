package com.tabs.tabs.plants;

public enum Health {
    WILT("wilt"),
    SAD("sad"),
    HEALTHY("healthy");

    private String health;

    Health(String health) {
        this.health = health;
    }

    public String getHealth() {
        return health;
    }
}
