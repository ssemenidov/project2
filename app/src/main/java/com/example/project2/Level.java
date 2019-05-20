package com.example.project2;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;

public class Level {
    public Level( Map map,  ArrayList<ArrayList<Enemy>> enemiesvave) {
        Log.i(TAG,"level");
        this.map = map;
        this.enemiesvave = enemiesvave;
    }
    private Map map;

    private ArrayList<ArrayList<Enemy>> enemiesvave;
    public void setMap(Map map) { this.map = map; }
    public void setEnemiesvave(ArrayList<ArrayList<Enemy>> enemiesvave) { this.enemiesvave = enemiesvave; }
    public Map getMap() { return map; }
    public ArrayList<ArrayList<Enemy>> getEnemiesvave() { return enemiesvave; }
    private static final String TAG = "MyApp";
}
