package com.example.hely.ui.yorum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hely.ParcaActivity;
import com.example.hely.R;
import com.example.hely.adapters.YorumAdapter;
import com.example.hely.models.YorumModel;

import java.util.ArrayList;
import java.util.List;

public class YorumlarActivity extends AppCompatActivity {

    ImageButton btnGeriYorumlar;
    ImageView yorumlarImage;
    TextView yorumlarParcaAdi;


    RecyclerView recyclerView;
    List<YorumModel> yorumModels;
    YorumAdapter yorumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorumlar);

        yorumlarImage = findViewById(R.id.yorumlar_img);
        yorumlarParcaAdi = findViewById(R.id.yorumlar_isim_txt);

        btnGeriYorumlar = findViewById(R.id.btnGeriYorumlar);

        //yorumlarImage.setImageResource(R.drawable.ay_sarkisi);
        //yorumlarParcaAdi.setText("ay şarkısı");

        btnGeriYorumlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ParcaActivity.class);
                view.getContext().startActivity(i);
            }
        });


        recyclerView = findViewById(R.id.yorumlar_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        yorumModels = new ArrayList<>();

        yorumModels.add(new YorumModel("Tuana Kaya", "Sagopa bir şeyler anlatıyor, dinlerken betimletiyor yoksa yine mi bir Story Telling!", 3.5F));
        yorumModels.add(new YorumModel("Hilal Seyhan", "Sagopa yine bildiğimiz gibi, bu parça bir harika!", 4.0F));
        yorumModels.add(new YorumModel("Nurhanım Hamdi", "Sagopa şaheseri. Öyle bir şarkıdır ki Sagopa şimdi denese bile bir daha böyle bir şey çıkmayacak!", 4.5F));
        yorumModels.add(new YorumModel("Enes Yüksel", "Negatif bir izlenim veren bu parça haftanın trendlerine girmiş durumda!", 2.0F));



        yorumAdapter = new YorumAdapter(this, yorumModels);
        recyclerView.setAdapter(yorumAdapter);

        yorumAdapter.notifyDataSetChanged();




    }
}