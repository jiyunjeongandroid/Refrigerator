package com.example.helprefrigerator;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    private OnFoodListener mOnFoodListener;

    public Adapter(Context ctx, List<String>titles, List<Integer> images, OnFoodListener onFoodListener) {
        this.titles = titles;
        this.images = images;
        this.mOnFoodListener = onFoodListener;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate (R.layout.custom_grid_layout,parent,false);
        return new ViewHolder (view, mOnFoodListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText (titles.get (position));
        holder.gridIcon.setImageResource (images.get (position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView gridIcon;
        OnFoodListener onFoodListener;

        public ViewHolder(@NonNull View itemView,OnFoodListener onFoodListener) {
            super (itemView);
            title = itemView.findViewById (R.id.grid_name);
            gridIcon = itemView.findViewById (R.id.grid_img);
            this.onFoodListener = onFoodListener;

            itemView.setOnClickListener (this);
        }

        @Override
        public void onClick(View view) {
            onFoodListener.onFoodClick (getAdapterPosition ());
        }
    }

    public interface OnFoodListener {
        void onFoodClick(int position);
    }
}
