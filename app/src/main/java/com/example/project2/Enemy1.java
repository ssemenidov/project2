package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy1 extends Enemy  {
    private static final String TAG = "MyApp";
    public Enemy1(Context context ,int x, int y) {
        super(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.goblin3), 70, 70, false),
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.deadgoblin3), 70, 80, false),
        x,y,3,3,7);

    }

}
