package com.tabs.tabs.gui;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tabs.tabs.R;
import com.tabs.tabs.database.MockDatabase;
import com.tabs.tabs.plants.Plant;

import java.util.ArrayList;
import java.util.List;

import static com.tabs.tabs.gui.GardenFragment.NUM_COLUMNS;

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
        private final TextView name;
        //private final ImageView profileView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            img = view.findViewById(R.id.plant_img);
            name = view.findViewById(R.id.plant_name);
            //profileView = view.findViewById(R.id.profile_pic);
        }

        public ImageView getImageView() {
            return img;
        }

        public TextView getTextView() {
            return name;
        }

//        public ImageView getProfileView() {
//            return profileView;
//        }
    }

    public PlantAdapter() {
        plants = MockDatabase.getPlants();
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
                if (BobLogic.pageNum == 1 && BobLogic.convoNum != 0) {
                    return;
                }
                Intent intent = new Intent(getContext(), PlantFocusActivity.class);
//                intent.putStringArrayListExtra("links", new ArrayList<>(galleryImages));
                intent.putExtra("total", getItemCount());
                //intent.putParcelableArrayListExtra("plantList", new ArrayList<>(plants));
//                System.out.println("\t On click first 3:" +  plants.get(0).getFileName() + " " + plants.get(1).getFileName() + " " + plants.get(2).getFileName());
                intent.putExtra("index", position);
                getContext().startActivity(intent);
            }
        });

        Plant currPlant;
        if (position < plants.size()) {
            currPlant = plants.get(position);
        } else {
            currPlant = new Plant();
        }
        int id = context.getResources().getIdentifier(currPlant.getFileName(), "drawable", context.getPackageName());
        System.out.println(currPlant.getFileName() + ": " + id);
        viewHolder.getImageView().setImageResource(id);
        viewHolder.getTextView().setText(currPlant.getProfile().getName());

        //viewHolder.getProfileView().setImageResource(R.drawable.oval);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (plants.size()/NUM_COLUMNS)*NUM_COLUMNS+2*NUM_COLUMNS;
    }
}
