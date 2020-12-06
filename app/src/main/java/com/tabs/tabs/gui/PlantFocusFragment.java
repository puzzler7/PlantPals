package com.tabs.tabs.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tabs.tabs.R;
import com.tabs.tabs.plants.Plant;

public class PlantFocusFragment extends Fragment {

    private Plant plant;

    public PlantFocusFragment(Plant plt) {
        plant = plt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.plant_focus_fragment, container, false);
        ImageView img = rootView.findViewById(R.id.img);
        EditText name = rootView.findViewById(R.id.name);

        EditText subtitle = rootView.findViewById(R.id.subtitle);
        //ImageView prof = rootView.findViewById(R.id.profile_pic);

//        img.setImageResource(getContext().getResources().getIdentifier("cutecactus", "drawable", getContext().getPackageName()));
//        System.out.println("MY FRAG FILENAME IS " + plant.getFileName());

        img.setImageResource(getContext().getResources().getIdentifier(plant.getFileName(), "drawable", getContext().getPackageName()));
        name.setText(plant.getProfile().getName());
        if(name.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        name.setOnFocusChangeListener((v, hasFocus)-> {
            if (!hasFocus) {
                plant.getProfile().setName(name.getText().toString());
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
        long daysDiff = (System.currentTimeMillis() - plant.getLastWater())/86400000;
        if (plant.getLastWater() == 0) {
            daysWater.setText(getString(R.string.zero_day_water));
        } else if (daysDiff == 1) {
            daysWater.setText(getString(R.string.day_since_water));
        } else {
            daysWater.setText(getString(R.string.days_since_water));
        }

        return rootView;
    }
}
