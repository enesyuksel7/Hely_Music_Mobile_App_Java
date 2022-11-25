package com.example.hely.ui.yorum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.hely.R;
import com.example.hely.data_local;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class YorumYapActivity extends AppCompatActivity {

    EditText yorum_yap_yorum_edtTxt;
    ImageView yorum_yap_ses_al_btn;
    RatingBar yorum_yap_rating_bar;
    Button yorum_yap_kaydet_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorum_yap);

        yorum_yap_yorum_edtTxt = findViewById(R.id.yorum_yap_yorum_edtTxt);
        yorum_yap_ses_al_btn = findViewById(R.id.yorum_yap_ses_al_btn);
        yorum_yap_rating_bar = findViewById(R.id.yorum_yap_rating_bar);
        yorum_yap_kaydet_btn = findViewById(R.id.yorum_yap_kaydet_btn);

        yorum_yap_ses_al_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tıklayın ve konuşun!");
                startActivityForResult(intent, 100);
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //txtYorumKisi.setText(data_local.AdSoyad);

        yorum_yap_kaydet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Kisi = data_local.AdSoyad;
                String Yorum = yorum_yap_yorum_edtTxt.getText().toString();
                Float Derece = yorum_yap_rating_bar.getRating();

                Map<String, Object> yorum = new HashMap<>();
                yorum.put("Kisi", Kisi);
                yorum.put("Yorum", Yorum);
                yorum.put("Derece", Derece);

                db.collection("Parcalar").document("6JOyGv0mKBs87f8zMUoK").collection("Yorumlar")
                        .add(yorum)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(YorumYapActivity.this, "Yorum kaydedildi.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) { }
                        });
            }
        });

        yorum_yap_ses_al_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Düşüncelerin heyecan verici! Konuşmaya devam et..");
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK){
            yorum_yap_yorum_edtTxt.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
        }
    }
}