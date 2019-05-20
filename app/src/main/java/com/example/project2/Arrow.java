package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class Arrow {
    private static final String TAG = "MyApp";
    private Enemy enemy;
    private Speed speed;
    private float x;
    private float y;
    private boolean isFly;
     private Bitmap bitmap;
   public Enemy getEnemy() {return enemy;}
   public Speed getSpeed() {return speed;}
   public float getX() {return x;}
   public float getY() {return y;}
   public boolean isFly() {return isFly;}
   public Bitmap getBitmap() {return bitmap;}
   public void setEnemy(Enemy enemy) {this.enemy = enemy;}
   public void setSpeed(Speed speed) {this.speed = speed;}
   public void setX(float x) {this.x = x;}
   public void setY(float y) {this.y = y;}
   public void setFly(boolean fly) {isFly = fly;}
   public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}


    public Arrow(Context context, float x, float y) {
        this.speed = new Speed();
        this.x=x;
        this.y=y;
        this.bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow), 60, 60, false);
         this.isFly=false;
    }
    public  void update(){
        if(isFly) {
            speed.setXv((enemy.getX() - x) /9);
            speed.setYv((enemy.getY() - y)/9);
            x = (int) (x + speed.getXv());
            y = (int) (y + speed.getYv());
            if (x >= (enemy.getX() - enemy.getBitmap().getWidth() / 2) &&
                    (x <= (enemy.getX() + enemy.getBitmap().getWidth() / 2))) {
                if (y >= (enemy.getY() - enemy.getBitmap().getHeight() / 2) &&
                        (y <= (enemy.getY() + enemy.getBitmap().getHeight() / 2))) {
                    isFly=false;
                }
            }
        }
    }
    public void draw(Canvas canvas){
        if(isFly) {
            Matrix m = new Matrix();
            m.setTranslate(x, y);
            /*if ((speed.getXv()>0)&&(speed.getYv()>0) ){
                m.preRotate(-180,bitmap.getWidth()/2,bitmap.getHeight()/2);
            }
            if ((speed.getXv()<0)&&(speed.getYv()>0) ){
                m.preRotate(-90,bitmap.getWidth()/2,bitmap.getHeight()/2);
            }
            if ((speed.getXv()>0)&&(speed.getYv()<0) ){
                m.preRotate(90,bitmap.getWidth()/2,bitmap.getHeight()/2);
            }*/
            if(((enemy.getX()-x))>=0) {
                m.preRotate((float) Math.toDegrees(Math.atan((enemy.getY() - y) / (enemy.getX() - x)))-45, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
            }
            else{
                m.preRotate((float) Math.toDegrees(Math.atan((enemy.getY() - y) / (enemy.getX() - x)))+135, bitmap.getWidth() / 2, bitmap.getHeight() / 2);


            }
            canvas.drawBitmap(bitmap,m,null);
        }

    }



}
