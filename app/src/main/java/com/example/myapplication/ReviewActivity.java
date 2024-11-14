package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        TextView reviewContent = findViewById(R.id.review_content);
        TextView reviewerName = findViewById(R.id.reviewer_name);
        TextView reviewDate = findViewById(R.id.review_date);
        TextView reviewRating = findViewById(R.id.review_rating);

        // Пример данных
        reviewContent.setText("Отличная игра! Очень понравилась механика.");
        reviewerName.setText("Имя: Игрок123");
        reviewDate.setText("Дата: 29.10.2024");
        reviewRating.setText("Рейтинг: 10/10");
    }
}
