package com.cs3303.mycomet;
/*
   An app that will render a simple comet style animation based on the dimensions of the device's
   screen.
   @author: Andrew Lee
   @version: 03/30/20
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the width and height of our UI screen
        // for this we use the DisplayMetrics instance to get the general description about the
        // display size and so on
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)getApplicationContext().
                getSystemService(Context.WINDOW_SERVICE);
        //windowManager will communicate using the the getSystemService to get the screen dimensions
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        // this tells the window manager to create a new window for the ui
        setContentView(new CometAnimation(this));
    }
}
