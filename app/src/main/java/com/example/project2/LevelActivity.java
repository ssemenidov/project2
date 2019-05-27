package com.example.project2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class LevelActivity extends AppCompatActivity {
    private MainView view;
    private Map map;
    private FileInputStream stream;
    private InputStreamReader reader;
    private ArrayList<TowerLand> towerLands;
    private ArrayList<ArrayList<Enemy>> enemiesvave;
    private  ArrayList<Rotatepoints> rotatepoints;
    ArrayList<Map> maps;
    Bundle arguments ;

    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=new MainView(this,map);
     /*
         arguments = getIntent().getExtras();
         maps=new ArrayList<>();
        towerLands=new ArrayList<>();
        rotatepoints=new ArrayList<>();
        rotatepoints.add(new Rotatepoints(400,100,"d"));
        rotatepoints.add(new Rotatepoints(400,500,"l"));
        rotatepoints.add(new Rotatepoints(100,500,"d"));
        rotatepoints.add(new Rotatepoints(100,920,"r"));
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
        maps.add( new Map(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.map2), 720, 1080, false)
        ,towerLands,30,250,new Trigger(50, 100, Bitmap.c
        reateScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trigger), 50, 50, false)),enemiesvave,rotatepoints));
       */
        Gson gson =new Gson();
        try {
            InputStream stream= getAssets().open("1.json");
            InputStreamReader reader=new InputStreamReader(stream);
            map=gson.fromJson(reader,Map.class);

            view.setMap(map);
            setContentView(view);
            Log.i(TAG,"file");
        } catch (IOException e) {
            e.printStackTrace();
        }







        /*arguments.getInt("level");
        map= maps.get(arguments.getInt("level")-1);
        view.setMap(map);
        setContentView(view);*/

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
