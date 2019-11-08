package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jonathan.proyectofinal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesChildFragment extends Fragment {

    @BindView(R.id.ratingBarMemoPlaces)
    RatingBar ratingBar;
    @BindView(R.id.data_rating_memoplaces)
    TextView textViewRatign;


    public PlacesChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_places_child, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ratingBar.setOnRatingBarChangeListener(mOnRatingChangeListener);
    }

    private RatingBar.OnRatingBarChangeListener mOnRatingChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            switch ((int) ratingBar.getRating()) {
                case 1:
                    //ratingTxt.setText("Very bad");
                    textViewRatign.setText("1");
                    break;
                case 2:
                    //ratingTxt.setText("Need some improvement");
                    textViewRatign.setText("2");
                    break;
                case 3:
                    //ratingTxt.setText("Good");
                    textViewRatign.setText("3");
                    break;
                case 4:
                    //ratingTxt.setText("Great");
                    textViewRatign.setText("4");
                    break;
                case 5:
                    //ratingTxt.setText("Awesome. I love it");
                    textViewRatign.setText("5");
                    break;
                default:
                    textViewRatign.setText("");
            }
        }
    };
}
