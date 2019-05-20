package com.example.project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import static java.security.AccessController.getContext;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClick1(View view) {
        Intent intent = new Intent(MenuActivity.this, LevelActivity.class);
        intent.putExtra("level", 1);
        startActivity(intent);
        Log.i(TAG,"1");
    }
    public void onClick2(View view) {
        Intent intent = new Intent(MenuActivity.this, LevelActivity.class);
        intent.putExtra("level", 2);
        startActivity(intent);
    }
    public void onClick3(View view) {
        Intent intent = new Intent(MenuActivity.this, LevelActivity.class);
        intent.putExtra("level", 3);
        startActivity(intent);
    }
    public void onClick4(View view) {
        Intent intent = new Intent(MenuActivity.this, LevelActivity.class);
        intent.putExtra("level", 4);
        startActivity(intent);
    }

    public void onClick5(View view) {
        Intent intent = new Intent(MenuActivity.this, LevelActivity.class);
        intent.putExtra("level", 5);
        startActivity(intent);
    }
    public void onClick6(View view) {
        Intent intent = new Intent(MenuActivity.this, LevelActivity.class);
        intent.putExtra("level", 6);

        startActivity(intent);
    }
    public  void onClickFinish(View view){
        super.finish();
    }
}
