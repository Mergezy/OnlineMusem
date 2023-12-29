package com.example.onlinemusem;

import android.net.Uri;

public class MuseumItem {
    private String title;
    private String description;
    private Uri imageUri;

    public MuseumItem(String title, String description, Uri imageUri) {
        this.title = title;
        this.description = description;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
