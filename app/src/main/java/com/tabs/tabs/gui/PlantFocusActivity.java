package com.tabs.tabs.gui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class PlantFocusActivity extends AppCompatActivity {

    private ViewPager2 pager;
    private int total;
    private PlantFocusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_focus_activity);

        //imageLinks = getIntent().getStringArrayListExtra("links");
        total = getIntent().getIntExtra("total", 10);
        int startIndex = getIntent().getIntExtra("index", 0);




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

//        ImageButton exit = findViewById(R.id.exit);
//        exit.setOnClickListener(s->finish());
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
            return new PlantFocusFragment(new Plant());
        }

        @Override
        public int getItemCount() {
            return total;
        }
    }
}
