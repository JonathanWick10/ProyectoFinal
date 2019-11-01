package com.jonathan.proyectofinal.fragments.admin;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.AdminListPSAdapter;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminListPSFragment extends Fragment {

    private View view;
    @BindView(R.id.txt_no_hp)
    TextView noHp;
    private RecyclerView recyclerView ;
    private AdminListPSAdapter.AdminListPSAdapterI adapterI;
    private List<HealthcareProfessional> healthcareProfessionalList = new ArrayList<>();
    HealthcareProfessional hp = new HealthcareProfessional();


    public AdminListPSFragment (AdminListPSAdapter.AdminListPSAdapterI adapterI){
        this.adapterI = adapterI;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        ButterKnife.bind(this, view);
        reference();
        return view;
    }

    private void reference() {
        recyclerView = view.findViewById(R.id.admin_rv_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Constants.HealthcareProfesional).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        healthcareProfessionalList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot:
                             queryDocumentSnapshots) {
                            hp = documentSnapshot.toObject(HealthcareProfessional.class);
                            healthcareProfessionalList.add(hp);
                        }
                        recyclerView.setAdapter(new AdminListPSAdapter(healthcareProfessionalList,adapterI));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setHasFixedSize(true);
                        if (healthcareProfessionalList.size()!=0){
                            recyclerView.setVisibility(View.VISIBLE);
                            noHp.setVisibility(View.INVISIBLE);
                        }else {
                            recyclerView.setVisibility(View.INVISIBLE);
                            noHp.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

   public interface AdminListPSFragmentI{
        void onclickAddPs();
    }

}
