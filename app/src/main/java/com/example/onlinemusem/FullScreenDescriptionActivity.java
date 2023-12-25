package com.example.onlinemusem;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FullScreenDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_description);

        ImageView backgroundImageView = findViewById(R.id.backgroundImageView);
        TextView fullScreenDescriptionTextView = findViewById(R.id.fullScreenDescriptionTextView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String description = extras.getString("description", "");
            int imageResourceId = extras.getInt("imageResourceId", 0);

            backgroundImageView.setImageResource(imageResourceId);
            fullScreenDescriptionTextView.setText(description);

            // Добавление слушателя для закрытия активити при нажатии на описание
            fullScreenDescriptionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
