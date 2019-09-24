package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jonathan.proyectofinal.R;

public class EmergencyFragment extends Fragment implements View.OnClickListener{

    Button btn_nearby,btn_call;

    private IMainCarer mIMainCarer;

    public EmergencyFragment() {
    }

    public EmergencyFragment(int contentLayoutId) { super(contentLayoutId); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_emergency,container,false);
        btn_nearby = view.findViewById(R.id.btn_hospital);
        btn_call = view.findViewById(R.id.btn_emergency);
        btn_nearby.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_emergency:
                mIMainCarer.inflateFragment(getString(R.string.Nearby_hospitals));
                break;
            case R.id.btn_hospital:
                mIMainCarer.inflateFragment(getString(R.string.Emergency_Contacts));
                break;
        }
    }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mIMainCarer = (IMainCarer) getActivity();
        }
}
