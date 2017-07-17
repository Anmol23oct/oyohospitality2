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

//import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Updateprofile extends AppCompatActivity {

    TextView nametext,emailtext,passtext,phtext;
    EditText nameedit1,emailedit1,passedit1,phedit1;
    String na,em,pa,ph;
    Button doneb,deleteb,emailb,passb,nameb,phb;
    FirebaseAuth auth;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
   // Firebase fire=new Firebase("https://oyohospitality-c0d27.firebaseio.com/");

    final   FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference myRef=database.getReference("Users");
    final DatabaseReference current_user_db1 = myRef.child(user.getUid());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // setTitle("Hello"+user.getEmail());
        auth=FirebaseAuth.getInstance();
        nametext=(TextView)findViewById(R.id.nametext);
        emailtext=(TextView)findViewById(R.id.emailtext);
        passtext=(TextView)findViewById(R.id.passtext);
        phtext=(TextView)findViewById(R.id.phtext);
        nameedit1=(EditText)findViewById(R.id.nameedit1);
        emailedit1=(EditText)findViewById(R.id.emailedit1);
        passedit1=(EditText)findViewById(R.id.passedit1);
        phedit1=(EditText)findViewById(R.id.phedit1);
        doneb=(Button) findViewById(R.id.donebutton);
        deleteb=(Button) findViewById(R.id.deletebutton);
        emailb=(Button) findViewById(R.id.rightbuttonemail);
        passb=(Button) findViewById(R.id.rightbuttonpass);
        nameb=(Button) findViewById(R.id.rightbuttonname);
        phb=(Button) findViewById(R.id.rightbuttonph);
        Log.e("id1",user.getUid());

   //   final   FirebaseDatabase database=FirebaseDatabase.getInstance();
     //  final DatabaseReference myRef=database.getReference("Users");
        setTitle("Hello"+user.getEmail());


        // Text setting on Textview
        emailtext.setText(user.getEmail());
        myRef.child(user.getUid()).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value1 = dataSnapshot.getValue(String.class);
                nametext.setText(value1);
                Log.d("TAG", "Value is: " + value1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Tag", "Failed to read value.", error.toException());
            }
        });
        myRef.child(user.getUid()).child("Phone_no").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                phtext.setText(value);
                Log.d("tag", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
      // final DatabaseReference current_user_db1 = myRef.child(user.getUid());



       //edit text ediiting

        nametext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametext.setVisibility(View.INVISIBLE);
                nameedit1.setVisibility(View.VISIBLE);
                nameb.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(na)) {
                    Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                na=nameedit1.getText().toString();
            }
        });


        nameb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        na=nameedit1.getText().toString();
                        if (TextUtils.isEmpty(na)) {
                            Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        myRef.child(user.getUid()).child("Name").setValue(na);


                    }

                    });




        emailtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailtext.setVisibility(View.INVISIBLE);
                emailedit1.setVisibility(View.VISIBLE);
                emailb.setVisibility(View.VISIBLE);
                em=emailedit1.getText().toString().trim();


                if (TextUtils.isEmpty(em)) {
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        emailb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em=emailedit1.getText().toString().trim();
                if (TextUtils.isEmpty(em)) {
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.updateEmail(em)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Updateprofile.this, "Email address is updated.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Updateprofile.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        passtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passtext.setVisibility(View.INVISIBLE);
                passedit1.setVisibility(View.VISIBLE);
                passb.setVisibility(View.VISIBLE);

                pa=passedit1.getText().toString().trim();

                if (TextUtils.isEmpty(pa)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        passb.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                pa=passedit1.getText().toString().trim();

                if (TextUtils.isEmpty(pa)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.updatePassword(pa)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Updateprofile.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Updateprofile.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });



        phtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phtext.setVisibility(View.INVISIBLE);
                phedit1.setVisibility(View.VISIBLE);
                phb.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(ph)) {
                    Toast.makeText(getApplicationContext(), "Enter 10 Digit Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                ph=phedit1.getText().toString();
            }
        });

        phb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ph=phedit1.getText().toString();
                    if (TextUtils.isEmpty(ph)) {
                        Toast.makeText(getApplicationContext(), "Enter 10 digit Phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                myRef.child(user.getUid()).child("Phone_no").setValue(ph);

            }
        });


    }

    public void update(View v){


        Intent i8=new Intent(Updateprofile.this,HomeForEmployees.class);
        startActivity(i8);

        Toast.makeText(getApplicationContext(), "updates Sucessfully", Toast.LENGTH_SHORT).show();
    }

    public void delete(View v){

        user.delete()
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
        myRef.child(user.getUid()).removeValue();
        final Intent i6=new Intent(Updateprofile.this,Secondpage.class);
        startActivity(i6);
        Toast.makeText(getApplicationContext(), "Deleted Successful", Toast.LENGTH_SHORT).show();


    }

}
