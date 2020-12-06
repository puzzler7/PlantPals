package com.tabs.tabs.gui;

//import android.content.context;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tabs.tabs.R;
import com.tabs.tabs.plants.Plant;

import java.util.List;

public class BobLogic {

    private static int pageNum; // 1 if on main garden, 2 if on focus
    private static int convoNum;
    private static Context context;
    private static View mainBobText;
    private static View focusBobText;
    private static TextView bobSimple;
    private static Button bobOption1;
    private static Button bobOption2;
    private static Button bobOption3;
    private static Button bobOption4;

    private static TextView focusSimpleText;
    private static Button focusConfirm;
    private static Button focusCancel;

    private static List<Plant> myPlantList;

    /**
     * 0: silent
     * 1:
     * 2:
     * 3:
     */

    public BobLogic(Context con, View mainBobText, TextView bobSimp, Button bob1, Button bob2, Button bob3, Button bob4, List<Plant> plantList) {
        context = con;
        pageNum = 1;
        convoNum = 0;
        this.mainBobText = mainBobText;
        this.myPlantList = plantList;
        bobSimple = bobSimp;
        bobOption1 = bob1;
        bobOption2 = bob2;
        bobOption3 = bob3;
        bobOption4 = bob4;
        mainDisappear();
    }

    public static void mainPageSetBob() {
        pageNum = 1;
        if (convoNum == 0) {
            convoNum = 1;
            introOptions();
        } else {
            convoNum = 0;
            mainDisappear();
        }
    }

    public static void dialogTreeUpdate(int choice) {
        if (choice == 11) {
            convoNum = 12;
            setSimpleText("");
        } else if (choice == 16) {
            convoNum = 17;
            setSimpleText("");
        } else if (choice == 21) {
            convoNum = 22;
            setSimpleText("");
        } else if (choice == 26) {
            convoNum = 27;
            setSimpleText("");
        } else {
            convoNum = 0;
            mainDisappear();
        }
    }

    private static void mainDisappear() {
        mainBobText.setVisibility(View.GONE); //change to entire box
        bobSimple.setVisibility(View.GONE);
        bobOption1.setVisibility(View.GONE);
        bobOption2.setVisibility(View.GONE);
        bobOption3.setVisibility(View.GONE);
        bobOption4.setVisibility(View.GONE);
    }

    public static void focusPageSetBob(int choice, String fill) {
        System.out.println("CLICKED " + choice + "" + convoNum);
        pageNum = 2;
        if ((convoNum != 0 || choice == 0) && !(choice == 156 || choice == 161)) {
            convoNum = 0;
            focusSimpleText.setVisibility(View.GONE);
            focusBobText.setVisibility(View.GONE);
            focusConfirm.setVisibility(View.GONE);
            focusCancel.setVisibility(View.GONE);
        } else {
            convoNum = choice;
            if (convoNum == 101 || convoNum == 111) {
                setSimpleText(fill);
            } else if (convoNum == 151) {
                setFocusTree();
            } else if (convoNum == 156 || convoNum == 161) {
                setSimpleText("");
            } else if (convoNum == 171) {
                setSimpleText("");
            }
        }
    }

    public static void setFocusBobText(View focusText, TextView focusSimple, Button focusYes, Button focusNo) {
        focusBobText = focusText;
        focusSimpleText = focusSimple;
        focusConfirm = focusYes;
        focusCancel = focusNo;
        focusBobText.setVisibility(View.GONE);
        convoNum = 0;
    }

    public static void introOptions() {
        mainBobText.setVisibility(View.VISIBLE); //change to entire box
        bobSimple.setVisibility(View.VISIBLE);
        bobOption1.setVisibility(View.VISIBLE);
        bobOption2.setVisibility(View.VISIBLE);
        bobOption3.setVisibility(View.VISIBLE);
        bobOption4.setVisibility(View.VISIBLE);
        bobSimple.setText(context.getString(R.string.bob_introduction));
        bobOption1.setText(context.getString(R.string.bob_option_addgrow_plant));
        bobOption2.setText(context.getString(R.string.bob_option_wilt));
        bobOption3.setText(context.getString(R.string.bob_option_del_plant));
        bobOption4.setText(context.getString(R.string.bob_option_encourage));
    }

    private static void setSimpleText(String fill) {
        if (pageNum == 1) { // main garden page
            mainBobText.setVisibility(View.VISIBLE); //change to entire box
            bobSimple.setVisibility(View.VISIBLE);
            bobOption1.setVisibility(View.GONE);
            bobOption2.setVisibility(View.GONE);
            bobOption3.setVisibility(View.GONE);
            bobOption4.setVisibility(View.GONE);
            if (convoNum == 12) {
                bobSimple.setText(context.getString(R.string.bob_option_addgrow_plant2));
            } else if (convoNum == 17) {
                bobSimple.setText(context.getString(R.string.bob_option_wilt2));
            } else if (convoNum == 22) {
                bobSimple.setText(context.getString(R.string.bob_option_del_plant2));
            } else if (convoNum == 27) {
                bobSimple.setText(context.getString(R.string.bob_option_encourage2));
            } else {
                mainBobText.setVisibility(View.GONE); //change to entire box
                bobSimple.setVisibility(View.GONE);
            }
        } else if (pageNum == 2) { // focus page
            focusSimpleText.setVisibility(View.VISIBLE);
            focusBobText.setVisibility(View.VISIBLE);
            focusConfirm.setVisibility(View.GONE);
            focusCancel.setVisibility(View.GONE);

            if (convoNum == 101) {
                focusSimpleText.setText(String.format(context.getString(R.string.bob_plant_wilt), fill));
            } else if (convoNum == 111) {
                focusSimpleText.setText(String.format(context.getString(R.string.bob_plant_congrats), fill));
            } else if (convoNum == 156) {
                focusSimpleText.setText(context.getString(R.string.del_plant_yes));
            } else if (convoNum == 161) {
                focusSimpleText.setText(context.getString(R.string.del_plant_no));
            } else if (convoNum == 171) {
                focusSimpleText.setText(context.getString(R.string.spam_water_can));
            } else {
                focusSimpleText.setVisibility(View.GONE);
                focusBobText.setVisibility(View.GONE);
            }
        }
    }

    private static void setFocusTree() {
        focusSimpleText.setVisibility(View.VISIBLE);
        focusBobText.setVisibility(View.VISIBLE);
        focusConfirm.setVisibility(View.VISIBLE);
        focusCancel.setVisibility(View.VISIBLE);
        focusSimpleText.setText(context.getString(R.string.del_plant_check));
    }

}
