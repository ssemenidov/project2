package com.example.project2;

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
    public void setLevel(Level level) {
        this.level = level;
    }

    Boolean touch;
    private Level level;
    private  MainThread thread;
    private ArrayList<Enemy> enemiesDead;
    private ArrayList<Enemy> enemiesLive;
    private ArrayList<Tower> towers;
    private int countvave;
    private boolean start;
    private static final String TAG = "MyApp";

    public MainView(Context context, Level level) {
        super(context);
        this.level = level;
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

            for (TowerLand i : level.getMap().getTowerLands()) {
                onTouchEventlandtower(event, i);
            }
            onTouchEventtrigger(event, level.getMap().getTrigger());
            if (event.getY() > getHeight() - 100) {
                thread.setRunning(false);
                ((Activity) getContext()).finish();
            }
        }

        return true;
    }


    public void update() {
        if (level.getMap().getTrigger().getTouched() == 0) {
            countvave += 1;
            enemiesLive.addAll(level.getEnemiesvave().get(countvave - 1));
            level.getMap().getTrigger().setTouched(1);
            start = true;
        } else if (enemiesLive.size() == 0) {
            start = false;
            level.getMap().getTrigger().setTouched(-1);
        }
        if (start) {
            for (int i = 0; i < enemiesLive.size(); i++) {
                Enemy enemy = enemiesLive.get(i);
                if (enemy.gethp() > 0) {
                    updateenemy(enemy);
                } else {
                    enemiesLive.remove(enemy);
                    enemiesDead.add(enemy);
                    enemy.setTimedead(new Date().getTime());
                    level.getMap().setMoney(level.getMap().getMoney() + enemy.getPrice());
                }
                if (enemy.isWin()) {

                    level.getMap().setLives(level.getMap().getLives() - 1);
                    enemiesLive.remove(enemy);
                    enemy.setWin(false);
                }
            }

            for (Tower tower : towers) {
                updatetower(tower);
            }
        }

        for (TowerLand i : level.getMap().getTowerLands()) {
            if (i.getTouched() == 1) {
                Log.i(TAG, "build");

                if ((level.getMap().getMoney() -  70) >= 0) {
                    Tower1 t = new Tower1(getContext(), i.getX(), i.getY());
                    level.getMap().setMoney((level.getMap().getMoney() - t.getPrice()));
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
                    < tower.getR()) && (enemy.gethp() > 0)&&(now > (tower.getLasthit() + 800L))) {
                if (enemy.gethp()>0){
                    tower.getArrow().setX(tower.getX());
                    tower.getArrow().setY(tower.getY());
                    tower.getArrow().setEnemy(enemy);
                    tower.getArrow().setFly(true);
                    enemy.sethp(enemy.gethp() - tower.getDamage());}

                tower.setLasthit(now);
            } }
    tower.getArrow().update();
    }

    public void updateenemy(Enemy enemy) {
            enemy.update();
            level.getMap().Road(enemy);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        level.getMap().drawmap(canvas);
        if ( level.getMap().getTrigger().getTouched()==-1){ level.getMap().getTrigger().draw(canvas);}
        for (int i = 0; i <enemiesDead.size() ; i++) {
            Enemy enemy=enemiesDead.get(i);
            if ((new Date().getTime()-enemy.getTimedead())<5000L){
                enemy.drawdead(canvas);
            }
        }
        for (Enemy i:enemiesLive)
        { i.draw(canvas); }

        for (Tower j:towers) {
            j.draw(canvas);
            if (j.getArrow().isFly()){
        j.getArrow().draw(canvas);}}

        for (TowerLand i:level.getMap().getTowerLands()) {
            if (i.getTouched()>-1){
            i.draw(canvas);
            }}
    }
}
