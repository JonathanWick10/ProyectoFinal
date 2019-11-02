package com.jonathan.proyectofinal.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.fragments.games.Memorama;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Games extends AppCompatActivity  implements Memorama.Memoramai {

    //region Reference
    @BindView(R.id.contanedor_games)
    public FrameLayout container;
    @BindView(R.id.progres_cont)
    public LinearLayout progresCont;
    @BindView(R.id.progressBar_init)
    public ProgressBar progressBar;
//endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //flecha de atras
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iniciarProgres();

    }
    private void iniciarProgres() {

        //mostrar Progress
        progresCont.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);

        ObjectAnimator anim = ObjectAnimator.ofArgb(progressBar, "progress", 0, 100);
        anim.setDuration(3000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { }
            @Override
            public void onAnimationEnd(Animator animation) {
                //inciar fragmento
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contanedor_games, new Memorama(Games.this));
                ft.commit();

                //ocultar y mostra
                progresCont.setVisibility(View.GONE);
                container.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationCancel(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)  onBackPressed();
        return true;
    }

    @Override
    public void reloadGame() {
        iniciarProgres();
    }

    @Override
    public void callOnbackPressed() {
        onBackPressed();
    }
}
