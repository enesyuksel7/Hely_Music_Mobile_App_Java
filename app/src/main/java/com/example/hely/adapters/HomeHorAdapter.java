package com.example.hely.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hely.NavigationDrawer2;
import com.example.hely.ParcaActivity;
import com.example.hely.R;
import com.example.hely.models.HomeHorModel;
import com.example.hely.models.HomeVerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeHorAdapter extends RecyclerView.Adapter <HomeHorAdapter.ViewHolder>{

    /*Context context;
    List<HomeHorModel> list;*/


    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int  row_index = -1;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    /*public HomeHorAdapter(Context context, List<HomeHorModel> list) {
        this.context = context;
        this.list = list;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if(check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4", "Ay Şarkısı", "Saian", "5,0","HipHop"));
            homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4", "Baytar", "Sagopa Kajmer", "3.4","HipHop"));
            homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4", "İhtiyacım Yok", "Norm Ender", "4.6","HipHop"));
            homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4", "Uğurla Bahtiyarları", "Sagopa Kajmer", "3,6","HipHop"));
            homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4", "Ders Al", "Ceza", "5,0","HipHop"));

            updateVerticalRec.callBack(position, homeVerModels);
            check = false;

        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                if(position == 0){

                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                    db.collection("Kategoriler").document("HipHop").collection("Parcalar")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        for (QueryDocumentSnapshot document : task.getResult()){
                                            HomeVerModel m=  document.toObject(HomeVerModel.class);
                                            homeVerModels.add(m);
                                        }
                                        updateVerticalRec.callBack(position,homeVerModels);
                                    } else{

                                    }
                                }
                            });

                    updateVerticalRec.callBack(position,homeVerModels);

                }
                else if(position == 1){
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                    db.collection("Kategoriler").document("Rock").collection("Parcalar")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        for (QueryDocumentSnapshot document : task.getResult()){
                                            HomeVerModel m=  document.toObject(HomeVerModel.class);
                                            homeVerModels.add(m);
                                        }
                                        updateVerticalRec.callBack(position,homeVerModels);
                                    } else{

                                    }
                                }
                            });

                    updateVerticalRec.callBack(position,homeVerModels);

                }
                else if(position == 2){
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Baytar","Saian", "5,0","Rock"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Baytar","Sagopa Kajmer", "3.4","Rock"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","İhtiyacım Yok","Norm Ender", "4.6","Rock"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Rock"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Rock"));
                    updateVerticalRec.callBack(position,homeVerModels);

                }
                else if(position == 3){
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Klasik"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Baytar","Sagopa Kajmer", "3.4","Klasik"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","İhtiyacım Yok","Norm Ender", "4.6","Klasik"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Klasik"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Klasik"));
                    updateVerticalRec.callBack(position,homeVerModels);

                }
                else if(position == 4){
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Musiki"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Baytar","Sagopa Kajmer", "3.4","Musiki"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","İhtiyacım Yok","Norm Ender", "4.6","Musiki"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Musiki"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Musiki"));
                    updateVerticalRec.callBack(position,homeVerModels);

                }
                else if(position == 5){
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Arabesk"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Baytar","Sagopa Kajmer", "3.4","Arabesk"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","İhtiyacım Yok","Norm Ender", "4.6","Arabesk"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Arabesk"));
                    homeVerModels.add(new HomeVerModel("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/ay_sarkisi.jpg?alt=media&token=731ddbaf-8833-43f2-8751-024cd06ef5a4","Ay Şarkısı","Saian", "5,0","Arabesk"));

                    updateVerticalRec.callBack(position,homeVerModels);
                }

            }
        });

        if(select){
            if(position==0){
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select = false;
            }
        }
        else {
            if(row_index == position){
                holder.cardView.setBackgroundResource(R.drawable.change_bg);

            }
            else{
                holder.cardView.setBackgroundResource(R.drawable.default_bg);

            }
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_txt);
            cardView = itemView.findViewById(R.id.cardView);


        }
    }
}
