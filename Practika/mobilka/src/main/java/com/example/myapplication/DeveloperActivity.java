package com.example.myapplication;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        ImageView developerImage = findViewById(R.id.developer_image);
        TextView developerName = findViewById(R.id.developer_name);
        TextView developerBirthYear = findViewById(R.id.developer_birth_year);

        // Пример данных
        developerImage.setImageResource(R.drawable.sample_developer); // Замените на ваше изображение
        developerName.setText("Разработчик: Игродел Игр");
        developerBirthYear.setText("Год рождения: 1980");
    }
}
