package com.example.onlinemusem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MuseumAdapter extends RecyclerView.Adapter<MuseumAdapter.MuseumViewHolder> {
    private List<MuseumItem> museumItems;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MuseumAdapter(Context context, List<MuseumItem> museumItems) {
        this.context = context;
        this.museumItems = museumItems;
    }

    @NonNull
    @Override
    public MuseumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_museum, parent, false);
        return new MuseumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseumViewHolder holder, int position) {
        int actualPosition = position % museumItems.size();
        MuseumItem museumItem = museumItems.get(actualPosition);

        holder.titleTextView.setText(museumItem.getTitle());
        holder.imageView.setImageURI(museumItem.getImageUri());

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(actualPosition, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class MuseumViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public MuseumViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}