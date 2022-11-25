package com.example.hely.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hely.R;
import com.example.hely.models.EditorModel;

import java.util.List;

public class EditorAdapter extends RecyclerView.Adapter<EditorAdapter.ViewHolder> {


    Context context;
    List<EditorModel> list;

    public EditorAdapter(Context context, List<EditorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.ratingBar.setRating(list.get(position).getRating());
        holder.comment.setText(list.get(position).getComment());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, comment;
        RatingBar ratingBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.editor_img);
            name = itemView.findViewById(R.id.editor_isim_txt);
            ratingBar = itemView.findViewById(R.id.editor_rating_bar);
            comment = itemView.findViewById(R.id.editor_yorum_txt);



        }
    }
}
