package com.example.anmolsharma.oyohospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileEmployer extends AppCompatActivity {

    TextView nametext4,emailtext4,passtext4,phtext4;
    EditText nameedit4,emailedit4,passedit4,phedit4;
    String na4,em4,pa4,ph4;
    Button doneb4,deleteb4,emailb4,passb4,nameb4,phb4;
    FirebaseAuth auth4;
    FirebaseUser user4= FirebaseAuth.getInstance().getCurrentUser();
    // Firebase fire=new Firebase("https://oyohospitality-c0d27.firebaseio.com/");

    final FirebaseDatabase database4=FirebaseDatabase.getInstance();
    final DatabaseReference myRef4=database4.getReference("Users");
    final DatabaseReference current_user_db1 = myRef4.child(user4.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_employer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth4=FirebaseAuth.getInstance();
        nametext4=(TextView)findViewById(R.id.nametext4);
        emailtext4=(TextView)findViewById(R.id.emailtext4);
        passtext4=(TextView)findViewById(R.id.passtext4);
        phtext4=(TextView)findViewById(R.id.phtext4);
        nameedit4=(EditText)findViewById(R.id.nameedit4);
        emailedit4=(EditText)findViewById(R.id.emailedit4);
        passedit4=(EditText)findViewById(R.id.passedit4);
        phedit4=(EditText)findViewById(R.id.phedit4);
        doneb4=(Button) findViewById(R.id.donebutton4);
        deleteb4=(Button) findViewById(R.id.deletebutton4);
        emailb4=(Button) findViewById(R.id.rightbuttonemail4);
        passb4=(Button) findViewById(R.id.rightbuttonpass4);
        nameb4=(Button) findViewById(R.id.rightbuttonname4);
        phb4=(Button) findViewById(R.id.rightbuttonph4);
        Log.e("id1",user4.getUid());

        //   final   FirebaseDatabase database=FirebaseDatabase.getInstance();
        //  final DatabaseReference myRef=database.getReference("Users");
        setTitle("Hello"+user4.getEmail());


        // Text setting on Textview
        emailtext4.setText(user4.getEmail());
        myRef4.child(user4.getUid()).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value1 = dataSnapshot.getValue(String.class);
                nametext4.setText(value1);
                Log.d("TAG", "Value is: " + value1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Tag", "Failed to read value.", error.toException());
            }
        });
        myRef4.child(user4.getUid()).child("Phone_no").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                phtext4.setText(value);
                Log.d("tag", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });


        //edit text ediiting

        nametext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametext4.setVisibility(View.INVISIBLE);
                nameedit4.setVisibility(View.VISIBLE);
                nameb4.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(na4)) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                na4=nameedit4.getText().toString();
            }
        });


        nameb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                na4=nameedit4.getText().toString();
                if (TextUtils.isEmpty(na4)) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRef4.child(user4.getUid()).child("Name").setValue(na4);


            }

        });


        emailtext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtext4.setVisibility(View.INVISIBLE);
                emailedit4.setVisibility(View.VISIBLE);
                emailb4.setVisibility(View.VISIBLE);
                em4=emailedit4.getText().toString().trim();


                if (TextUtils.isEmpty(em4)) {
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        emailb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em4=emailedit4.getText().toString().trim();
                if (TextUtils.isEmpty(em4)) {
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                user4.updateEmail(em4)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UpdateProfileEmployer.this, "Email address is updated.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(UpdateProfileEmployer.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


        passtext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passtext4.setVisibility(View.INVISIBLE);
                passedit4.setVisibility(View.VISIBLE);
                passb4.setVisibility(View.VISIBLE);

                pa4=passedit4.getText().toString().trim();

                if (TextUtils.isEmpty(pa4)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        passb4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                pa4=passedit4.getText().toString().trim();

                if (TextUtils.isEmpty(pa4)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                user4.updatePassword(pa4)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UpdateProfileEmployer.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateProfileEmployer.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                    //progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

            }
        });


        phtext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phtext4.setVisibility(View.INVISIBLE);
                phedit4.setVisibility(View.VISIBLE);
                phb4.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(ph4)) {
                    Toast.makeText(getApplicationContext(), "Enter 10 Digit Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                ph4=phedit4.getText().toString();
            }
        });

        phb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ph4=phedit4.getText().toString();
                if (TextUtils.isEmpty(ph4)) {
                    Toast.makeText(getApplicationContext(), "Enter 10 digit Phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRef4.child(user4.getUid()).child("Phone_no").setValue(ph4);

            }
        });


    }

    public void update4(View v){


        Intent i8=new Intent(UpdateProfileEmployer.this,HomeForEmployees.class);
        startActivity(i8);

        Toast.makeText(getApplicationContext(), "updates Sucessfully", Toast.LENGTH_SHORT).show();
    }

    public void delete4(View v){

        user4.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        myRef4.child(user4.getUid()).removeValue();
        final Intent i6=new Intent(UpdateProfileEmployer.this,Secondpage.class);
        startActivity(i6);
        Toast.makeText(getApplicationContext(), "Deleted Successful", Toast.LENGTH_SHORT).show();


    }

}
