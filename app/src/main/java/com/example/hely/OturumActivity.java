package com.example.hely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hely.ui.yorum.YorumYapActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class OturumActivity extends AppCompatActivity {

    Button btnOturum;
    TextView lblHesapOlustur;
    EditText tbEPostaO, tbParolaO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oturum);

        tbEPostaO = findViewById(R.id.tbEPostaO);
        tbParolaO = findViewById(R.id.tbParolaO);
        btnOturum = findViewById(R.id.btnOturum);
        lblHesapOlustur = findViewById(R.id.lblHesapOlustur);

        getSupportActionBar().hide();

        btnOturum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OturumActivity.this, NavigationDrawer2.class);
                startActivity(i);
                String EPosta = tbEPostaO.getText().toString();
                String Parola = tbParolaO.getText().toString();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("Kullanicilar").whereEqualTo("EPosta",EPosta).whereEqualTo("Parola",Parola)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        data_local.AdSoyad = document.getString("AdSoyad");
                                        data_local.EPosta = document.getString("EPosta");
                                        data_local.Parola = document.getString("Parola");
                                        Toast.makeText(OturumActivity.this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                } else {
                                    Toast.makeText(OturumActivity.this, "Giriş başarısız!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        lblHesapOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OturumActivity.this, KaydolActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}