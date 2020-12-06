package com.tabs.tabs.gui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tabs.tabs.R;
import com.tabs.tabs.plants.Health;
import com.tabs.tabs.plants.Plant;
import com.tabs.tabs.plants.PlantType;
import com.tabs.tabs.plants.Profile;
import com.tabs.tabs.plants.Stage;
import com.tabs.tabs.plants.Status;

import java.util.ArrayList;
import java.util.List;

public class GardenFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private PlantAdapter adapter;

    private static boolean init = false;
    private static List<Plant> plants;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        if (!init) {
            init = true;
            plants = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
//                plants.add(new Plant());
                plants.add(new Plant(PlantType.POPPY, new Status(Stage.values()[i % Stage.values().length].getStage(), Health.values()[i % Health.values().length].getHealth()), new Profile()));
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlantAdapter(plants);
        adapter.addContext(getActivity());
        // Set CustomAdapter as the adapter for RecyclerView.
        recyclerView.setAdapter(adapter);
    }
}