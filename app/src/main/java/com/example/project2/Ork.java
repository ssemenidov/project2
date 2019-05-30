package com.example.project2;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ork extends Enemy  {
    private static final String TAG = "MyApp";
    public Ork(Context context , int x, int y) {
        super(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ork), 70, 70, false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deadork), 70, 70, false),
                x,y,3,6,15);

    }

}
