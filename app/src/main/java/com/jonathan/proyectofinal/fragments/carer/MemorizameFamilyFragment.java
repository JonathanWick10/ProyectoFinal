package com.jonathan.proyectofinal.fragments.carer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.hp.PatientsListFragment;
import com.jonathan.proyectofinal.interfaces.IMainCarer;

public class MemorizameFamilyFragment extends Fragment {

    CardView addquestion;

    private IMainCarer mIMainCarer;

    public MemorizameFamilyFragment(){}

/*    public MemorizameFamilyFragment(int contentLayoutId) {
        super(contentLayoutId);
    }


 */
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
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainCarer = (IMainCarer) getActivity();
    }
}
