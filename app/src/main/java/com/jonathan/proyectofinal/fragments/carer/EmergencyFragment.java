package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class EmergencyFragment extends Fragment {

    private IMainCarer mIMainCarer;
    FrameLayout callemergency,nearbyhospital;
    View view1,view2;
    TextView tvcall, tvhospital;
    public EmergencyFragment() {
    }

    public EmergencyFragment(int contentLayoutId) { super(contentLayoutId); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_emergency,container,false);
        callemergency = view.findViewById(R.id.callemergency);
        nearbyhospital = view.findViewById(R.id.nearbyhospital);
        view1 = view.findViewById(R.id.view1);
        view2 = view.findViewById(R.id.view2);
        tvcall = view.findViewById(R.id.tvcall);
        tvhospital = view.findViewById(R.id.tvhospital);
        callemergency.setOnClickListener(clik);
        nearbyhospital.setOnClickListener(clik);

        //LOAD PAGE FOR FIRST
        loadPage(new CallEmergencyFragment());
        tvcall.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        return view;
    }

    //ONCLICK LISTENER
    public View.OnClickListener clik = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.callemergency:
                    //ONSELLER CLICK
                    //LOAD SELLER FRAGMENT CLASS
                    loadPage(new CallEmergencyFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvcall.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    tvhospital.setTextColor(getActivity().getResources().getColor(R.color.gray));

                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                    mIMainCarer.inflateFragment(getString(R.string.emergency_contacts));
                    break;
                case R.id.nearbyhospital:
                    loadPage(new NearbyHospitalFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvcall.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    tvhospital.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));

                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    mIMainCarer.inflateFragment(getString(R.string.nearby_hospitals));
                    break;
            }
        }
    };

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mIMainCarer = (IMainCarer) getActivity();
        }

    //LOAD PAGE FRAGMENT METHOD
    private boolean loadPage(Fragment fragment) {

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerpage, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}