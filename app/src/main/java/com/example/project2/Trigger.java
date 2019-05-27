package com.example.project2;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Trigger {
    private int x;
    private int y;
    private Bitmap bitmap;
    private int touched;

    public int getX() { return x; }
    public int getY() { return y; }
    public Bitmap getBitmap() { return bitmap; }
    public int getTouched() { return touched; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }
    public void setTouched(int touched) { this.touched = touched; }

    public Trigger(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.touched= 0;
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2), null);
    }
    public void handleActionDown(double eventX, double eventY) {
        if (touched==0){
        if (eventX >= (x - bitmap.getWidth() / 2) &&
                (eventX <= (x + bitmap.getWidth() / 2))) {
            if (eventY >= (y - bitmap.getHeight() / 2) &&
                    (eventY <= (y + bitmap.getHeight() / 2))) {
                touched=1;
            } } } }
}
