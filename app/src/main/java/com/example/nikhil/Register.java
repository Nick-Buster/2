package com.example.nikhil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    Button register;
    private EditText email,passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();

        firebaseAuth = FirebaseAuth.getInstance();
        register = (Button)findViewById(R.id.register);
        email = (EditText)findViewById(R.id.email);
        passcode = (EditText)findViewById(R.id.passcode);

        ProgressDialog p =new ProgressDialog(this);

        register.setOnClickListener(this);
    }

    private void registerUser{
        String uemail = email.getText().toString().trim();
        String upasscode  = passcode.getText().toString().trim();

        if(TextUtils.isEmpty(uemail)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
        }

        if(TextUtils.isEmpty(upasscode)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
        }

        ProgressDialog.setMessage("Registering Please Wait...");
        ProgressDialog.show();
    }


    public void signp(View view){
        registerUser();
    }
}

