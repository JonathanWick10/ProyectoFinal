package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class HomeFragment extends Fragment {

    CardView heart, manage, diary;

    private IMainCarer mIMainCarer;

    public HomeFragment() {
    }

    public HomeFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_home,container,false);
        /*
        btn_heart = view.findViewById(R.id.heart);
        btn_manage = view.findViewById(R.id.manage);
        btn_diary = view.findViewById(R.id.diary);
        btn_heart.setOnClickListener(this);
        btn_manage.setOnClickListener(this);
        btn_diary.setOnClickListener(this);
        */
        heart = view.findViewById(R.id.heart);
        manage = view.findViewById(R.id.manage);
        diary = view.findViewById(R.id.diary);

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.my_care));
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.manage));
            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.diary));
            }
        });




        return view;
    }
    /*
    public void onClick(View view){
        switch (view.getId()){
            case R.id.heart:
                mIMainCarer.inflateFragment(getString(R.string.my_care));
                break;
            case R.id.manage:
                mIMainCarer.inflateFragment(getString(R.string.manage));
                break;
            case R.id.diary:
                mIMainCarer.inflateFragment(getString(R.string.diary));
                break;
        }
    }
    */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}
