package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import java.util.Vector;

public class Arrow {
    private static final String TAG = "MyApp";
    private Enemy enemy;
    private float x;
    private float y;
    private boolean isFly;
    private boolean isHit;
    private boolean isMiss;
     private Bitmap bitmap;
   public Enemy getEnemy() {return enemy;}
   public float getX() {return x;}
   public float getY() {return y;}
   public boolean isFly() {return isFly;}
    public boolean isHit() {return isHit;}
   public Bitmap getBitmap() {return bitmap;}
   public void setEnemy(Enemy enemy) {this.enemy = enemy;}
   public void setX(float x) {this.x = x;}
   public void setY(float y) {this.y = y;}
   public void setFly(boolean fly) {isFly = fly;}
    public void setHit(boolean hit) {isHit = hit;}
   public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}


    public Arrow(Context context, float x, float y) {
         this.x=x;
         this.y=y;
         this.bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow), 60, 60, false);
         this.isFly=false;
         this.isHit=false;
         this.isMiss=false;
    }
    public  void update(){
        if(isFly) {
            float k=(float) Math.sqrt(Math.pow(enemy.getX()-x,2)+Math.pow(enemy.getY()-y,2));

            x =  (x + (enemy.getX()-x)*7/k);
            y =  (y + (enemy.getY()-y)*7/k);
            if (x >= (enemy.getX() - 1.2*enemy.getBitmap().getWidth() ) &&
                    (x <= (enemy.getX() + 1.2*enemy.getBitmap().getWidth() ))) {
                if (y >= (enemy.getY() - 1.2*enemy.getBitmap().getHeight() ) &&
                        (y <= (enemy.getY() + 1.2*enemy.getBitmap().getHeight() ))) {
                    isHit=true;
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
                m.preRotate((float) Math.toDegrees(Math.atan((enemy.getY() - y) / (enemy.getX() - x)))-45, 0, 0);
            }
            else{
                m.preRotate((float) Math.toDegrees(Math.atan((enemy.getY() - y) / (enemy.getX() - x)))+135, 0, 0);
                }
            canvas.drawBitmap(bitmap,m,null);
        }
    }
}
