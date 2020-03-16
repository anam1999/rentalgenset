package com.example.tekmulang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterReset extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignUp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextNamaLengkap;
    private EditText editTextTelepon;
    private TextView textViewSignIn;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerreset);

               firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), RegisterReset.class));
        }

        buttonSignUp = (Button)findViewById(R.id.registerkedua);
        editTextEmail = (EditText)findViewById(R.id.usernamekedua);
        editTextPassword = (EditText)findViewById(R.id.passwordkedua);
        editTextNamaLengkap = (EditText)findViewById(R.id.namalengkap);
        editTextTelepon =(EditText)findViewById(R.id.telepon);
        textViewSignIn = (TextView)findViewById(R.id.loginkedua);

        progressDialog = new ProgressDialog(this);

        buttonSignUp.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String namalengkap = editTextNamaLengkap.getText().toString().trim();
        String telepon = editTextTelepon.getText().toString().trim();

        if (TextUtils.isEmpty(namalengkap)){
            //
            Toast.makeText(this, "Silahkan masukkan nama lengkap anda", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(telepon)){
            //
            Toast.makeText(this, "Silahkan masukkan no telepon anda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            //
            Toast.makeText(this, "Silahkan masukkan email anda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //
            Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            //
            Toast.makeText(this, "Silahkan masukkan password anda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() <6){
            Toast.makeText(this, "Minimal passwors 6 karakter", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Proses register...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
                        }else {
                            Toast.makeText(RegisterReset.this, "Register gagal..",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignUp){
            userLogin();
        }
        if (view == textViewSignIn){
            startActivity(new Intent(this, FragmentActivity.class));
        }
    }
}
