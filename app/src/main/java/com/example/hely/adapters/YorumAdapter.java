package com.example.hely.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hely.R;
import com.example.hely.models.YorumModel;

import java.util.List;

public class YorumAdapter extends RecyclerView.Adapter<YorumAdapter.ViewHolder>{

    Context context;
    List<YorumModel> list;

    public YorumAdapter(Context context, List<YorumModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YorumAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.yorum_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.yorumlarKisi.setText(list.get(position).getYorumlarKisi());
        holder.yorumlarListeYorum.setText(list.get(position).getYorumlarListeYorum());
        holder.yorumlarRating.setRating(list.get(position).getYorumlarRating());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView yorumlarKisi, yorumlarListeYorum;
        RatingBar yorumlarRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            yorumlarKisi = itemView.findViewById(R.id.yorumlar_kisi_txt);
            yorumlarListeYorum = itemView.findViewById(R.id.yorumlar_yorum_txt);
            yorumlarRating = itemView.findViewById(R.id.yorumlar_rating_bar);

        }
    }
}
