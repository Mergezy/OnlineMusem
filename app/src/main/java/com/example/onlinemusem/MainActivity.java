package com.example.onlinemusem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MuseumAdapter museumAdapter;
    private List<MuseumItem> museumItems;

    private static final int REQUEST_CODE_ADD_ITEM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        museumItems = generateSampleData();
        museumAdapter = new MuseumAdapter(this, museumItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(museumAdapter);

        museumAdapter.setOnItemClickListener((position, view) -> {
            MuseumItem selectedItem = museumItems.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("imageUri", selectedItem.getImageUri().toString());
            intent.putExtra("description", selectedItem.getDescription());
            startActivity(intent);
        });

        // Добавление слушателя для кнопки "+"
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent addItemIntent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(addItemIntent, REQUEST_CODE_ADD_ITEM);
        });
    }

    // Метод вызывается при получении результата от другой активности
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_ITEM && resultCode == RESULT_OK && data != null) {
            // Получение данных из AddItemActivity и добавление нового элемента в список
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            Uri imageUri = Uri.parse(data.getStringExtra("imageUri"));

            MuseumItem newItem = new MuseumItem(title, description, imageUri);
            museumItems.add(newItem);
            museumAdapter.notifyDataSetChanged();
        }
    }

    private List<MuseumItem> generateSampleData() {
        List<MuseumItem> items = new ArrayList<>();
        items.add(new MuseumItem("Painting 1", "Description 1", Uri.parse("android.resource://" + getPackageName() + "/" + R.mipmap.images)));
        items.add(new MuseumItem("Икона", "Описание иконы...", Uri.parse("android.resource://" + getPackageName() + "/" + R.mipmap.ikon)));
        return items;
    }
}
