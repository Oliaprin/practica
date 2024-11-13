package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button1 = findViewById(R.id.button1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button2 = findViewById(R.id.button2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button3 = findViewById(R.id.button3);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button4 = findViewById(R.id.button4);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button5 = findViewById(R.id.button5);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button6 = findViewById(R.id.button6);

        button1.setOnClickListener(v -> startActivity(new Intent(this, GameDetailsActivity.class)));
        button2.setOnClickListener(v -> startActivity(new Intent(this, DeveloperActivity.class)));
        button3.setOnClickListener(v -> startActivity(new Intent(this, PublisherActivity.class)));
        button4.setOnClickListener(v -> startActivity(new Intent(this, PlatformActivity.class)));
        button5.setOnClickListener(v -> startActivity(new Intent(this, GenreActivity.class)));
        button6.setOnClickListener(v -> startActivity(new Intent(this, ReviewActivity.class)));
    }
}
