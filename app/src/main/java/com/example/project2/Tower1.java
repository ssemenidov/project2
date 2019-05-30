package com.example.project2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tower1 extends Tower {
    public Tower1(Context context,int x, int y) {
        super(x, y,1, 240,
                Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tower3), 150, 150, false),
                0L,70,new Arrow(context,x,y));

    }
}
