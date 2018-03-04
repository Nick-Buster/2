package com.example.nikhil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_Activity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    TextView register;
    private EditText email, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        firebaseAuth = FirebaseAuth.getInstance();

        login = (Button) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.passcode);
        register = (TextView) findViewById(R.id.register);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    startActivity(new Intent(login_Activity.this,Home.class));


                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);

    }

    private void signIn() {

        String memail = email.getText().toString();
        String mpasscode = password.getText().toString();

        if(TextUtils.isEmpty(memail) || TextUtils.isEmpty(mpasscode)) {

            Toast.makeText(login_Activity.this, "Field are Empty", Toast.LENGTH_LONG).show();


        }
        else{
            firebaseAuth.signInWithEmailAndPassword(memail, mpasscode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(login_Activity.this, "SignIn Problem", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    public void Reg(View view){
        startActivity(new Intent(login_Activity.this,Register.class));

    }

}

