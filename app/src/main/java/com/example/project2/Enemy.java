package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ProgressBar;


public class Enemy {
    private Bitmap bitmap;
    private Bitmap bitmapdead;
    private float x;
    private  float y;
    private int speedmax;
    private Speed speed;
    private  int hp;
    private  int maxhp;
    private boolean win;
    private int price;
    private  long timedead;
    private static final String TAG = "MyApp";

    public Enemy(Bitmap bitmap, Bitmap bitmapdead, int x, int y, int speedmax, int maxhp, int price) {
        this.bitmapdead=bitmapdead;
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.speedmax=speedmax;
        this.maxhp=maxhp;
        this.hp=maxhp;
        this.speed=new Speed(speedmax,0);
        this.win=false;
        this.price=price;



    }
    public int getPrice() { return price; }
    public long getTimedead() { return timedead; }
    public void setTimedead(long timedead) { this.timedead = timedead; }
    public void setWin(boolean win) { this.win = win; }
    public boolean isWin() { return win; }
    public void setSpeedmax(int speedmax) { this.speedmax = speedmax; }
    public int getSpeedmax() { return speedmax; }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap= bitmap;
    }
    public float getX(){
        return x;
    }
    public void setX(float x){
        this.x= x;
    }
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y= y;
    }
    public Speed getSpeed() {
        return speed;
    }
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }
    public int gethp() {
        return hp;
    }
    public void sethp(int hp) { this.hp=hp; }

    public void update(){

        x=(int)(x+speed.getXv());
        y=(int)(y+speed.getYv());

    }

    public void  draw(Canvas canvas){
        Matrix m =new Matrix();
        m.setTranslate(x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2));
        Paint paint=new Paint();
        Paint paintstroke=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        
        paintstroke.setStyle(Paint.Style.STROKE);
        paintstroke.setStrokeWidth(3);
        paintstroke.setColor(Color.BLACK);
        Rect rect=new Rect();
        rect.set((int)x-(bitmap.getWidth()/2),(int)y-(bitmap.getHeight()/2)-10,(int)x-(bitmap.getWidth()/2)+bitmap.getWidth()*hp/maxhp,(int)y-(bitmap.getHeight()/2));
        if (speed.getXv()<0){
            m.setTranslate(x+(bitmap.getWidth()/2),y-(bitmap.getHeight()/2));
            m.preScale(-1,1);

        }
        canvas.drawBitmap(bitmap,m,null);
        canvas.drawRect(rect,paint);
        canvas.drawRect(rect,paintstroke);
    }
    public void  drawdead(Canvas canvas){
        canvas.drawBitmap(bitmapdead, x-(bitmapdead.getWidth()/2), y-(bitmapdead.getHeight()/2),null);


    }




}
