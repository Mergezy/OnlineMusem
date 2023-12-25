package com.example.onlinemusem;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenImageActivity extends AppCompatActivity {

    private ImageView imageFullScreen;
    private Button btnZoomIn;
    private Button btnZoomOut;
    private float scale = 1f;

    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        imageFullScreen = findViewById(R.id.imageFullScreen);
        btnZoomIn = findViewById(R.id.btnZoomIn);
        btnZoomOut = findViewById(R.id.btnZoomOut);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageResourceId = extras.getInt("imageResourceId", 0);
            imageFullScreen.setImageResource(imageResourceId);

            btnZoomIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomIn();
                }
            });

            btnZoomOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomOut();
                }
            });

            scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
            gestureDetector = new GestureDetector(this, new GestureListener());

            imageFullScreen.post(new Runnable() {
                @Override
                public void run() {
                    // Вычисляем координаты центра
                    float centerX = imageFullScreen.getWidth() * 0.5f;
                    float centerY = imageFullScreen.getHeight() * 0.5f;

                    // Смещаем так, чтобы центр оставался по центру
                    imageFullScreen.setTranslationX(imageFullScreen.getWidth() * 0.5f - centerX);
                    imageFullScreen.setTranslationY(imageFullScreen.getHeight() * 0.5f - centerY);
                }
            });

            imageFullScreen.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    scaleGestureDetector.onTouchEvent(event);
                    gestureDetector.onTouchEvent(event);
                    return true;
                }
            });
        }
    }

    private void zoomIn() {
        scale *= 1.2f;
        applyScaleAndTranslation();
    }

    private void zoomOut() {
        scale /= 1.2f;
        applyScaleAndTranslation();
    }

    private void applyScaleAndTranslation() {
        // Учтите текущий масштаб и смещение
        imageFullScreen.setScaleX(scale);
        imageFullScreen.setScaleY(scale);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            applyScaleAndTranslation();
            return true;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // Обработка листания
            imageFullScreen.setTranslationX(imageFullScreen.getTranslationX() - distanceX);
            imageFullScreen.setTranslationY(imageFullScreen.getTranslationY() - distanceY);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            // Обработка двойного нажатия
            if (scale > 1.0f) {
                scale = 1.0f;
            } else {
                scale = 2.0f;
            }
            applyScaleAndTranslation();
            return true;
        }
    }
}
