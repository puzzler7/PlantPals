package com.tabs.tabs.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        subtitle.setText(plant.getProfile().getSubtitle());
        //prof.setImageResource(getContext().getResources().getIdentifier("oval", "drawable", getContext().getPackageName()));

        return rootView;
    }
}
