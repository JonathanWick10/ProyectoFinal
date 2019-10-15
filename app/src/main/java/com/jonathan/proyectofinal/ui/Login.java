package com.jonathan.proyectofinal.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
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
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.jonathan.proyectofinal.R;
import com.jonathan.proyectofinal.data.Carer;
import com.jonathan.proyectofinal.database.CarerManager;
import com.jonathan.proyectofinal.database.LoginManager;
import com.jonathan.proyectofinal.fragments.admin.AdminHome;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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
    /*
    @BindView(R.id.btn_login_google)
    SignInButton btnLoginGoogle;
    */
    @BindView(R.id.btn_login_google)
    MaterialButton btnLoginGoogle;
    @BindView(R.id.btn_login_facebook)
    Button btnLoginFacebook;

    private GoogleSignInClient googleSignInClient;
    private int GOOGLE_SIGN_IN=1;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    List<AuthUI.IdpConfig> providers;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;

    CallbackManager callbackManager;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
       // reference();
        //region ScreenOrientationPortrait
        //Screen orientation portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //endregion
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        loginInstance().googleClientSettings(this);
        callbackManager=CallbackManager.Factory.create();

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


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    goMainProfil();
                }
            }
        };

    }

    private void goMainProfil() {
        startActivity(new Intent(Login.this, MainPatient.class));
        Toast.makeText(this, "LOGUEEEEEADOOOO", Toast.LENGTH_SHORT).show();
    }

    private LoginManager loginInstance() {
        LoginManager loginManager = new LoginManager();
        return loginManager;
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
        if (loginInstance().userLoggedIn() == true) {
            goMainProfil();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    private void emailPassLogin() {

        if (validateFields() != null) {
            loginInstance().emailPasswordLogin(this, validateFields()[0], validateFields()[1]);
        }

    }

    private void googleLogin(){
        Intent intent = loginInstance().googleClientSettings(this).getSignInIntent();
        startActivityForResult(intent, GOOGLE_SIGN_IN);
    }

    private void facebookLogin() {

        com.facebook.login.LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile"));
        com.facebook.login.LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                loginInstance().handleFacebookAccessToken(loginResult.getAccessToken(), Login.this);
                // Toast.makeText(MainActivity.this,"LOGUIN CORRECTO", Toast.LENGTH_SHORT).show();
                // simpleProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this,"LOGUIN CANCELADO", Toast.LENGTH_SHORT).show();
                // ...
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(Login.this,"ERROOOOOOOOOR", Toast.LENGTH_SHORT).show();
                // ...
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                loginInstance().handleGoogleAccessToken(result, Login.this);
                goMainProfil();
            }else {
                Toast.makeText(Login.this,getString(R.string.auth_fail), Toast.LENGTH_SHORT).show();
            }
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

    @OnClick({R.id.login_admin_btn_enter, R.id.link_registrar, R.id.btn_login_google, R.id.btn_login_facebook})
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
            case R.id.btn_login_facebook:
                facebookLogin();
                break;
        }
    }

}
