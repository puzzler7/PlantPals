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
import com.tabs.tabs.plants.Plant;

import java.util.ArrayList;

public class GardenFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private PlantAdapter adapter;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
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

        adapter = new PlantAdapter(new ArrayList<Plant>());
        adapter.addContext(getActivity());
        // Set CustomAdapter as the adapter for RecyclerView.
        recyclerView.setAdapter(adapter);
    }
}