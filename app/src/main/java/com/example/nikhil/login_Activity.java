package com.example.nikhil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;


public class login_Activity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    TextView register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        login = (Button)findViewById(R.id.login);

        register = (TextView)findViewById(R.id.register);


        // Read and compare database with user entered values
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.child("1").child("name").getValue().toString()==)

                Toast.makeText(login_Activity.this, dataSnapshot.child("1").child("name").getValue().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void Reg(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);

    }

}

