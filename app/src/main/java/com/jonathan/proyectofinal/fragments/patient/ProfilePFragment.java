package com.jonathan.proyectofinal.fragments.patient;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.ui.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePFragment extends Fragment {

    @BindView(R.id.button_logout)
    MaterialButton btn_logout;
    FirebaseAuth firebaseAuth;

    public static ProfilePFragment newInstance() {
        return new ProfilePFragment();
    }

    private IMainCarer mIMainCarer;


    public ProfilePFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @OnClick(R.id.btn_logout)
    @Optional
    public void logout(View view) {

        switch (view.getId()) {
            case R.id.button_logout:
                firebaseAuth.signOut();
                mIMainCarer.inflateFragment(getString(R.string.btn_logout));
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }

}
