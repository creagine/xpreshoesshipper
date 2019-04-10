package com.creaginetech.xpresshoesshipper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.creaginetech.xpresshoesshipper.common.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    TextInputEditText inputEmail,inputPassword;
    Button btnLogin,btnRegister;
    ProgressBar progressBarLogin;

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        widgets();

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    private void widgets() {

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        progressBarLogin = findViewById(R.id.progressBarLogin);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnLogin){
            signIn();
        } else if (i == R.id.btnRegister){
            register();
        }
    }

    private void register() {
        //TODO : register function
    }

    private void signIn() {
        Log.d(TAG,"Sign In");
        validateForm();

        progressBarLogin.setVisibility(View.VISIBLE);
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               Log.d(TAG,"create User: Complete " +task.isSuccessful());
               progressBarLogin.setVisibility(View.GONE);

               if (task.isSuccessful()){
                   onAuthSuccess(task.getResult().getUser());
               } else {
                   String errorMessage = task.getException().getMessage();
                   Log.d(TAG,"Error :  " + errorMessage);
                   Toast.makeText(LoginActivity.this, "Failed to login : " + errorMessage, Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private void onAuthSuccess(FirebaseUser user) {

        String userId = firebaseUser.getUid();
        Common.userId = userId;

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void validateForm(){

        boolean result = true;
        if (TextUtils.isEmpty(inputEmail.getText().toString())){
            Toast.makeText(this, "Please input your Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(inputPassword.getText().toString())){
            Toast.makeText(this, "Please input your Password", Toast.LENGTH_SHORT).show();
        }

    }
}
