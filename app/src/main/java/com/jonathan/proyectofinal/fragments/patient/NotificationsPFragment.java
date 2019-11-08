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

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsPFragment extends Fragment {

    public static NotificationsPFragment newInstance() {
        return new NotificationsPFragment();
    }


    public NotificationsPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildStructure(view);
    }

    // Build the fragment structure
    private void buildStructure(View view) {
        ViewPager viewPager = view.findViewById(R.id.view_pager_notifications);
        NotificationsPFragment.PatientFragmentPageAdapter adapter = new PatientFragmentPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        TabLayout tabLayout = view.findViewById(R.id.tabs_notifications);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_local_pharmacy_black);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_group_work_black);

        // Add a badge to a specific tab
        BadgeDrawable badgeTherapies = tabLayout.getTabAt(1).getOrCreateBadge();
        badgeTherapies.setVisible(true);
        badgeTherapies.setNumber(1);

        BadgeDrawable badgeMedicaments = tabLayout.getTabAt(0).getOrCreateBadge();
        badgeMedicaments.setVisible(true);
        badgeMedicaments.setNumber(1);
        //tabLayout.getTabAt(1).removeBadge();
    }

    private class PatientFragmentPageAdapter extends FragmentPagerAdapter {

        public PatientFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    MedicamentsChildFragment medicamentsChildFragment = new MedicamentsChildFragment();
                    return medicamentsChildFragment;
                case 1:
                    TherapiesChildFragment therapiesChildFragment = new TherapiesChildFragment();
                    return therapiesChildFragment;
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
                    String tab1 = getString(R.string.tab_medicaments);
                    return tab1;
                case 1:
                    String tab2 = getString(R.string.tab_therapies);
                    return tab2;
            }
            return null;
        }
    }
}