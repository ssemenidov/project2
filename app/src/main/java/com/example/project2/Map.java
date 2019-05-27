package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class Map {
    public void setContext(Context context) {
        this.context = context;
    }

    private  Context context;
    private String filename;
    private Bitmap bitmap;
    private int x;
    private int y;
    private int lives;
    private int money;
    private int i;
    private Trigger trigger;
    private static final String TAG = "MyApp";
    private ArrayList<TowerLand> towerLands;
    private ArrayList<ArrayList<Enemy1>> enemiesvave;
    private ArrayList<Rotatepoints> rotatepoints;

    public Map(Context context,String filename, ArrayList<TowerLand> towerLands,int lives, int money, Trigger trigger, ArrayList<ArrayList<Enemy1>> enemiesvave,ArrayList<Rotatepoints> rotatepoints) throws IOException {
        this.context=context;
        this.bitmap = BitmapFactory.decodeStream(.getAssets().open(filename));
        this.x = 0;
        this.y = 0;
        this.towerLands=towerLands;
        this.lives=lives;
        this.money=money;
        this.trigger=trigger;
        this.enemiesvave=enemiesvave;
        this.rotatepoints=rotatepoints;
         i=0;


    }
    public void setEnemiesvave(ArrayList<ArrayList<Enemy1>> enemiesvave) {this.enemiesvave = enemiesvave;}
    public ArrayList<ArrayList<Enemy1>> getEnemiesvave() {return enemiesvave;}
    public Bitmap getBitmap() { return bitmap; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Trigger getTrigger() { return trigger; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) {this.y = y; }
    public void setTrigger(Trigger trigger) { this.trigger = trigger; }
    public void setMoney(int money) { this.money = money; }
    public int getMoney() { return money; }
    public void setLives(int lives) { this.lives = lives; }
    public int getLives() { return lives; }
    public ArrayList<TowerLand> getTowerLands() { return towerLands; }
    public void setTowerLands(ArrayList<TowerLand> towerLands) { this.towerLands = towerLands; }
    public void drawmap(Canvas canvas) {
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawBitmap(bitmap,0, 0, null);

        canvas.drawText(lives+"",bitmap.getWidth()-60,60,paint);
        canvas.drawText(money+"",bitmap.getWidth()-200,60,paint);
    }

    public void Road(Enemy enemy){
        for (Rotatepoints r:rotatepoints) {
            if(r.Rotate(enemy)){
                enemy.getSpeed().rotate(enemy.getSpeedmax(),r.getDirection());
        }
        if ((enemy.getX()>(bitmap.getWidth())+enemy.getBitmap().getWidth())){
                enemy.setWin(true);
                }

    }
    }
    /*public void Road(Enemy enemy){
        if ((enemy.getX()>400) && (enemy.getY()>=100)&&(enemy.getY()<500)){
            enemy.getSpeed().rotateDown(enemy.getSpeedmax());
        }
        if ((enemy.getX()>400)&&(enemy.getY()>=500)&&(enemy.getY()<900)){
            enemy.getSpeed().rotateLeft(enemy.getSpeedmax());
        }
        if ((enemy.getX()<100 )&&(enemy.getY()>=500)&&(enemy.getY()<900)){
            enemy.getSpeed().rotateDown(enemy.getSpeedmax());
        }
        if (enemy.getY()>=900){
            enemy.getSpeed().rotateRight(enemy.getSpeedmax());
        }
        if ((enemy.getX()>(bitmap.getWidth())+enemy.getBitmap().getWidth())){

            enemy.setWin(true);

        }
    }*/
}
