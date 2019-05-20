package com.example.project2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;



public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private static final String TAG = "MyApp";
    private MainView mainView;
    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }
    public MainThread(SurfaceHolder surfaceHolder, MainView mainView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.mainView = mainView;
        Log.i(TAG,running+"");
    }
    @Override
    public void run() {
        Canvas canvas;
        Log.i(TAG,"Starting game loop");
        while (running) {
            canvas=null;
            try{
            canvas= this.surfaceHolder.lockCanvas();
            synchronized(surfaceHolder){
// обновляем состояние
                this.mainView.update();
// формируем новый кадр
                this.mainView.onDraw(canvas);//Вызываем метод для рисования
            }
        } finally{
// в случае ошибки, плоскость не перешла в
//требуемое состояние
            if(canvas!=null){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        }

    }
}
