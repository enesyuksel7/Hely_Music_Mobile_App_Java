package com.example.hely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    ImageView imageViewSS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageViewSS = findViewById(R.id.imageViewSS);
        Glide.with(this).asGif().load(R.drawable.splash_logo).into(imageViewSS);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, OturumActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}