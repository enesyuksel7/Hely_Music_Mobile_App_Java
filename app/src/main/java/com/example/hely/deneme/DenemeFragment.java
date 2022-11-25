package com.example.hely.deneme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hely.R;

public class DenemeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_deneme, container,false);

        final RatingBar ratingRatingBar = (RatingBar)rootView.findViewById(R.id.rating_rating_bar11);
        Button submitButton = (Button)rootView.findViewById(R.id.submit_button);
        final TextView ratingDisplayTextView = (TextView)rootView.findViewById(R.id.rating_display_text_View);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDisplayTextView.setText("Your rating is: " + ratingRatingBar.getRating());
            }
        });
        return rootView;
    }
}