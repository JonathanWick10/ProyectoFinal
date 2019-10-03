package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class InformationFragment extends Fragment{

    TabLayout tabs;
    TabItem information, phases;
    ViewPager viewPag;
    Adapter adapt;
    String text1, text2;

    private IMainCarer mIMainCarer;


    public InformationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_information, container, false);
        //information = view.findViewById(R.id.generalinformation);
        //phases = view.findViewById(R.id.phases);
        tabs = view.findViewById(R.id.tablayout);
        tabs.addTab(tabs.newTab().setText(getString(R.string.general_information)));
        tabs.addTab(tabs.newTab().setText(getString(R.string.phases_ea)));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPag = view.findViewById(R.id.viewpager);
        viewPag.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(getOnTabSelectedListener(viewPag));
        PhasesEAFragment fragment = new PhasesEAFragment();
        SetUpViewPager(viewPag, tabs, fragment);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPag, TabLayout tab, Fragment fragment) {
        if (fragment != null) {
            adapt = new Adapter(getActivity().getSupportFragmentManager());
            tab.setupWithViewPager(viewPag);
            viewPag.setAdapter(adapt);
            tab.getTabAt(0).setIcon(R.drawable.ic_loyalty);
            tab.getTabAt(1).setIcon(R.drawable.ic_hospital);
        }
    }

    public class Adapter extends FragmentPagerAdapter {

        public Adapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    GeneralInformationFragment generalInformationFragment = new GeneralInformationFragment();
                    return generalInformationFragment;

                case 1:
                    PhasesEAFragment phasesEAFragment = new PhasesEAFragment();
                    return phasesEAFragment;

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
                    text1 = getString(R.string.general_information);
                    return text1;
                case 1:
                    text2 = getString(R.string.phases_ea);
                    return text2;
            }
            return null;
        }
    }

private TabLayout.OnTabSelectedListener getOnTabSelectedListener(final ViewPager viewPager) {
    return new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            int positionn = tab.getPosition();
            String position = String.valueOf(positionn);
            Log.d("tag", position);
            switch (positionn) {
                case 0:
                    //mIMainCarer.inflateFragment(getString(R.string.general_information));
                    break;
                case 1:
                    //mIMainCarer.inflateFragment(getString(R.string.phases_ea));
                    break;
                // Continue for each tab in TabLayout
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            int positionn = tab.getPosition();
            String position = String.valueOf(positionn);
            Log.d("tag2", position);
            // nothing now
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            int positionn = tab.getPosition();
            String position = String.valueOf(positionn);
            Log.d("tag3", position);
            // nothing now
        }
    };
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}
