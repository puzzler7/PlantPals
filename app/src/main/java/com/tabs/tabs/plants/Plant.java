package com.tabs.tabs.plants;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable {

    private PlantType myPlantType;
    private Status myStatus;
    private Profile myProfile;
    private static int days = 0;
    private long lastWater = 0;
    private int numberOfWaters = 0;

    private static long SIXTEEN_HOURS_MILLI = 57600000;
    private static long FOURTEEN_DAYS_MILLI = 1209600000;
    private static long ONE_DAY_MILLI = 86400000;
    private static int TO_SPROUT = 2;
    private static int TO_SAPLING = 5;
    private static int TO_BUD = 7;
    private static int TO_FLOWER = 14;

    public Plant(PlantType pt, Status s, Profile p) {
        myPlantType = pt;
        myStatus = s;
        myProfile = p;
    }

    public Plant() {
        this(PlantType.POPPY, Status.EMPTY, new Profile());
    }

    public static void setDays(int d) {
        days = d;
    }

    public static int getDays() {
        return days;
    }

    public int getNumberOfWaters() {
        return numberOfWaters;
    }

    public void setNumberOfWaters(int numWaters) {
        numberOfWaters = numWaters;
    }

    //////Parcel
    protected Plant(Parcel in) {
        /**
         * dest.writeString(myPlantType.getPlantType());
         * dest.writeString(myStatus.getStage().getStage());
         * dest.writeString(myStatus.getHealth().getHealth());
         * dest.writeString(myProfile.writeProfile());
         */
        myPlantType = PlantType.valueOf(in.readString());
//        myStatus = Status.valueOf(in.readString()); //fix if bad
        myStatus = Status.SEED;
        Stage newStage = Stage.valueOf(in.readString());
        Health newHealth = Health.valueOf(in.readString());
        myStatus.setStage(newStage);
        myStatus.setHealth(newHealth);

        myProfile = Profile.readProfile(in.readString()); //___ is grrrr
        lastWater = in.readLong();
        numberOfWaters = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(myPlantType.getPlantType());
//        dest.writeString(myStatus.name());
        dest.writeString(myStatus.getStage().getStage());
        dest.writeString(myStatus.getHealth().getHealth());
        dest.writeString(myProfile.writeProfile());
        dest.writeLong(lastWater);
        dest.writeInt(numberOfWaters);
    }

    @Override
    public int describeContents() {
        return 0; //TODO: ???
    }

    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };
    //////Parcel

    /**
     * Call when water bucket is clicked for the plant
     */
    public void water() {
        long currentWater = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        if(currentWater - lastWater < SIXTEEN_HOURS_MILLI || myStatus == Status.EMPTY) return;
        else {
            lastWater = currentWater;
            numberOfWaters++;
            checkStatusUpdate();
        }
        return;
    }

    /**
     * Call when app is opened or on a timer to update plant statuses for decay
     */
    public void checkDecay() {
        long currentTime = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        if(currentTime - lastWater > FOURTEEN_DAYS_MILLI && myStatus.getStage() != Stage.SEED && myStatus != Status.EMPTY) {
            if(myStatus.getHealth() == Health.HEALTHY) {
                myStatus.setHealth(Health.WILT);
                numberOfWaters = 0;
            } else if (myStatus.getHealth() == Health.WILT) {
                if (myStatus.getStage() == Stage.BUD || myStatus.getStage() == Stage.FLOWER) {
                    myStatus.setHealth(Health.SAD);
                    numberOfWaters = 0;
                }
            }
        }
    }

    public Status getStatus() {
        return getStatus();
    }

    public Health getHealth() {
        return myStatus.getHealth();
    }

    public Stage getStage() {
        return myStatus.getStage();
    }

    public PlantType getPlantType() {
        return myPlantType;
    }

    public String getFileName() {
        return myPlantType.getPlantType() + "." + myStatus.getFilename();
    }

    public long getLastWater() {
        return lastWater;
    }

    public Profile getProfile() {
        return myProfile;
    }

    private void checkStatusUpdate() {
        if (myStatus.getHealth() == Health.SAD) {
            if (numberOfWaters > 0) {
                numberOfWaters = 0;
                myStatus.setHealth(Health.WILT);
            }
        } else if (myStatus.getHealth() == Health.WILT) {
            if (numberOfWaters > 0) {
                numberOfWaters = 0;
                myStatus.setHealth(Health.HEALTHY);
            }
        } else {
            checkIterateGrowth();
        }
        return;
    }

    private void checkIterateGrowth() {
        switch (myStatus.getStage()) {
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
                if (numberOfWaters >= TO_BUD) {
                    myStatus.setStage(Stage.BUD);
                    numberOfWaters = 0;
                }
                break;
            case BUD:
                if (numberOfWaters >= TO_FLOWER) {
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
