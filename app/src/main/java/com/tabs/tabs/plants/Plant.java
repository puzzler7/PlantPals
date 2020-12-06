package com.tabs.tabs.plants;

import android.os.Parcel;
import android.os.Parcelable;

import com.tabs.tabs.database.PlantModel;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Plant implements Parcelable {

    private int uid;
    private PlantType myPlantType;
    private Status myStatus;
    private Profile myProfile;
    private long whenCreated = 0;
    private long lastWater = 0;
    private int numberOfWaters = 0;

    // For Debug Purposes
    private static int days = 0;

    private static long SIXTEEN_HOURS_MILLI = 57600000;
    private static long FOURTEEN_DAYS_MILLI = 1209600000;
    private static long ONE_DAY_MILLI = 86400000;
    private static int TO_SPROUT = 2;
    private static int TO_SAPLING = 5;
    private static int TO_BUD = 7;
    private static int TO_FLOWER = 14;

    // TODO: INIT CONSTRUCTOR VS. LOAD/RECREATE CONSTRUCTOR

    // CONSTRUCTOR FROM THE DATABASE


    public Plant(int id, int day_count, PlantType pt, Status s, Profile p, long createdAt, long lastWater, int numberOfWaters) {
        uid = id;
        days = day_count;
        myPlantType = pt;
        myStatus = s;
        myProfile = p;
        whenCreated = createdAt;
        this.lastWater = lastWater;
        this.numberOfWaters = numberOfWaters;
    }

    public Plant(PlantType pt, Status s, Profile p) {
        myPlantType = pt;
        myStatus = s;
        myProfile = p;
        whenCreated = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        makeUID();
        System.out.println(p.getName() + ": " + s.getHealth());
    }

    public Plant() {
        this(PlantType.POPPY, new Status(Stage.EMPTY.getStage(), Health.HEALTHY.getHealth()), new Profile());
    }

    public Plant makePlant(PlantModel pm) {

        uid = pm.id;
        myStatus = new Status(pm.stage, pm.health);
        days = pm.last_watered_in_days;

        myProfile = makeProfile(pm.name, pm.nickname, pm.notes);
        lastWater = pm.last_watered;
        whenCreated = pm.creation;
        numberOfWaters = pm.droplets;

        return this;
    }

    public PlantModel makePlantModel() {
        PlantModel pm = new PlantModel();
        pm.id = uid;
        pm.last_watered_in_days = days;
        pm.type = myPlantType.getPlantType();
        pm.health = myStatus.getHealth().getHealth();
        pm.stage = myStatus.getStage().getStage();
        pm.name = myProfile.getName();
        pm.nickname = myProfile.getSubtitle();
        pm.notes = "";
        for(String s : myProfile.getNotes()) {
            pm.notes += "___" + s;
        }
        pm.notes = pm.notes.substring(3);
        pm.last_watered = lastWater;
        pm.creation = whenCreated;
        pm.droplets = numberOfWaters;
        return pm;
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

        uid = in.readInt();
        myPlantType = PlantType.valueOf(in.readString());
//        myStatus = new Status(Stage.SEED.getStage(), Health.HEALTHY.getHealth());
        Stage newStage = Stage.valueOf(in.readString());
        Health newHealth = Health.valueOf(in.readString());
//        myStatus.setStage(newStage);
//        myStatus.setHealth(newHealth);
        myStatus = new Status(newStage.getStage(), newHealth.getHealth());
//        System.out.println("myStatus = " + myStatus.getFilename());

        myProfile = Profile.readProfile(in.readString()); //___ is grrrr
        whenCreated = in.readLong();
        lastWater = in.readLong();
        numberOfWaters = in.readInt();
//        System.out.println("\tread from parcel = " + getFileName());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(myPlantType.getPlantType());
        dest.writeString(myStatus.getStage().getStage());
        dest.writeString(myStatus.getHealth().getHealth());
        dest.writeString(myProfile.writeProfile());
        dest.writeLong(whenCreated);
        dest.writeLong(lastWater);
        dest.writeInt(numberOfWaters);
//        System.out.println("\twrite to parcel: " + getFileName());
    }

    @Override
    public int describeContents() {
        return 0;
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
        System.out.print("---thanks for the water!---");
        long currentWater = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        if(currentWater - lastWater < SIXTEEN_HOURS_MILLI || myStatus.getStage() == Stage.EMPTY) return;
        else {
            System.out.println("\tI liked it!---");
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
        if(currentTime - lastWater > FOURTEEN_DAYS_MILLI && myStatus.getStage() != Stage.SEED && myStatus.getStage() != Stage.EMPTY) {
            if (myStatus.getHealth() == Health.HEALTHY && lastWater != 0) {
                System.out.println("***BECOME WILT***");
                System.out.println(currentTime + "," + lastWater);
                myStatus.setHealth(Health.WILT);
                // lastWater = System.currentTimeMillis() + days * ONE_DAY_MILLI;
                numberOfWaters = 0;
            }
        }
        if(currentTime - lastWater > FOURTEEN_DAYS_MILLI * 2 && myStatus.getStage() != Stage.SEED && myStatus.getStage() != Stage.EMPTY){
            if (myStatus.getHealth() == Health.WILT && lastWater != 0) {
                if (myStatus.getStage() == Stage.BUD || myStatus.getStage() == Stage.FLOWER) {
                    System.out.println("***BECOME SAD***");
                    System.out.println(currentTime + "," + lastWater);
                    myStatus.setHealth(Health.SAD);
                    numberOfWaters = 0;
                }
            }
        }
    }

    public long getDaysSinceWatered() {
        long currentTime = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        return (currentTime - lastWater)/ONE_DAY_MILLI;
    }

    public long getMillsSinceWatered() {
        long currentTime = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        return (currentTime - lastWater);
    }

    public long getDaysOld() {
        long currentTime = System.currentTimeMillis() + days * ONE_DAY_MILLI;
        return (currentTime - whenCreated)/ONE_DAY_MILLI;
//        return(System.currentTimeMillis() - currPlant[0].getWhenCreated()) / 86400000 + currPlant[0].getDays()
    }

    public Status getStatus() {
        return myStatus;
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
//        return myPlantType.getPlantType() + "." + myStatus.getFilename();
        return myStatus.getFilename().toLowerCase();
    }

    public long getLastWater() {
        return lastWater - (days * 86400000);
    }

    public Profile getProfile() {
        return myProfile;
    }

    public int getUID() {
        return uid;
    }

    public long getWhenCreated() {
        return whenCreated;
    }

    private void checkStatusUpdate() {
        if (myStatus.getHealth() == Health.SAD) {
            if (numberOfWaters > 0) {
                numberOfWaters = 0;
                System.out.println("***BECOME WILT***");
                myStatus.setHealth(Health.WILT);
            }
        } else if (myStatus.getHealth() == Health.WILT) {
            if (numberOfWaters > 0) {
                numberOfWaters = 0;
                System.out.println("***BECOME HEALTHY***");
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
                    System.out.println("I'M A BUD NOW");
                    myStatus.setStage(Stage.BUD);
                    numberOfWaters = 0;
                }
                break;
            case BUD:
                if (numberOfWaters >= TO_FLOWER) {
                    System.out.println("I'M A FLOWER NOW");
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

    private void makeUID() {
        uid = (int) (UUID.randomUUID().getLeastSignificantBits() & 0x00000000FFFFFFFF);
    }

    private Profile makeProfile(String name, String subtitle, String notes) {
        return new Profile(name, subtitle, Arrays.asList(notes.split("___")));
    }
}
