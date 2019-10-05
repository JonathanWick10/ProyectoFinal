package com.jonathan.proyectofinal.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.database.LoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {
    @BindView(R.id.login_admin_edt_name)
    TextInputEditText name;
    @BindView(R.id.txtinput_name)
    TextInputLayout txtname;
    @BindView(R.id.login_admin_edt_pass)
    TextInputEditText pass;
    @BindView(R.id.txtinput_password)
    TextInputLayout txtpass;
    @BindView(R.id.login_admin_btn_enter)
    MaterialButton btnLoginEmailPass;
    @BindView(R.id.link_registrar)
    TextView register;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
       // reference();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtpass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtname.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loginEmailPass() {
        String email = name.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (email.isEmpty()) {
            txtname.setError("Nombre de usuario obligatorio");
        }
        if (password.isEmpty()) {
            txtpass.setError("Contraseña obligatoria");
        } else {
            LoginManager loginManager = new LoginManager();
            loginManager.loginEmailPassword(this, email, password);
        }
    }

    /*********************************** REGISTRO PARA PROBAR *****************************************/
    private void register() {

        //pass.setError(Html.fromHtml("<font color='red'>hello</font>"));
        String email = name.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (email.isEmpty()) {
            txtname.setError("Nombre de usuario obligatorio");
        }
        if (password.isEmpty()) {
            txtpass.setError("Contraseña obligatoria");
        } else {

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Login.this, "Registro correcto",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login.this, "ERROR EN EL REGISTRO!",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }


        //    pass.requestFocus();
    }

    @OnClick({R.id.login_admin_btn_enter, R.id.link_registrar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_admin_btn_enter:
                loginEmailPass();
                break;
            case R.id.link_registrar:
                register();
                break;
        }
    }

    /*************************************************************************************/
    /*************************************************************************************/
}
