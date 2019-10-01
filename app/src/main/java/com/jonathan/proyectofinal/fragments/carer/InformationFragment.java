package com.jonathan.proyectofinal.fragments.carer;

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
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class InformationFragment extends Fragment {

    TabLayout tab;
    TabItem information, phases;
    ViewPager viewPag;
    Adapter adapt;
    String text1,text2;

    private IMainCarer mIMainCarer;

    public InformationFragment() {
    }

    public InformationFragment(int contentLayoutId) { super(contentLayoutId);  }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_information,container,false);
        information = view.findViewById(R.id.generalinformation);
        phases = view.findViewById(R.id.phases);
        tab = view.findViewById(R.id.tablayout);
        viewPag = view.findViewById(R.id.viewpager);
        PhasesEAFragment fragment = new PhasesEAFragment();
        SetUpViewPager(viewPag,tab,fragment);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPag, TabLayout tab, Fragment fragment) {
        if (fragment != null) {
            adapt = new Adapter(getActivity().getSupportFragmentManager());
            tab.setupWithViewPager(viewPag);
            viewPag.setAdapter(adapt);
            //tabs.getTabAt(0).setIcon(R.drawable.ic_loyalty);
            //tabs.getTabAt(1).setIcon(R.drawable.ic_hospital);
        }
    }

    public class Adapter extends FragmentPagerAdapter{

        public Adapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            switch (position){
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
            switch (position){
                case 0:
                    text1 = getString(R.string.general_information);
                    mIMainCarer.inflateFragment(getString(R.string.general_information));
                    return text1;
                case 1:
                    text2 = getString(R.string.phases_ea);
                    mIMainCarer.inflateFragment(getString(R.string.phases_ea));
                    return text2;
            }
            return null;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}
