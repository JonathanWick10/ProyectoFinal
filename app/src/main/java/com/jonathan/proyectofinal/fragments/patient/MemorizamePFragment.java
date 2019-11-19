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

/**
 * A simple {@link Fragment} subclass.
 */
public class MemorizamePFragment extends Fragment {

    public static MemorizamePFragment newInstance() {
        return new MemorizamePFragment();
    }


    public MemorizamePFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memorizame, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildStructure(view);
    }

    // Build the fragment structure
    private void buildStructure(View view) {
        ViewPager viewPager = view.findViewById(R.id.view_pager_memorizame);
        MemorizamePFragment.PatientFragmentPageAdapter adapter = new PatientFragmentPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        TabLayout tabLayout = view.findViewById(R.id.tabs_memorizame);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_people_black);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_domain_black);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_pets_black);
    }

    private class PatientFragmentPageAdapter extends FragmentPagerAdapter {

        public PatientFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FamilyChildFragment();
                case 1:
                    return new HomeChildFragment();
                case 2:
                    return new PlacesChildFragment();
                case 3:
                    return new PetsChildFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_family_questions);
                case 1:
                    return getString(R.string.tab_home_questions);
                case 2:
                    return getString(R.string.tab_places_questions);
                case 3:
                    return getString(R.string.tab_pets_questions);
            }
            return null;
        }
    }
}