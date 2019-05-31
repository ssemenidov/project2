package com.example.project2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;


public class LevelActivity extends AppCompatActivity {
    private MainView view;
    private Map map;
    private ArrayList<TowerLand> towerLands;
    private ArrayList<ArrayList<Enemy>> enemiesvave;
    private  ArrayList<Rotatepoints> rotatepoints;
    private
    ArrayList<Map> maps;
    private int flag;
    Bundle arguments ;
    ///////

    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=new MainView(this,map);
         arguments = getIntent().getExtras();
         flag=0;
         maps=new ArrayList<>();
        towerLands=new ArrayList<>();
        rotatepoints=new ArrayList<>();
        rotatepoints.add(new Rotatepoints(400,100,"d"));
        rotatepoints.add(new Rotatepoints(400,525,"l"));
        rotatepoints.add(new Rotatepoints(100,525,"d"));
        rotatepoints.add(new Rotatepoints(100,925,"r"));
        towerLands.add(new TowerLand( view.getContext(),260, 700));
        towerLands.add(new TowerLand( view.getContext(), 220, 300));
        towerLands.add(new TowerLand( view.getContext(),530, 740));
        towerLands.add(new TowerLand( view.getContext(),550, 200));
        towerLands.add(new TowerLand(view.getContext(), 550, 460));
        enemiesvave=new ArrayList<>();
        enemiesvave.add(new ArrayList<Enemy>());

        for (int i =0; i <6 ; i++) {enemiesvave.get(0).add(new Enemy1(view.getContext(),-100,100));flag++;}
        flag=0;
        enemiesvave.add(new ArrayList<Enemy>());
        for (int i =0; i <10 ; i++) {enemiesvave.get(1).add(new Enemy1(view.getContext(),-100,100)); flag++;}
        enemiesvave.add(new ArrayList<Enemy>());
        for (int i =0; i <7 ; i++) {enemiesvave.get(2).add(new Fastenemy(view.getContext(),-100,100));flag++;}
        enemiesvave.add(new ArrayList<Enemy>());
        for (int i =0; i <7 ; i++) {enemiesvave.get(3).add(new Ork(view.getContext(),-100,100));flag++;}


        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2),720,1080, /*view.getResources().getDisplayMetrics().widthPixels, view.getResources().getDisplayMetrics().heightPixels*/ false)
        ,towerLands,30,150,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 80, 60, false)),enemiesvave,rotatepoints));
        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2),720,1080, /*view.getResources().getDisplayMetrics().widthPixels, view.getResources().getDisplayMetrics().heightPixels*/ false)
                ,towerLands,30,150,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 80, 60, false)),enemiesvave,rotatepoints));
        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2),720,1080, /*view.getResources().getDisplayMetrics().widthPixels, view.getResources().getDisplayMetrics().heightPixels*/ false)
                ,towerLands,30,150,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 80, 60, false)),enemiesvave,rotatepoints));
        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2),720,1080, /*view.getResources().getDisplayMetrics().widthPixels, view.getResources().getDisplayMetrics().heightPixels*/ false)
                ,towerLands,30,150,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 80, 60, false)),enemiesvave,rotatepoints));
        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2),720,1080, /*view.getResources().getDisplayMetrics().widthPixels, view.getResources().getDisplayMetrics().heightPixels*/ false)
                ,towerLands,30,150,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 80, 60, false)),enemiesvave,rotatepoints));
        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2),720,1080, /*view.getResources().getDisplayMetrics().widthPixels, view.getResources().getDisplayMetrics().heightPixels*/ false)
                ,towerLands,30,150,new Trigger(50, 100, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 80, 60, false)),enemiesvave,rotatepoints));

        arguments.getInt("level");
        map= maps.get(arguments.getInt("level")-1);
        view.setMap(map);
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
