package com.jonathan.proyectofinal.fragments.hp;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

import java.io.FileReader;

public class InformationPSFragment extends Fragment {

    TabLayout tabs;
    TabItem tabPatientInfo, nearbyhospital;
    ViewPager viewPager;
    Adapter adapter;
    String text1, text2;

    private IMainCarer iMainHealthProfessional;

    public InformationPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ps_information, container, false);
        tabPatientInfo = view.findViewById(R.id.ps_tab_info_patient);
        nearbyhospital = view.findViewById(R.id.ps_tab_info_carer);
        tabs = view.findViewById(R.id.ps_tabs_info);
        viewPager = view.findViewById(R.id.containerPageInformationPS);
        InformationPatientPSFragment fragment = new InformationPatientPSFragment();
        SetUpViewPager(viewPager, tabs, fragment);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPager, TabLayout tabs, Fragment fragment) {

        if (fragment != null) {
            adapter = new Adapter(getChildFragmentManager());
            tabs.setupWithViewPager(viewPager);
            viewPager.setAdapter(adapter);
            tabs.getTabAt(0);
            tabs.getTabAt(1);
        }

    }


    public class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    InformationPatientPSFragment informationPatientPSFragment = new InformationPatientPSFragment();
                    return informationPatientPSFragment;
                case 1:
                    InformationCarerPSFragment informationCarerPSFragment = new InformationCarerPSFragment();
                    return informationCarerPSFragment;

            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    text1 = getString(R.string.patient);
                    iMainHealthProfessional.inflateFragment(getString(R.string.patient));
                    return text1;
                case 1:
                    text2 = getString(R.string.carer);
                    iMainHealthProfessional.inflateFragment(getString(R.string.carer));
                    return text2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainHealthProfessional = (IMainCarer) getActivity();
    }
}
