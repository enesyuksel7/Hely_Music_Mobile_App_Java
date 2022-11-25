package com.example.hely;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.common.collect.Maps;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AyarlarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AyarlarFragment extends Fragment {

    Button btnLokasyon;
    EditText tbAdSoyadS, tbEPostaS, tbParolaS, tbLokasyon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AyarlarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AyarlarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AyarlarFragment newInstance(String param1, String param2) {
        AyarlarFragment fragment = new AyarlarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ayarlar, container, false);

        View rootView = inflater.inflate(R.layout.fragment_ayarlar, container, false);

        Intent intent = new Intent(getActivity(), MapsActivity.class);
        btnLokasyon = (Button) rootView.findViewById(R.id.btnLokasyon);
        tbAdSoyadS = (EditText) rootView.findViewById(R.id.tbAdSoyadS);
        tbEPostaS = (EditText) rootView.findViewById(R.id.tbEPostaS);
        tbParolaS = (EditText) rootView.findViewById(R.id.tbParolaS);
        tbLokasyon = (EditText) rootView.findViewById(R.id.tbLokasyon);
        tbAdSoyadS.setText(data_local.AdSoyad);
        tbEPostaS.setText(data_local.EPosta);
        tbParolaS.setText(data_local.Parola);

        tbAdSoyadS.setEnabled(false);
        tbEPostaS.setEnabled(false);
        tbParolaS.setEnabled(false);
        tbLokasyon.setEnabled(false);
        btnLokasyon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        return rootView;

    }
}