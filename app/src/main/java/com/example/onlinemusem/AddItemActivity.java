package com.example.onlinemusem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddItemActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 1;

    private ImageView imageView;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        Button addButton = findViewById(R.id.addButton);
        imageView = findViewById(R.id.imageView);

        addButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (title.isEmpty() || description.isEmpty() || selectedImageUri == null) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            } else {
                // Возвращаем результат в MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", title);
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("imageUri", selectedImageUri.toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        imageView.setOnClickListener(v -> {
            // Открываем галерею для выбора изображения
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_IMAGE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            // Получаем выбранное изображение из галереи
            selectedImageUri = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
