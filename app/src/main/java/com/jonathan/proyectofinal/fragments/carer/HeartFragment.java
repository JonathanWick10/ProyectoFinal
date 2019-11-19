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

import com.google.android.material.tabs.TabLayout;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.patient.MotorChildFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class HeartFragment extends Fragment  {

    private TabLayout tabs;
    private ViewPager viewPag;
    private Adapter adapt;
    private String text1, text2, text3;
    //private PhysicalExecise.PhysicalExeciseI physicalExeciseI;
    private MotorChildFragment.MotorChildFragmentI motorChildFragmentI;
    private IMainCarer mIMainCarer;

    /*
    public HeartFragment(PhysicalExecise.PhysicalExeciseI physicalExeciseI) {
        this.physicalExeciseI = physicalExeciseI;
    }
    */
    public HeartFragment(MotorChildFragment.MotorChildFragmentI motorChildFragmentI){
        this.motorChildFragmentI = motorChildFragmentI;
    }

    public HeartFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_heart,container,false);
        tabs = view.findViewById(R.id.tabh);
        viewPag = view.findViewById(R.id.viewpagerh);
        SetUpViewPager(viewPag, tabs);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPag, TabLayout tabs) {
            adapt = new Adapter(getChildFragmentManager());
            tabs.setupWithViewPager(viewPag);
            viewPag.setAdapter(adapt);
            tabs.getTabAt(1).setIcon(R.drawable.ic_info);
            tabs.getTabAt(0).setIcon(R.drawable.ic_accessibility_black);
            tabs.getTabAt(2).setIcon(R.drawable.ic_description);
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
                    ExerciseCarerFragment exerciseCarerFragment = new ExerciseCarerFragment(/*physicalExeciseI*/ motorChildFragmentI);
                    return exerciseCarerFragment;

                case 1:
                    InformationCarerFragment informationCarerFragment = new InformationCarerFragment();
                    return informationCarerFragment;

                case 2:
                    WarningCarerFragment warningCarerFragment = new WarningCarerFragment();
                    return warningCarerFragment;
                default:
                    throw new RuntimeException("Invalid item index.");

            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    text2 = getString(R.string.exercise_carer);
                    mIMainCarer.inflateFragment(getString(R.string.exercise_carer));
                    return text2;
                case 1:
                    text1 = getString(R.string.information_carer);
                    mIMainCarer.inflateFragment(getString(R.string.information_carer));
                    return text1;
                case 2:
                    text3 = getString(R.string.advice_carer);
                    mIMainCarer.inflateFragment(getString(R.string.advice_carer));
                    return text3;
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