package com.tabs.tabs.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        img.setImageResource(getContext().getResources().getIdentifier("cutecactus", "drawable", getContext().getPackageName()));

        return rootView;
    }
}
