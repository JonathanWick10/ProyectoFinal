package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class EmergencyFragment extends Fragment {

    TabLayout tabs;
    ViewPager viewPager;
    Adapter adapter;
    String text1,text2;

    private IMainCarer mIMainCarer;

    public EmergencyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_emergency,container,false);
        tabs = view.findViewById(R.id.flexbox);
        viewPager = view.findViewById(R.id.containerpage);
        CallEmergencyFragment fragment = new CallEmergencyFragment();
        SetUpViewPager(viewPager,tabs,fragment);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPager, TabLayout tabs, Fragment fragment) {
        if (fragment != null) {
            adapter = new Adapter(getChildFragmentManager());
            tabs.setupWithViewPager(viewPager);
            viewPager.setAdapter(adapter);
            tabs.getTabAt(0).setIcon(R.drawable.ic_loyalty);
            tabs.getTabAt(1).setIcon(R.drawable.ic_local_convenience_store_black);
        }
    }
    public class Adapter extends FragmentPagerAdapter{

        public Adapter(FragmentManager fm) { super(fm);}

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    CallEmergencyFragment callEmergencyFragment = new CallEmergencyFragment();
                    return callEmergencyFragment;
                case 1:
                    NearbyHospitalFragment nearbyHospitalFragment = new NearbyHospitalFragment();
                    return nearbyHospitalFragment;
            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    text1 = getString(R.string.emergency_contacts);
                    mIMainCarer.inflateFragment(getString(R.string.emergency_contacts));
                    return text1;
                case 1:
                    text2 = getString(R.string.nearby_hospitals);
                    mIMainCarer.inflateFragment(getString(R.string.nearby_hospitals));
                    return text2;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}