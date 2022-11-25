package com.example.hely.ui.yorum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hely.R;

import java.util.Locale;

public class SeslendirActivity extends AppCompatActivity {

    EditText seslendir_edtTxt;
    Button seslendir_btn;
    TextToSpeech t1;

    Locale trlocale= new Locale("tr","TR");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seslendir);

        seslendir_edtTxt = findViewById(R.id.seslendir_edtTxt);
        seslendir_btn = findViewById(R.id.seslendir_btn);

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR)
                    t1.setLanguage(trlocale);
            }
        });

        seslendir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = seslendir_edtTxt.getText().toString();
                t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }
}