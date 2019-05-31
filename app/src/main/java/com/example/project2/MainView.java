package com.example.project2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Date;


public class MainView extends SurfaceView implements
        SurfaceHolder.Callback {
    public void setMap(Map map) {
        this.map = map;
    }
    Boolean touch;
    private Map map;
    private  MainThread thread;
    private ArrayList<Enemy> enemiesDead;
    private ArrayList<Enemy> enemiesLive;
    private ArrayList<Tower> towers;
    private int countvave;
    private boolean start;
    private boolean flagb;
    private int flag;
    private static final String TAG = "MyApp";

    public MainView(Context context, Map map) {
        super(context);
        this.map = map;
        getHolder().addCallback(this);
        countvave = 0;
        enemiesDead = new ArrayList<>();
        enemiesLive =new ArrayList<>();
        towers =new ArrayList<>();
        touch = false;
        start=false;
        thread=new MainThread(getHolder(), this);
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "Surface is being changed");
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "Surface is being created");
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "Surface is being destroyed");
//посылаем потоку команду на закрытие и дожидаемся,
//пока поток не будет закрыт.
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
// пытаемся снова остановить поток thread
            }
        }
        Log.i(TAG, "Thread was shut down cleanly");

    }

    public boolean onTouchEventlandtower(MotionEvent event, TowerLand towerLand) {
            towerLand.handleActionDown(event.getX(), event.getY());

        return true;
}
    public boolean onTouchEventtrigger(MotionEvent event, Trigger trigger) {
        trigger.handleActionDown(event.getX(), event.getY());
        return true;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if ((countvave)==map.getEnemiesvave().size()&& (enemiesLive.size() == 0)){
                if(( event.getY()>200)&& ( event.getY()<400)){
                    thread.setRunning(false);
                    ((Activity) getContext()).finish();
                }
            }

            for (TowerLand i : map.getTowerLands()) {
                onTouchEventlandtower(event, i);
            }
            onTouchEventtrigger(event, map.getTrigger());
            if (event.getY() > getHeight() - 100) {
                thread.setRunning(false);
                ((Activity) getContext()).finish();
            }
        }

        return true;
    }


    public void update() {
        if (map.getTrigger().getTouched() == 1) {
            countvave += 1;
            //enemiesLive.addAll(map.getEnemiesvave().get(countvave - 1));
            map.getTrigger().setTouched(-1);
            start = true;
            flag=0;
            flagb=true;

            Log.i(TAG,"start");
        } else if (enemiesLive.size() == 0) {
            start = false;
            map.getTrigger().setTouched(0);

        }
        if (start) {
            if (flagb){
            enemiesLive.add(map.getEnemiesvave().get(countvave - 1).get(flag));
            flagb=false;
            }
            if ((map.getEnemiesvave().get(countvave - 1).get(flag).getX()>10)&&(flag<map.getEnemiesvave().get(countvave - 1).size()-1)){
                flag++;
                flagb=true;
            }

            for (int i = 0; i < enemiesLive.size(); i++) {
                Enemy enemy = enemiesLive.get(i);
                if (enemy.gethp() > 0) {
                    updateenemy(enemy);
                } else {
                    enemiesLive.remove(enemy);
                    enemiesDead.add(enemy);
                    enemy.setTimedead(new Date().getTime());
                    map.setMoney(map.getMoney() + enemy.getPrice());
                }
                if (enemy.isWin()) {

                    map.setLives(map.getLives() - 1);
                    enemiesLive.remove(enemy);
                    enemy.setWin(false);
                }
            }

            for (Tower tower : towers) {
                updatetower(tower);
            }
        }

        for (TowerLand i : map.getTowerLands()) {
            if (i.getTouched() == 1) {
                Log.i(TAG, "build");

                if ((map.getMoney() -  70) >= 0) {
                    Tower1 t = new Tower1(getContext(), i.getX(), i.getY());
                    map.setMoney((map.getMoney() - t.getPrice()));
                    towers.add(t);
                    i.setTouched(-1);

                }
                else{
                    i.setTouched(0);
                }
            }
        }
    }


    public void updatetower(Tower tower) {
        long now = new Date().getTime();
        for (Enemy enemy : enemiesLive) {
            if ((Math.sqrt(
                    Math.pow(((tower.getX()) - (enemy.getX())), 2) +
                            Math.pow((tower.getY() - enemy.getY()), 2))
                    < tower.getR()) && (enemy.gethp() > 0)&&(now > (tower.getLasthit() + 800L))&&(!tower.getArrow().isFly())) {
                if (enemy.gethp()>0){
                    tower.getArrow().setX(tower.getX());
                    tower.getArrow().setY(tower.getY());
                    tower.getArrow().setEnemy(enemy);
                    tower.getArrow().setFly(true);

                }
                tower.setLasthit(now);
            }

        }
        if (tower.getArrow().isHit()){
            tower.getArrow().getEnemy().sethp(tower.getArrow().getEnemy().gethp() - tower.getDamage());
            tower.getArrow().setHit(false);
            Log.i(TAG,"hit");
        }
        tower.getArrow().update();
    }

    public void updateenemy(Enemy enemy) {
            enemy.update();
            map.Road(enemy);


    }
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, map.getEnemiesvave().size()+" "+countvave+" "+ enemiesLive.size());

        map.drawmap(canvas);
        if (map.getTrigger().getTouched()==0){ map.getTrigger().draw(canvas);}
        for (Enemy i :enemiesDead) {
            if ((new Date().getTime()-i.getTimedead())<5000L){
                i.drawdead(canvas);
            }
        }
        for (Enemy i:enemiesLive)
        { i.draw(canvas); }

        for (Tower j:towers) {
            j.draw(canvas);
            j.getArrow().draw(canvas);}

        for (TowerLand i:map.getTowerLands()) {
            if (i.getTouched()>-1){
            i.draw(canvas);
            }}
        if ((countvave)==map.getEnemiesvave().size()&& (enemiesLive.size() == 0)){
            canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.exit), 300, 100, false),200,200,null);

        }
    }
}
