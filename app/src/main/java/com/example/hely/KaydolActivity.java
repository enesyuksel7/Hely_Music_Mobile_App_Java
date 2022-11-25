package com.example.hely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class KaydolActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button btnKaydol;
    TextView lblOturumAc;
    EditText tbAdSoyadK, tbEPostaK, tbParolaK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        tbAdSoyadK = findViewById(R.id.tbAdSoyadK);
        tbEPostaK = findViewById(R.id.tbEPostaK);
        tbParolaK = findViewById(R.id.tbParolaK);
        btnKaydol = findViewById(R.id.btnKaydol);
        lblOturumAc = findViewById(R.id.lblOturumAc);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String AdSoyad = tbAdSoyadK.getText().toString();
                String EPosta = tbEPostaK.getText().toString();
                String Parola = tbParolaK.getText().toString();

                Map<String, Object> kullanici = new HashMap<>();
                kullanici.put("AdSoyad", AdSoyad);
                kullanici.put("EPosta", EPosta);
                kullanici.put("Parola", Parola);

                db.collection("Kullanicilar")
                        .add(kullanici)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent i = new Intent(KaydolActivity.this, OturumActivity.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) { }
                        });
            }
        });

        lblOturumAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(KaydolActivity.this, OturumActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}