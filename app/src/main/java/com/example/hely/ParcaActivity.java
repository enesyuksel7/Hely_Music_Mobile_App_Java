package com.example.hely;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hely.models.HomeVerModel;
import com.example.hely.ui.yorum.YorumYapActivity;
import com.example.hely.ui.yorum.YorumlarActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ParcaActivity extends AppCompatActivity {
    TextView lblParcaAdi, lblTarih, lblSanatci, lblParcaSozleri;
    ImageButton btnGeriParca;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CollapsingToolbarLayout collapsing_toolbar;
    ImageView imgKapak;

    HomeVerModel homeVerModel;
    Intent intent;

    ImageView btn_mp3Oynat;
    SeekBar mp3_seekBar;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;



    FloatingActionButton fabOpenClose, fabSeslendir, fabYorumYap, fabTumYorumlar;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parca);


        getSupportActionBar().hide();

        imgKapak = findViewById(R.id.imgKapak);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        lblParcaAdi = findViewById(R.id.lblParcaAdi);
        lblTarih = findViewById(R.id.lblTarih);
        lblSanatci = findViewById(R.id.lblSanatci);
        lblParcaSozleri = findViewById(R.id.lblParcaSozleri);
        btnGeriParca = findViewById(R.id.btnGeriParca);


        fabOpenClose = findViewById(R.id.fab_open_close);
        fabSeslendir = findViewById(R.id.fab_seslendir);
        fabYorumYap = findViewById(R.id.fab_yorumyap);
        fabTumYorumlar = findViewById(R.id.fab_tumyorumlar);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //parcaID = fAuth.getCurrentUser().getUid();

        intent = getIntent();


        homeVerModel = (HomeVerModel) intent.getSerializableExtra("data");
        String gelenSarkiAd = homeVerModel.getAd();
        String gelenSarkiImg = homeVerModel.getKapak();

        DocumentReference documentReference1 = fStore.collection("Parcalar").document("6JOyGv0mKBs87f8zMUoK");
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String uri = documentSnapshot.getString("Kapak");
                Glide.with(ParcaActivity.this).load(uri).into(imgKapak);
            }
        });


        DocumentReference docRef = db.collection("Parcalar").document("6JOyGv0mKBs87f8zMUoK");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    String albumAd = document.getString("Album");
                    if (document.exists()) {
                        collapsing_toolbar.setTitle(albumAd);
                        lblParcaAdi.setText(document.getString("Ad"));
                        lblTarih.setText(document.getString("Yil"));
                        lblSanatci.setText(document.getString("Sanatci"));
                        lblParcaSozleri.setText(document.getString("Sozler"));
                    } else {

                    }
                } else {

                }
            }
        });




        btn_mp3Oynat = findViewById(R.id.btn_mp3Oynat);
        mp3_seekBar = findViewById(R.id.mp3_seekBar);

        mediaPlayer = new MediaPlayer();
        handler = new Handler();

        mp3_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                    mp3_seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_mp3Oynat.setOnClickListener(btnClickListen);



        btnGeriParca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ParcaActivity.this, NavigationDrawer2.class);
                startActivity(i);
            }
        });


        fabOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        fabSeslendir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        fabYorumYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent i = new Intent(ParcaActivity.this, YorumYapActivity.class);
                startActivity(i);
            }
        });
        fabTumYorumlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent i = new Intent(ParcaActivity.this, YorumlarActivity.class);
                startActivity(i);
            }
        });


        }

    private void animateFab(){
        if(isOpen){
            fabOpenClose.startAnimation(rotateForward);

            fabSeslendir.startAnimation(fabClose);
            fabYorumYap.startAnimation(fabClose);
            fabTumYorumlar.startAnimation(fabClose);

            fabSeslendir.setClickable(false);
            fabYorumYap.setClickable(false);
            fabTumYorumlar.setClickable(false);

            isOpen = false;
        }else{
            fabOpenClose.startAnimation(rotateBackward);

            fabSeslendir.startAnimation(fabOpen);
            fabYorumYap.startAnimation(fabOpen);
            fabTumYorumlar.startAnimation(fabOpen);

            fabSeslendir.setClickable(true);
            fabYorumYap.setClickable(true);
            fabTumYorumlar.setClickable(true);

            isOpen = true;
        }

    }

    private View.OnClickListener btnClickListen = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            btn_mp3Oynat.setImageResource(R.drawable.ic_baseline_pause_24);

            Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hely-1606.appspot.com/o/Saian%20-%20Ay%20%C5%9Eark%C4%B1s%C4%B1.mp3?alt=media&token=801b80e1-3168-4ad8-a5e9-d469ee35b5b0");
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.reset();

            try {
                mediaPlayer.setDataSource(ParcaActivity.this, uri);
            }catch (Exception e){
                e.printStackTrace();

            }

            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp){
                    mp3_seekBar.setMax(mp.getDuration());
                    mediaPlayer.start();
                    updateSeekbar();
                }
            });

            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    double ratio = percent / 100.0;
                    int bufferingLevel = (int)(mp.getDuration()*ratio);
                    mp3_seekBar.setSecondaryProgress(bufferingLevel);
                }
            });
        }
    };

    public void updateSeekbar(){
        int currPos = mediaPlayer.getCurrentPosition();
        mp3_seekBar.setProgress(currPos);

        runnable = new Runnable(){
            @Override
            public void run(){
                updateSeekbar();
            }
        };
        handler.postDelayed(runnable, 1000);
    }

}