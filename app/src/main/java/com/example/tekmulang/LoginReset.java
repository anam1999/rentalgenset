package com.example.tekmulang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginReset extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginreset);

        //MENAMPILKAN PASSWORD//////////////////////////////////////////////////////////////////////
        final EditText ed = (EditText) findViewById(R.id.passwordpertama);
        CheckBox saputra = (CheckBox) findViewById(R.id.cekbox);

        saputra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                                               public  void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                                                   if (! isChecked){
                                                       ed.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                                   }else{
                                                       ed.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                                   }
                                               }
                                           }

        );
        ////////////////////////////////////////////////////////////////////////////////////////////
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), RentalReset.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button)findViewById(R.id.loginpertama);
        editTextEmail = (EditText)findViewById(R.id.usernamepertama);
        editTextPassword = (EditText)findViewById(R.id.passwordpertama);
        textViewSignIn = (TextView)findViewById(R.id.registerspertama);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //
            Toast.makeText(this, "Silahkan masukkan email anda", Toast.LENGTH_SHORT).show();
            return;

        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show();
            return;

        }else if (TextUtils.isEmpty(password)){
            //
            Toast.makeText(this, "Silahkan masukkan password anda", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(LoginReset.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
                        }else{
                            Toast.makeText(LoginReset.this, "Email tidak terdaftrar",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser();
        }
        if (view == textViewSignIn){
            startActivity(new Intent(this, RegisterReset.class));
        }

    }

}