package com.example.ahmeddiab_180470;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Start extends AppCompatActivity {
    Weather weatherimage=new Weather();

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button start =(Button)findViewById(R.id.button4);
        Button weather =(Button)findViewById(R.id.button5);
        img=(ImageView)findViewById(R.id.weatherimage);
        img.setImageResource(weatherimage.getImgResource());
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this,MainActivity.class));
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this,Weather.class));
            }
        });
    }
}