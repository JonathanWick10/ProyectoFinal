package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.api.Distribution;
import com.jonathan.proyectofinal.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CognitiveChildFragment extends Fragment {

    LinearLayout expandableView;
    Button btnExpand;
    CardView cardActivity;
    RatingBar ratingBar;
    TextView ratingTxt;

    /*
    private static final String ARGUMENT_POSITION = "argument_position";

    public static CognitiveChildFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        CognitiveChildFragment fragment = new CognitiveChildFragment();
        fragment.setArguments(args);
        return fragment;
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cognitive_child, container, false);

    }

    private RatingBar.OnRatingBarChangeListener mOnRatingChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            switch ((int) ratingBar.getRating()) {
                case 1:
                    //ratingTxt.setText("Very bad");
                    ratingTxt.setText("1");
                    break;
                case 2:
                    //ratingTxt.setText("Need some improvement");
                    ratingTxt.setText("2");
                    break;
                case 3:
                    //ratingTxt.setText("Good");
                    ratingTxt.setText("3");
                    break;
                case 4:
                    //ratingTxt.setText("Great");
                    ratingTxt.setText("4");
                    break;
                case 5:
                    //ratingTxt.setText("Awesome. I love it");
                    ratingTxt.setText("5");
                    break;
                default:
                    ratingTxt.setText("");
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        TextView textView = view.findViewById(R.id.tv_dashboard);
        int position =getArguments().getInt(ARGUMENT_POSITION, -1);
        textView.setText(position == 0 ? R.string.do_not_stop_believing : R.string.a8);
        */
        expandableView = view.findViewById(R.id.expandableView);
        btnExpand = view.findViewById(R.id.btnExpand);
        cardActivity = view.findViewById(R.id.cardActivity);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingTxt = view.findViewById(R.id.data_rating);
        ratingBar.setOnRatingBarChangeListener(mOnRatingChangeListener);

        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardActivity, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black);
                } else {
                    TransitionManager.beginDelayedTransition(cardActivity, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnExpand.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black);
                }
            }
        });

        /*
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //ratingTxt.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        //ratingTxt.setText("Very bad");
                        ratingBar.setNumStars(1);
                        break;
                    case 2:
                        //ratingTxt.setText("Need some improvement");
                        ratingBar.setNumStars(2);
                        break;
                    case 3:
                        //ratingTxt.setText("Good");
                        ratingBar.setNumStars(3);
                        break;
                    case 4:
                        //ratingTxt.setText("Great");
                        ratingBar.setNumStars(4);
                        break;
                    case 5:
                        //ratingTxt.setText("Awesome. I love it");
                        ratingBar.setRating(5);
                        break;
                    default:
                        //ratingTxt.setText("");
                }
            }
        });
        */
    }
}
