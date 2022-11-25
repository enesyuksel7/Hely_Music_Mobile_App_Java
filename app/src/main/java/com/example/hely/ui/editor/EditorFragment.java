package com.example.hely.ui.editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hely.R;
import com.example.hely.adapters.EditorAdapter;
import com.example.hely.models.EditorModel;

import java.util.ArrayList;
import java.util.List;


public class EditorFragment extends Fragment {

    RecyclerView recyclerView;
    List<EditorModel> editorModels;
    EditorAdapter editorAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_editor, container,false);

        recyclerView = rootView.findViewById(R.id.editor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        editorModels = new ArrayList<>();

        editorModels.add(new EditorModel(R.drawable.ay_sarkisi, "Ay Şarkısı", 3.5F, "Saian SS yine bildiğimiz gibi, bu parça bir harika!"));
        editorModels.add(new EditorModel(R.drawable.yedinci_ev, "Sevsene Beni", 4.0F, "Pozitif bir izlenim veren bu parça haftanın trendlerine girmiş durumda!"));
        editorModels.add(new EditorModel(R.drawable.butun_ayalarin_ortasinda, "Yol", 2.5F, "Kayra bir şeyler anlatıyor, dinlerken betimletiyor yoksa yine mi bir Story Telling!"));
        editorModels.add(new EditorModel(R.drawable.bpg, "Karikatür Komedya", 5.0F, "Sagopa şaheseri. Öyle bir şarkıdır ki Sagopa şimdi denese bile bir daha böyle bir şey çıkmayacak!"));

        editorAdapter = new EditorAdapter(getContext(), editorModels);
        recyclerView.setAdapter(editorAdapter);

        editorAdapter.notifyDataSetChanged();




        return rootView;
    }
}