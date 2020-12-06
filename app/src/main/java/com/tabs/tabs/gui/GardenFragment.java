package com.tabs.tabs.gui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    public BobLogic myBob;

    private static boolean init = false;
    private static List<Plant> plants;

    public static int NUM_COLUMNS = 3;

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
                Plant p = new Plant(PlantType.POPPY, new Status(Stage.values()[i % Stage.values().length].getStage(), Health.values()[i % Health.values().length].getHealth()), new Profile());
                Log.i("gardenfragment", p.getFileName());
                plants.add(p);
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.garden_fragment_layout, container, false);
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
        layoutManager = new GridLayoutManager(getActivity(), NUM_COLUMNS);
        recyclerView.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlantAdapter(plants);
        adapter.addContext(getActivity());
        // Set CustomAdapter as the adapter for RecyclerView.
        recyclerView.setAdapter(adapter);


        ImageView bobText = view.findViewById(R.id.bobtext);
        ImageView bob = view.findViewById(R.id.bob);
        TextView bobSimple = view.findViewById(R.id.simple_text);
        Button bobOption1 = view.findViewById(R.id.option1);
        Button bobOption2 = view.findViewById(R.id.option2);
        Button bobOption3 = view.findViewById(R.id.option3);
        Button bobOption4 = view.findViewById(R.id.option4);


        System.out.println("yo homie this is my text" + bobText);
        myBob = new BobLogic(getContext(), bobText, bobSimple, bobOption1, bobOption2, bobOption3, bobOption4, plants); // TODO: MAKE SURE THESE PLANTS GET UPDATED

        bobSimple.setOnClickListener(s -> myBob.mainPageSetBob());
        bobOption1.setOnClickListener(s -> myBob.dialogTreeUpdate(11));
        bobOption2.setOnClickListener(s -> myBob.dialogTreeUpdate(16));
        bobOption3.setOnClickListener(s -> myBob.dialogTreeUpdate(21));
        bobOption4.setOnClickListener(s -> myBob.dialogTreeUpdate(26));

        bobOption1.setOnHoverListener((View v, MotionEvent e) -> {
            bobOption1.setBackgroundColor(Color.parseColor("#dddddd"));
            System.out.println("yeeeeeeeetle");
            return true;
        });
        bobOption2.setOnHoverListener((View v, MotionEvent e) -> {
            bobOption2.setBackgroundColor(Color.parseColor("#dddddd"));
            return true;
        });
        bobOption3.setOnHoverListener((View v, MotionEvent e) -> {
            bobOption3.setBackgroundColor(Color.parseColor("#dddddd"));
            return true;
        });
        bobOption4.setOnHoverListener((View v, MotionEvent e) -> {
            bobOption4.setBackgroundColor(Color.parseColor("#dddddd"));
            return true;
        });

//        bob.setOnClickListener(s -> Toast.makeText(getContext(), "i am bob and wholesome end me pls", Toast.LENGTH_LONG).show());
        bob.setOnClickListener(s -> myBob.mainPageSetBob());
        bobText.setOnClickListener(s -> myBob.mainPageSetBob());
    }
}