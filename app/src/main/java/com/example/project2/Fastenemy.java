package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Fastenemy extends Enemy  {
    private static final String TAG = "MyApp";
    public Fastenemy(Context context , int x, int y) {
        super(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf), 80, 65, false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deadwolf), 80, 65, false),
                x,y,6,2,7);

    }

}
