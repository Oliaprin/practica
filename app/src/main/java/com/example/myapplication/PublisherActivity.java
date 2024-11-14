package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PublisherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);

        ImageView publisherImage = findViewById(R.id.publisher_image);
        TextView publisherName = findViewById(R.id.publisher_name);
        TextView publisherYearFounded = findViewById(R.id.publisher_year_founded);

        // Пример данных
        publisherImage.setImageResource(R.drawable.sample_publisher); // Замените на ваше изображение
        publisherName.setText("Издатель: Игроиздат");
        publisherYearFounded.setText("Год основания: 2000");
    }
}
