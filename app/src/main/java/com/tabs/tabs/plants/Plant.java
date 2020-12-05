package com.tabs.tabs.plants;

public class Plant {

    private PlantType myPlantType;
    private Status myStatus;
    private Profile myProfile;
    private long lastWater = 0;
    private int numberOfWaters = 0;

    private static long SIXTEEN_HOURS_MILLI = 57600000;
    private static long FOURTEEN_DAYS_MILLI = 1209600000;
    private static int TO_SPROUT = 2;
    private static int TO_SAPLING = 5;
    private static int TO_BUD = 7;
    private static int TO_FLOWER = 14;

    public Plant() {

    }

    public void water() {
        long currentWater = System.currentTimeMillis();
        if(currentWater - lastWater < SIXTEEN_HOURS_MILLI) return;
        else {
            lastWater = currentWater;
            numberOfWaters++;
            checkStatusUpdate();
        }
        return;
    }

    public void checkDecay() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastWater > FOURTEEN_DAYS_MILLI) {
            if(myStatus.getHealth() == Health.HEALTHY) {
                myStatus.setHealth(Health.WILT);
                numberOfWaters = 0;
            } else if(myStatus.getHealth() == Health.WILT) {
                myStatus.setHealth(Health.SAD);
                numberOfWaters = 0;
            }
        }
    }

    private void checkStatusUpdate() {
        if(myStatus.getHealth() == Health.SAD) {
            if(numberOfWaters > 0) {
                numberOfWaters = 0;
                myStatus.setHealth(Health.WILT);
            }
        } else if(myStatus.getHealth() == Health.WILT)  {
            if(numberOfWaters > 0) {
                numberOfWaters = 0;
                myStatus.setHealth(Health.HEALTHY);
            }
        } else {
            checkIterateGrowth();
        }
        return;
    }

    private void checkIterateGrowth() {
        switch(myStatus.getStage()) {
            case SEED:
                if (numberOfWaters >= TO_SPROUT) {
                    myStatus.setStage(Stage.SPROUT);
                    numberOfWaters = 0;
                }
                break;
            case SPROUT:
                if (numberOfWaters >= TO_SAPLING) {
                    myStatus.setStage(Stage.SAPLING);
                    numberOfWaters = 0;
                }
                break;
            case SAPLING:
                if(numberOfWaters >= TO_BUD) {
                    myStatus.setStage(Stage.BUD);
                    numberOfWaters = 0;
                }
                break;
            case BUD:
                if(numberOfWaters >= TO_FLOWER) {
                    myStatus.setStage(Stage.FLOWER);
                    numberOfWaters = 0;
                }
                break;
            case FLOWER:
            default:
                break;
        }
        return;
    }
}
