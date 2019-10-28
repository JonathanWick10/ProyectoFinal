package com.jonathan.proyectofinal.fragments.hp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.carer.MemorizameFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class TherapyPSFragment extends Fragment {

    TabLayout tabs;
    TabItem tabPatientInfo, nearbyhospital,tabMemorizame;
    ViewPager viewPager;
    Adapter adapter;
    String text1, text2, text3;

    private IMainCarer iMainHealthProfessional;

    public TherapyPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //   return inflater.inflate(R.layout.fragment_ps_therapy, container, false);


        View view = inflater.inflate(R.layout.fragment_ps_therapy, container, false);
        tabPatientInfo = view.findViewById(R.id.ps_tab_therapy_cognitive);
        nearbyhospital = view.findViewById(R.id.ps_tab_therapy_motor);
        tabMemorizame=view.findViewById(R.id.ps_tab_therapy_memorizame);
        tabs = view.findViewById(R.id.ps_tabs_therapy);
        viewPager = view.findViewById(R.id.containerPageTherapyPS);
        TherapyPSFragment fragment = new TherapyPSFragment();
        SetUpViewPager(viewPager, tabs, fragment);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPager, TabLayout tabs, Fragment fragment) {

        if (fragment != null) {
            adapter = new Adapter(getChildFragmentManager());
            tabs.setupWithViewPager(viewPager);
            viewPager.setAdapter(adapter);
            tabs.getTabAt(0).setIcon(R.drawable.ic_lightbulb_outline_black);
            tabs.getTabAt(1).setIcon(R.drawable.ic_accessibility_black);
            tabs.getTabAt(2).setIcon(R.drawable.ic_brain_black);
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
                    CognitiveTherapyPSFragment cognitiveTherapyPSFragment = new CognitiveTherapyPSFragment();
                    return cognitiveTherapyPSFragment;
                case 1:
                    MotorTherapyPSFragment motorTherapyPSFragment = new MotorTherapyPSFragment();
                    return motorTherapyPSFragment;

                case 2:
                    MemorizameFragment memorizameFragment = new MemorizameFragment();
                    return memorizameFragment;

            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    text1 = getString(R.string.cognitive);
                    iMainHealthProfessional.inflateFragment(getString(R.string.cognitive));
                    return text1;
                case 1:
                    text2 = getString(R.string.motor);
                    iMainHealthProfessional.inflateFragment(getString(R.string.motor));
                    return text2;

                case 2:
                    text3=getString(R.string.menu_memorizame);
                    iMainHealthProfessional.inflateFragment(getString(R.string.menu_memorizame));
                    return text3;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainHealthProfessional = (IMainCarer) getActivity();
    }
}
