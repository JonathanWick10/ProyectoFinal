package com.jonathan.proyectofinal.fragments.patient;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.carer.GeneralInformationFragment;
import com.jonathan.proyectofinal.fragments.carer.PhasesEAFragment;
import com.jonathan.proyectofinal.fragments.games.PhysicalExecise;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePFragment extends Fragment {

    public static HomePFragment newInstance() {
        return new HomePFragment();
    }


    public HomePFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildStructure(view);
    }

    // Build the fragment structure
    private void buildStructure(View view) {
        ViewPager viewPager = view.findViewById(R.id.view_pager_home);
        PatientFragmentPageAdapter adapter = new PatientFragmentPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        TabLayout tabLayout = view.findViewById(R.id.tabs_home);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_lightbulb_outline_black);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_accessibility_black);
    }

    private class PatientFragmentPageAdapter extends FragmentPagerAdapter {

        public PatientFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CognitiveChildFragment();
                case 1:
                    return  new PhysicalExecise();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    String tab1 = getString(R.string.tab_cognitive_exercises);
                    return tab1;
                case 1:
                    String tab2 = getString(R.string.tab_motor_exercises);
                    return tab2;
            }
            return null;
        }
    }
}