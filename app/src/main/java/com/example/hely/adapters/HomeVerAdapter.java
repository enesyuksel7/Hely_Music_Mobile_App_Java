package com.example.hely.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hely.OturumActivity;
import com.example.hely.ParcaActivity;
import com.example.hely.R;
import com.example.hely.SplashScreen;
import com.example.hely.models.HomeVerModel;
import com.example.hely.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    public ParcaActivity parcaActivity;

    Context context;
    ArrayList<HomeVerModel> list;

    HomeClickListener homeClickListener;

    public interface HomeClickListener {
        void selectedItem(HomeVerModel homeVerModel);
    }


    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list, HomeClickListener homeClickListener) {
        this.context = context;
        this.list = list;
        this.homeClickListener = homeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getKapak()).into(holder.imageView);
        HomeVerModel homeVerModel = list.get(position);
        holder.imageView.setImageURI(Uri.parse(list.get(position).getKapak()));
        holder.name.setText(list.get(position).getAd());
        holder.sanatciAdi.setText(list.get(position).getSanatci());
        holder.rating.setText(list.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeClickListener.selectedItem(homeVerModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, sanatciAdi, rating;
        Button sozGoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.ver_txt);
            sanatciAdi = itemView.findViewById(R.id.home_ver_sanatci);
            rating = itemView.findViewById(R.id.txtRatingBar);


            sozGoster = itemView.findViewById(R.id.ver_button);


        }
    }
}
