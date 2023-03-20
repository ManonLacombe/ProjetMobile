package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToolBar myToolBar =(ToolBar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);
    }
}