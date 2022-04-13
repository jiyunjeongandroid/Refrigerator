package com.example.helprefrigerator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {
    List<String> titles;
    List<String> dates;
    List<String> amounts;
    List<Integer> images;
    LayoutInflater inflater;

    public AdapterSearch(Context ctx, List<String> titles, List<String> dates,List<String> amounts, List<Integer> images) {
        this.titles = titles;
        this.dates = dates;
        this.amounts = amounts;
        this.images = images;
        this.inflater = LayoutInflater.from (ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate (R.layout.custom_search_layout,parent,false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText (titles.get (position));
        holder.date.setText (dates.get (position));
        holder.amount.setText (amounts.get (position));
        holder.gridIcon.setImageResource (images.get (position));
    }

    @Override
    public int getItemCount() {
        return titles.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView amount;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            title = itemView.findViewById (R.id.searched_name);
            date = itemView.findViewById (R.id.searched_date);
            amount = itemView.findViewById (R.id.searched_amount);
            gridIcon = itemView.findViewById (R.id.img_harvest);
        }
    }
}
