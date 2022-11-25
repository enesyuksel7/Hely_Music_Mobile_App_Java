package com.example.hely.ui.muzikCalar;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hely.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MuzikCalarFragment extends Fragment {

    ListView allMusicList; // listVIEW
    ArrayAdapter<String> musicArrayAdapter; // Adapter for music list
    String songs[]; // to storage song names;
    ArrayList<File> musics;

    public MuzikCalarFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_muzik_calar, container,false);
        allMusicList = rootView.findViewById(R.id.listViewM);

        Dexter.withActivity(this.getActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                // display music files on storage

                musics = findMusicFiles(Environment.getExternalStorageDirectory());
                songs = new String[musics.size()];
                for (int i = 0; i < musics.size(); i++) {
                    songs[i] = musics.get(i).getName();
                }

                // here you are passing songs in the adapter;
                musicArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, songs);
                //setting the adapter on listview

                allMusicList.setAdapter(musicArrayAdapter);

                allMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // passing the intent to the playeractivity

                        Intent intent = new Intent(getActivity(), MuzikCalarActivity.class);
                        intent.putExtra("songsList", musics);
                        intent.putExtra("position", position);
                        startActivity(intent);
                        // we are storing all the songs in the key songlist
                        // we are storing the position of songs in key position

                    }
                });



            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                // asking for permission

                permissionToken.continuePermissionRequest();

            }
        }).check();


        return rootView;
    }

    // creating an arraylist for music files available on sotrage

    private ArrayList<File> findMusicFiles(File file) {
        ArrayList<File> musicfileobject = new ArrayList<>();
        File[] files = file.listFiles();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (File currentFiles : files) {
            if (currentFiles.getName().length()>2 && currentFiles.getName().endsWith(".mp3") )
                if (currentFiles.isDirectory() && !currentFiles.isHidden()) {
                    musicfileobject.addAll(findMusicFiles(currentFiles));
                } else {
                    if (currentFiles.getName().endsWith(".mp3") || currentFiles.getName().endsWith(".mp4a") || currentFiles.getName().endsWith(".wav")) {
                        musicfileobject.add(currentFiles);
                    }
                }
        }

        return musicfileobject;
    }
}