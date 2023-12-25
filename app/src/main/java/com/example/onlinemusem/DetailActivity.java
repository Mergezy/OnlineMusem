package com.example.onlinemusem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private static final long DOUBLE_CLICK_TIME_DELTA = 300;
    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailDescriptionTextView = findViewById(R.id.detailDescriptionTextView);

        // Получение данных из Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageResourceId = extras.getInt("imageResourceId", 0);
            String description = extras.getString("description", "");

            detailImageView.setImageResource(imageResourceId);
            detailDescriptionTextView.setText("");

            // Добавление слушателя для двойного нажатия на изображение
            detailDescriptionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long clickTime = System.currentTimeMillis();
                    if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                        // Двойное нажатие - открытие полноэкранного описания
                        openFullScreenDescription(description,imageResourceId);
                    }
                    lastClickTime = clickTime;
                }
            });
            detailImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long clickTime = System.currentTimeMillis();
                    if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                        // Двойное нажатие - открытие полноэкранного описания
                        openFullScreenImage(imageResourceId);
                    }
                    lastClickTime = clickTime;
                }
            });
        }
    }

    private void openFullScreenDescription(String description, int imageResourceId) {
        Intent intent = new Intent(this, FullScreenDescriptionActivity.class);
        intent.putExtra("description", description);
        intent.putExtra("imageResourceId", imageResourceId);
        startActivity(intent);
    }
    private void openFullScreenImage(int imageResourceId) {
        Intent intent = new Intent(this, FullScreenImageActivity.class);
        intent.putExtra("imageResourceId", imageResourceId);
        startActivity(intent);
    }
}