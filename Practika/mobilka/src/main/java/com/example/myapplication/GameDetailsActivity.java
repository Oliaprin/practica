package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        ImageView gameImage = findViewById(R.id.game_image);
        TextView gameName = findViewById(R.id.game_name);
        TextView gameGenre = findViewById(R.id.game_genre);
        TextView gameReleaseDate = findViewById(R.id.game_release_date);
        TextView gameRating = findViewById(R.id.game_rating);

        // Пример данных
        gameImage.setImageResource(R.drawable.sample_game); // Замените на ваше изображение
        gameName.setText("Пример игры");
        gameGenre.setText("Экшен");
        gameReleaseDate.setText("2023");
        gameRating.setText("9/10");
    }
}
