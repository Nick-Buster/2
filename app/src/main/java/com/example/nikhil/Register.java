package com.example.nikhil;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private Button register;
    private EditText email, passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        passcode = (EditText) findViewById(R.id.passcode);

        register = (Button) findViewById(R.id.register);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){

                    Toast.makeText(Register.this, "SignedIn", Toast.LENGTH_LONG).show();

                }
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
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
        String mpasscode = passcode.getText().toString();

        if(TextUtils.isEmpty(memail) || TextUtils.isEmpty(mpasscode)) {

            Toast.makeText(Register.this, "Field are Empty", Toast.LENGTH_LONG).show();


        }
    else{
            firebaseAuth.signInWithEmailAndPassword(memail, mpasscode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(Register.this, "SignIn Problem", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
        }
}