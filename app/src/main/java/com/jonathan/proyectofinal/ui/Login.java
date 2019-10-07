package com.jonathan.proyectofinal.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.database.LoginManager;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;

import java.util.List;

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
    @BindView(R.id.btn_login_google)
    SignInButton btnLoginGoogle;

    private GoogleSignInClient googleSignInClient;
    private int GOOGLE_SIGN_IN=1;

    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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

    private LoginManager loginInstance() {
        LoginManager loginManager = new LoginManager();
        return loginManager;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (loginInstance().userLoggedIn() == true) {
            startActivity(new Intent(Login.this, AdminHome.class));
        }
    }

    private void emailPassLogin() {

        if (validateFields() != null) {
            loginInstance().emailPasswordLogin(this, validateFields()[0], validateFields()[1]);
        }

    }



    //////// login con google *****************************************************************************
    private void googleLogin(){
        //loginInstance().loginwithGoogle(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(Login.this,"CORRECTO GOOGLE", Toast.LENGTH_SHORT).show();
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(Login.this,"ERRO GOOGLE", Toast.LENGTH_SHORT).show();
                // ...
                firebaseAuthWithGoogle(null);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GOOGLE", "signInWithCredential:success");
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    updateUI(user);
                    Toast.makeText(Login.this,"OK", Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GOOGLE", "signInWithCredential:failure", task.getException());
               //     Snackbar.make(findViewById(R.id.la), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                    updateUI(null);

                    Toast.makeText(Login.this,"MAL", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            register.setText("Nombre:"+personName+ " GivenName"+personGivenName+" familiar:"+personFamilyName+" Email:"+personEmail+" ID:"+personId);
        }
    }

    /***************************************************************************************/
    /*********************************** REGISTRO LUEGO BORRAR ******************************/
    private void register() {

        if (validateFields() != null) {
            loginInstance().createUserWithEmailAndPassword(this, validateFields()[0], validateFields()[1]);
        }

    }
    /*************************************************************************************/
    /*************************************************************************************/


    private String[] validateFields() {
        String email = name.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String[] data = new String[2];

        if (email.isEmpty()) {
            txtname.setError(getString(R.string.email_required));
            data = null;
        }
        if (password.isEmpty()) {
            txtpass.setError(getString(R.string.password_required));
            data = null;
        } else if (!email.isEmpty() && !password.isEmpty()) {
            data[0] = email;
            data[1] = password;
        } else {
            data = null;
        }
        return data;
    }

    @OnClick({R.id.login_admin_btn_enter, R.id.link_registrar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_admin_btn_enter:
                emailPassLogin();
                break;
            case R.id.link_registrar:
                register();
                break;
            case R.id.btn_login_google:
                googleLogin();
                break;
        }
    }

}
