package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlatformActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform);

        ImageView platformImage = findViewById(R.id.platform_image);
        TextView platformName = findViewById(R.id.platform_name);
        TextView platformManufacturer = findViewById(R.id.platform_manufacturer);

        // Пример данных
        platformImage.setImageResource(R.drawable.sample_platform); // Замените на ваше изображение
        platformName.setText("Платформа: ПК");
        platformManufacturer.setText("Производитель: Компания ПК");
    }
}
