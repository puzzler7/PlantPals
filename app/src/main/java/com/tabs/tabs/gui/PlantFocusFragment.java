package com.tabs.tabs.gui;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tabs.tabs.R;
import com.tabs.tabs.plants.Plant;

import java.sql.SQLOutput;

public class PlantFocusFragment extends Fragment {

    private Plant plant;
    private ImageView img;
    private RelativeLayout parentCard;
    private EditText name;
    private EditText subtitle;
    private ViewGroup rootView;

    public PlantFocusFragment(Plant plt) {
        plant = plt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.plant_focus_fragment, container, false);
        img = rootView.findViewById(R.id.img);
        name = rootView.findViewById(R.id.name);
        parentCard = rootView.findViewById(R.id.plantcard);

        parentCard.setOnClickListener(s->{
            parentCard.setVisibility(View.GONE);
            parentCard.setVisibility(View.VISIBLE);

            System.out.println("I was hiding");
//            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


            View view = getActivity().getCurrentFocus();
            System.out.println("My view: " + view);
//            if (view != null) {
//                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
//            } else {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);


        });

        subtitle = rootView.findViewById(R.id.subtitle);
        //ImageView prof = rootView.findViewById(R.id.profile_pic);

//        img.setImageResource(getContext().getResources().getIdentifier("cutecactus", "drawable", getContext().getPackageName()));
//        System.out.println("MY FRAG FILENAME IS " + plant.getFileName());

        draw();

        return rootView;
    }

    public void draw() {
        draw(""); // the string is to make lambdas happy
    }

    public void draw(String s) {

        img.setImageResource(getContext().getResources().getIdentifier(plant.getFileName(), "drawable", getContext().getPackageName()));
        name.setText(plant.getProfile().getName());
        if(name.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        name.setOnFocusChangeListener((v, hasFocus)-> {
          if (!hasFocus) {
//            name.requestFocus();

                plant.getProfile().setName(name.getText().toString());
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

              System.out.println("I have focus");

//              name.clearFocus();
//              name.setShowSoftInputOnFocus(false);
          } else {
              System.out.println("I lack focus");
//              name.clearFocus();
//              name.setShowSoftInputOnFocus(false);
          }
        });



        subtitle.setText(plant.getProfile().getSubtitle());
        if(subtitle.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        subtitle.setOnFocusChangeListener((v, hasFocus)-> {
            if (!hasFocus) {
                plant.getProfile().setSubtitle(subtitle.getText().toString());
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
        //prof.setImageResource(getContext().getResources().getIdentifier("oval", "drawable", getContext().getPackageName()));

        TextView daysWater = rootView.findViewById(R.id.days_since_watered);
        long daysDiff = plant.getDaysSinceWatered();
        if (plant.getLastWater() % 86400000 == 0) {
            daysWater.setText(getString(R.string.zero_day_water));
        } else if (daysDiff == 1) {
            daysWater.setText(getString(R.string.day_since_water));
        } else {
            daysWater.setText(String.format(getString(R.string.days_since_water), daysDiff));
        }
    }
}
