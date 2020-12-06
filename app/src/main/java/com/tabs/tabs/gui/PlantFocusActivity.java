package com.tabs.tabs.gui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.tabs.tabs.R;
import com.tabs.tabs.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantFocusActivity extends AppCompatActivity {

    private ViewPager2 pager;
    private int total;
    private PlantFocusAdapter adapter;
    private List<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_focus_activity);

        //imageLinks = getIntent().getStringArrayListExtra("links");
        total = getIntent().getIntExtra("total", 10);
        int startIndex = getIntent().getIntExtra("index", 0);
        plantList = getIntent().getParcelableArrayListExtra("plantList");
        System.out.println("ON FOCUS CREATE index: " + startIndex + " : " + plantList.get(3).getFileName());

        // Instantiate a ViewPager and a PagerAdapter.
        pager = findViewById(R.id.plant_pager);
        adapter = new PlantFocusAdapter(this);
        pager.setAdapter(adapter);
        pager.setCurrentItem(startIndex);

//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                //do nothing
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("image_position", position);
//                bundle.putString("image", imageLinks.get(position));
//                mFirebaseAnalytics.logEvent("gallery_scroll", bundle);
//                setTitleText(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                //do nothing
//            }
//        });

//        title = findViewById(R.id.appbar_title);
//        setTitleText(startIndex);

        Plant currPlant;
        if (pager.getCurrentItem() < plantList.size()) {
            currPlant = plantList.get(pager.getCurrentItem());
        } else {
            currPlant = new Plant();
        }

        ImageButton exit = findViewById(R.id.exit);
        exit.setOnClickListener(s->finish());

        ImageButton water = findViewById(R.id.water);
        water.setOnClickListener(s->currPlant.water());

        ImageButton delete = findViewById(R.id.delete);
        //delete.setOnClickListener();

        ImageButton notes = findViewById(R.id.notes);
        notes.setOnClickListener(s->{
            final Plant thisPlant;
            if (pager.getCurrentItem() < plantList.size()) {
                thisPlant = plantList.get(pager.getCurrentItem());
            } else {
                thisPlant = new Plant();
            }
            ConstraintLayout notesLayout = findViewById(R.id.notes_layout);
            TextView title = notesLayout.findViewById(R.id.notes_title);
            title.setText(String.format("Notes on %s:", thisPlant.getProfile().getName()));
            EditText editText = notesLayout.findViewById(R.id.notes_text);
            if (thisPlant.getProfile().getNotes().size() == 0) {
                thisPlant.getProfile().addNote("");
            }
            editText.setText(thisPlant.getProfile().getNotes().get(0));
            if(editText.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            notesLayout.setVisibility(View.VISIBLE);
            editText.setOnFocusChangeListener((v, hasFocus)-> {
                if (!hasFocus) {
                    thisPlant.getProfile().getNotes().set(0, editText.getText().toString());
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    notesLayout.setVisibility(View.GONE);
                }
            });
        });

    }

//    private void setTitleText(int pos) {
//        String text = String.format(getString(R.string.image_n_of_n), pos+1, imageLinks.size());
//        title.setText(text);
//    }

    private class PlantFocusAdapter extends FragmentStateAdapter { //Used for viewpager2

        public PlantFocusAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
//            return new PlantFocusFragment(null);//new Plant());
//            return new PlantFocusFragment(new Plant());
//            System.out.println("BAD" + plantList.size() + " " + position);
            Plant currPlant;
            if (position < plantList.size()) {
                currPlant = plantList.get(position);
            } else {
                currPlant = new Plant();
            }
            return new PlantFocusFragment(currPlant);
        }

        @Override
        public int getItemCount() {
            return total;
        }
    }
}
