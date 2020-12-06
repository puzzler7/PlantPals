package com.tabs.tabs;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tabs.tabs.database.AppDatabase;
import com.tabs.tabs.database.MockDatabase;
import com.tabs.tabs.database.PlantModel;
import com.tabs.tabs.gui.BobLogic;
import com.tabs.tabs.plants.Plant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "plant_db").allowMainThreadQueries().build();
        db.PlantDao().deleteAll();
        for (int i = 0; i < MockDatabase.getPlants().size(); i++) {
            Plant p = MockDatabase.getPlants().get(i);
            PlantModel pm = p.makePlantModel();
            pm.id = i+1;
            db.PlantDao().insert(pm);
        }
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}