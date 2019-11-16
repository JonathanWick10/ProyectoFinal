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
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Patient;
import com.jonathan.proyectofinal.fragments.carer.InformationCarerFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;

import java.io.FileReader;

public class InformationPSFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    Patient patient = new Patient();
    Bundle args ;
    Bundle bundle;


    private IMainCarer iMainHealthProfessional;

    public InformationPSFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ps_information, container, false);
        bundle = getArguments();
        if (bundle!=null){
            patient = (Patient)bundle.getSerializable("patient");
            args.putSerializable("patient", patient);
        }
        /*FragmentManager manager = getChildFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        Fragment change;
        change = new InformationPatientPSFragment();
        change.setArguments(args);
        //setFlag(1);
        transaction.replace(R.id.info_patient,change).commit();*/
        iMainHealthProfessional.inflateFragment("patient2");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainHealthProfessional = (IMainCarer) getActivity();
    }
}
