package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class TowerLand {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int touched;
    private static final String TAG = "MyApp";

    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setTouched(int istouched) { this.touched = istouched; }
    public Bitmap getBitmap() { return bitmap; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getTouched() { return touched; }
    public TowerLand(Context context, int x, int y) {
        this.bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.towerland), 110, 70, false);
        this.x = x;
        this.y = y;
        this.touched =0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2), null);
    }

    public void handleActionDown(double eventX, double eventY) {
        if (touched!=-1){
        if (eventX >= (x - bitmap.getWidth() / 2) &&
                (eventX <= (x + bitmap.getWidth() / 2))) {
            if (eventY >= (y - bitmap.getHeight() / 2) &&
                    (eventY <= (y + bitmap.getHeight() / 2))) {
                if (touched==0 ) touched=1;
            }else touched=0;
        }else touched=0;

        }
    }
}
