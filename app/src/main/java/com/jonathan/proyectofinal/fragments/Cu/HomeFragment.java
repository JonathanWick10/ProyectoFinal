package com.jonathan.proyectofinal.fragments.Cu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jonathan.proyectofinal.R;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "HomeFragment";

    Button btn_heart,btn_manage, btn_diary, btn_test;
    Fragment activ=null;
    FragmentTransaction transaction;

    public HomeFragment() {
    }

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_home,container,false);
        btn_heart = view.findViewById(R.id.heart);
        btn_manage = view.findViewById(R.id.manage);
        btn_diary = view.findViewById(R.id.diary);
        btn_test = view.findViewById(R.id.test);
        btn_heart.setOnClickListener(this);
        btn_manage.setOnClickListener(this);
        btn_diary.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        return view;
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.heart:
                HeartFragment fragment = new HeartFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentHome, fragment);
                break;
            case R.id.manage:
                transaction = getChildFragmentManager().beginTransaction();
                break;
            case R.id.diary:

                break;
            case R.id.test:

                break;
        }
    }
}
