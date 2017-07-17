package com.example.anmolsharma.oyohospitality;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registeremployee extends AppCompatActivity {


    EditText nameedit,passedit,emailedit,phedit;
    Button donebutton;
    String name,email,pass,phone;
    DatabaseReference databaseemployee;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private ProgressDialog prgdlg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeremployee);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        //.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        setTitle("Sign up");
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
     nameedit=(EditText) findViewById(R.id.nameedit);
     passedit=(EditText) findViewById(R.id.passedit);
     emailedit=(EditText) findViewById(R.id.emailedit);
        phedit=(EditText) findViewById(R.id.phedit);
        donebutton=(Button) findViewById(R.id.donebutton);
        auth=FirebaseAuth.getInstance();
        prgdlg=new ProgressDialog(this);
        databaseemployee= FirebaseDatabase.getInstance().getReference().child("Users");

        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addemployee();

            }
        });

    }

    public void addemployee() {
        name = nameedit.getText().toString().trim();
        pass = passedit.getText().toString().trim();
        email = emailedit.getText().toString().trim();

            phone = phedit.getText().toString();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() < 10) {
            Toast.makeText(getApplicationContext(), "Enter 10 digit phone number", Toast.LENGTH_SHORT).show();
            return;
        }



            prgdlg.setMessage("Signing In");
            prgdlg.show();

       //final Intent i4=new Intent(Registeremployee.this,HomeForEmployees.class);
        //startActivity(i4);

            //for Authentication

           auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        String uid = auth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = databaseemployee.child(uid);
                        current_user_db.child("Name").setValue(name);
                        current_user_db.child("Phone_no").setValue(phone);

                        Log.e("AS", " PROCEED finnal");
                        prgdlg.dismiss();
                        Toast.makeText(getApplicationContext(), "Registered Successfull", Toast.LENGTH_SHORT).show();
                        final Intent i4=new Intent(Registeremployee.this,HomeForEmployees.class);
                        startActivity(i4);
                    }

                }
            });


    }

    protected void onResume() {
        super.onResume();
    }
}





