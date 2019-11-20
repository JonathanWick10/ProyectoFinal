package com.jonathan.proyectofinal.fragments.hp;

import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Patient;

public class TherapyPSFragment extends Fragment {

    private Bundle args;

    public TherapyPSFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        Patient patientSendFragment;
        args = getActivity().getIntent().getExtras();
        if (args!= null){
            patientSendFragment = (Patient) args.getSerializable("patient");
            args.putSerializable("patient",patientSendFragment);

            SharedPreferences preferences = getActivity().getPreferences(0);
            SharedPreferences.Editor editor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(patientSendFragment);
            editor.putString("serialipatient",json);
            editor.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ps_therapy, container, false);

        TabItem tabPatientInfo = view.findViewById(R.id.ps_tab_therapy_cognitive);
        TabItem nearbyhospital = view.findViewById(R.id.ps_tab_therapy_motor);
        TabItem tabMemorizame = view.findViewById(R.id.ps_tab_therapy_memorizame);

        TabLayout tabs = view.findViewById(R.id.ps_tabs_therapy);
        ViewPager viewPager = view.findViewById(R.id.containerPageTherapyPS);

        SetUpViewPager(viewPager, tabs);
        return view;
    }

    private void SetUpViewPager(ViewPager viewPager, TabLayout tabs) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.setArgs(args);

        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        tabs.getTabAt(0).setIcon(R.drawable.ic_lightbulb_outline_black);
        tabs.getTabAt(1).setIcon(R.drawable.ic_accessibility_black);
        tabs.getTabAt(2).setIcon(R.drawable.ic_brain_black);
    }

    public class Adapter extends FragmentPagerAdapter {

        private Bundle argsx;
        public void setArgs(Bundle argsx) { this.argsx = argsx; }
        public Adapter(FragmentManager fm) { super(fm);}

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new CognitiveTherapyPSFragment();
                case 1:
                    return new MotorTherapyPSFragment();

                case 2:
                    MemorizameParent memorizameParent = new MemorizameParent();
                    //memorizameParent.setArguments(this.argsx);
                    return memorizameParent;

                default: return null;
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.cognitive);
                case 1: return getString(R.string.motor);
                case 2: return getString(R.string.menu_memorizame);

            }
            return null;
        }

        @Override
        public int getCount() { return 3; }
    }
}
