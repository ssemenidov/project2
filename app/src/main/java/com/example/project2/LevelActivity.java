package com.example.project2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.project2.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;


public class LevelActivity extends AppCompatActivity {
    private MainView view;
    private Map map;
    private ArrayList<TowerLand> towerLands;
    private ArrayList<ArrayList<Enemy>> enemiesvave;
    ArrayList<Level> levels;
    Bundle arguments ;
    com.example.project2.Level level;
    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=new MainView(this,level);

         arguments = getIntent().getExtras();
         levels=new ArrayList<>();
        towerLands=new ArrayList<>();
        towerLands.add(new TowerLand( view.getContext(),260, 700));
        towerLands.add(new TowerLand( view.getContext(), 220, 300));
        towerLands.add(new TowerLand( view.getContext(),530, 740));
        towerLands.add(new TowerLand( view.getContext(),550, 200));
        towerLands.add(new TowerLand(view.getContext(), 550, 460));
        enemiesvave=new ArrayList<>();
        enemiesvave.add(new ArrayList<Enemy>());
        enemiesvave.add(new ArrayList<Enemy>());
        for (int i = 0; i <10 ; i++) {

            enemiesvave.get(0).add(new Enemy1(view.getContext(),-90*i,100));
            enemiesvave.get(1).add(new Enemy1(view.getContext(),-90*i,100));}
        Log.i(TAG,towerLands.size()+"tower");
        map = new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2), 720, 1048, false)
        ,0 , 0 ,towerLands,30,250,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 50, 50, false)));

        levels.add(new Level(map,enemiesvave));
        arguments.getInt("level");
        level= levels.get(arguments.getInt("level")-1);
        view.setLevel(level);
        setContentView(view);

    }
    @Override
    protected void onDestroy() {
        Log.i(TAG, "Destroying...");
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        Log.i(TAG, "Stopping...");
        super.onStop();

    }
}
