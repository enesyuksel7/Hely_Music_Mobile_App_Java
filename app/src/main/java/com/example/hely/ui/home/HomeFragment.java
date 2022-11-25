package com.example.hely.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hely.KaydolActivity;
import com.example.hely.NavigationDrawer;
import com.example.hely.OturumActivity;
import com.example.hely.ParcaActivity;
import com.example.hely.ParcaFragment;
import com.example.hely.R;
import com.example.hely.adapters.HomeHorAdapter;
import com.example.hely.adapters.HomeVerAdapter;
import com.example.hely.adapters.UpdateVerticalRec;
import com.example.hely.models.HomeHorModel;
import com.example.hely.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVerticalRec, HomeVerAdapter.HomeClickListener {

    RecyclerView homeHorizontalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    RecyclerView homeVerticalRec;
    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container,false);

        homeHorizontalRec = rootView.findViewById(R.id.home_hor_rec);
        homeHorModelList = new ArrayList<>();
        homeHorModelList.add(new HomeHorModel(R.drawable.ic_baseline_photo_album_24,"HipHop"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ic_baseline_photo_album_24,"Rock"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ic_baseline_photo_album_24,"Caz"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ic_baseline_photo_album_24,"Dini"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ic_baseline_photo_album_24,"HipHop"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ic_baseline_photo_album_24,"HipHop"));

        homeHorAdapter = new HomeHorAdapter(this, getActivity(), homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);



        homeVerticalRec = rootView.findViewById(R.id.home_ver_rec);
        homeVerModelList = new ArrayList<>();

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList, this::selectedItem);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));



        return rootView;
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(), list, this::selectedItem);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);

    }

    @Override
    public void selectedItem(HomeVerModel homeVerModel) {
        //Toast.makeText(getActivity(), "Secili Item"+ homeVerModel.getName(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), ParcaActivity.class).putExtra("data", homeVerModel);
        getContext().startActivity(i);
    }
}