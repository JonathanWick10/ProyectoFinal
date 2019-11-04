package com.jonathan.proyectofinal.fragments.carer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.adapters.MemorizameFamilyGridAdapter;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.data.HealthcareProfessional;
import com.jonathan.proyectofinal.data.Memorizame;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;
import com.jonathan.proyectofinal.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemorizameFamilyFragment extends Fragment {

    CardView addquestion;

    private IMainCarer mIMainCarer;

    public MemorizameFamilyFragment(){}

/*    public MemorizameFamilyFragment(int contentLayoutId) {
        super(contentLayoutId);
    }


 */


//region for fragment grid

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MemorizameFamilyGridAdapter adapter;
    private MemorizameFamilyGridAdapter.ISelectionMemorizame iSelectionMemorizame;
    private MemorizameFamilyGridAdapter.IDeleteMemorizame iDeleteMemorizame;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<Memorizame> memorizameList = new ArrayList<>();
    String userHPoCarer = "";
    Memorizame memorizameM = new Memorizame();
    HealthcareProfessional hp = new HealthcareProfessional();
    Carer carer = new Carer();
    ProgressDialog progressDialog;
    //endregion


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cu_memorizame_family,container,false);
        addquestion = view.findViewById(R.id.cv_add_image);
        addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIMainCarer.inflateFragment(getString(R.string.family_questions_img));
            }
        });

//region for fragment grid
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userHPoCarer = user.getUid();
        db = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(getActivity());
        ButterKnife.bind(this, view);


        initAdapter();
        initRecyclerView();
      //endregion
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }


//region region for fragment grid
    private void initAdapter() {

        if (adapter == null){
            //   adapter = new MemorizameFamilyFragmentGrid(getActivity().getApplicationContext(), this);
        }


    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));//cambiar numero de columnas

        String uid = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionReferenceMemorizame = db.collection(Constants.Memorizame);

        collectionReferenceMemorizame
                .whereArrayContains("assigns", uid)
                .orderBy("firstName")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        memorizameList = new ArrayList<Memorizame>();
                        for (QueryDocumentSnapshot documentSnapshopt :
                                queryDocumentSnapshots) {
                            memorizameM = documentSnapshopt.toObject(Memorizame.class);
                            memorizameList.add(memorizameM);
                        }
                        adapter = new MemorizameFamilyGridAdapter(memorizameList,getActivity(),iSelectionMemorizame,iDeleteMemorizame);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Message", e.toString());
                    }
                });


    }
//endregion
}
