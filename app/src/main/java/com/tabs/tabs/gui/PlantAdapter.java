package com.tabs.tabs.gui;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.tabs.tabs.R;
import com.tabs.tabs.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private List<Plant> plants;
    private Context context;

    public void addContext(Context c) {
        context = c;
    }

    private Context getContext() {
        return context;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            img = view.findViewById(R.id.plant_img);
        }

        public ImageView getImageView() {
            return img;
        }

        //TODO: getters for image views and text views
    }

    public PlantAdapter(List<Plant> plts) {
        plants = plts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.plant_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        //viewHolder.getTextView().setText(localDataSet[position]);

        viewHolder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlantFocusActivity.class);
                //intent.putStringArrayListExtra("links", new ArrayList<>(galleryImages));
                intent.putExtra("total", getItemCount());
                intent.putExtra("index", position);
                getContext().startActivity(intent);
            }
        });

        //TODO: SET IMAGE AND ETC
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return plants.size(); //+1 for empty pot?
    }
}
