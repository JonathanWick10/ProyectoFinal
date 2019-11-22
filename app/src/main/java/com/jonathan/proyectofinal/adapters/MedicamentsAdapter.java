package com.jonathan.proyectofinal.adapters;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.MedicationAssignment;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class MedicamentsAdapter extends FirestoreRecyclerAdapter<MedicationAssignment,MedicamentsAdapter.MeicamentsViewHolder> {


    //region Variables
    Context context;
    MedicamentsAdapter.ISelectItemMedicaments iSelectItemMedicaments;
    //endregion

    //region Buids
    public MedicamentsAdapter(@NonNull FirestoreRecyclerOptions options, Context context, ISelectItemMedicaments iSelectItemMedicaments) {
        super(options);
        this.context = context;
        this.iSelectItemMedicaments = iSelectItemMedicaments;
    }
    //endregion

    @Override
    protected void onBindViewHolder(@NonNull MeicamentsViewHolder holder, int position, @NonNull MedicationAssignment model) {
        //Get the position of the professional inside the adapter
        final DocumentSnapshot medicamentDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());


        holder.setData(medicamentDocument.toObject(MedicationAssignment.class));
        final LinearLayout expandableView = holder.expandableView;
        final MaterialCardView cardMedicament = holder.cardMedicament;
        final TextView txtSwit = holder.txtSwitch;

        //events onclick
        holder.btn_show_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectItemMedicaments.clickSelect(medicamentDocument.toObject(MedicationAssignment.class));
                if (expandableView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(cardMedicament, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    //btn_show_hide.setText(R.string.btn_hide_info);
                    //btn_show_hide.setWidth(240);
                } else {
                    TransitionManager.beginDelayedTransition(cardMedicament, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    //btn_show_hide.setText(R.string.btn_show_info);
                }
            }
        });
    }

    @NonNull
    @Override
    public MeicamentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicament_card, parent, false);
        return new MeicamentsViewHolder(view);
    }

    //region View Holder Medica,entsAdapter
    public class MeicamentsViewHolder extends RecyclerView.ViewHolder{

        View layout;
        MedicationAssignment item;

        //region code Luis
        MaterialButton btn_show_hide;
        LinearLayout expandableView;
        MaterialCardView cardMedicament;

        ImageView imgMedicaments;
        Switch aSwitch;

        public TextView tvMedicamentName;
        public TextView tvMedicamentDescription;
        public TextView tvRecipeDate;
        public TextView tvFrequency;
        public TextView tvDose;
        public TextView tvHours;
        public TextView tvStatement;
        public TextView txtSwitch;
        //endregion

        public MeicamentsViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;

            //region code Luis
            btn_show_hide = itemView.findViewById(R.id.btn_show_hide_medicament);
            expandableView = itemView.findViewById(R.id.expandableMedicamentView);
            cardMedicament = itemView.findViewById(R.id.cardActivityMedicaments);

            tvMedicamentName = itemView.findViewById(R.id.lbl_medicament_name);
            tvMedicamentDescription = itemView.findViewById(R.id.lbl_data_description);
            tvRecipeDate = itemView.findViewById(R.id.data_recipe_date);
            tvFrequency = itemView.findViewById(R.id.data_frequency);
            tvDose = itemView.findViewById(R.id.data_dose);
            tvHours = itemView.findViewById(R.id.data_hours);
            tvStatement = itemView.findViewById(R.id.data_statement_medicament);
            imgMedicaments = itemView.findViewById(R.id.iv_medicament_miniature);
            //aSwitch = (Switch) itemView.findViewById(R.id.switch_desactivate);
            txtSwitch = itemView.findViewById(R.id.txt_switch_list);
            //endregion
        }

        //setdata
        //Set data to views
        public void setData(MedicationAssignment item) {
            this.item = item;

            tvMedicamentName.setText(item.getMedicamentName());
            tvMedicamentDescription.setText(item.getMedicamentDescription());
            //tvRecipeDate.setText(item.getRecipeDate());
            tvFrequency.setText(item.getFrequency());
            tvDose.setText(item.getDose());
            tvHours.setText(item.getHours());
            tvStatement.setText(item.getStatement());
            Glide.with(context).load(item.getUriImg()).centerCrop().into(imgMedicaments);

            if (item.getStatement().equals("Activada")){
                txtSwitch.setText("Activada");
            }else{
                txtSwitch.setText("Desactivada");
            }
        }
    }
    //endregion

    //region Interfaces
    public interface ISelectItemMedicaments{
        void clickSelect(MedicationAssignment medicationAssignment);
        void check(MedicationAssignment medicationAssignment);
    }
    //endregion

}
