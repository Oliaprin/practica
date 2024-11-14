package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GenreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        TextView genreName = findViewById(R.id.genre_name);
        TextView genreDescription = findViewById(R.id.genre_description);

        // Пример данных
        genreName.setText("Жанр: Экшен");
        genreDescription.setText("Описание: Жанр, сосредоточенный на динамичном игровом процессе.");
    }
}
