package com.example.project2;

import android.util.Log;

public class Rotatepoints {
    private int x;
    private int y;
    private static final String TAG = "MyApp";
    private String direction;
    public int getX() { return x; }
    public int getY() { return y; }
    public String getDirection() { return direction; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setDirection(String direction) { this.direction = direction; }

    public Rotatepoints(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public boolean Rotate(Enemy enemy){

        if (((enemy.getX()<(x+5))&&(enemy.getX()>(x-5)))&&
        ((enemy.getY()<(y+5))&&(enemy.getY()>(y-5)))){


            return  true;
        }
        else {
            return false;}
    }



}
