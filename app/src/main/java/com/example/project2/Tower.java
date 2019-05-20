package com.example.project2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Date;

public class Tower {
    private int x;
    private int y;
    private int damage;
    private int r;
    private Bitmap bitmap;
    private long lasthit;
    private int price;
    private Arrow arrow;
    private static final String TAG = "MyApp";

    public Tower(int x, int y, int damage, int r, Bitmap bitmap, long lasthit, int price,Arrow arrow) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.r = r;
        this.bitmap = bitmap;
        this.lasthit=lasthit;
        this.price=price;
        this.arrow=arrow;

    }
    public Arrow getArrow() { return arrow; }
    public void setArrow(Arrow arrow) { this.arrow = arrow; }
    public void setPrice(int price) { this.price = price; }
    public int getPrice() { return price; }
    public void setLasthit(Long lasthit) { this.lasthit = lasthit; }
    public Long getLasthit() { return lasthit; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getDamage() { return damage; }
    public int getR() { return r; }
    public Bitmap getBitmap() { return bitmap; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setDamage(int damage) { this.damage = damage;}
    public void setR(int r) { this.r = r; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }



    public void  draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2), y-(bitmap.getHeight()/2),null);
    }
}
