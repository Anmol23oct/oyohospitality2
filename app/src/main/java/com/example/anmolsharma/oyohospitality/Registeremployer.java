package com.example.anmolsharma.oyohospitality;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Registeremployer extends AppCompatActivity {

    EditText nameedit2,passedit2,phedit2,emailedit2;
    Button donebutton2,uploadbutton,choosebutton;
    ImageView imageView;
    private static final int PICK_Image_Request=234;
    private Uri filepath;
    private StorageReference storageReference;
    private FirebaseAuth auth2;
    private FirebaseAuth.AuthStateListener authListener2;
    private ProgressDialog prgdlg2;
    String name2,email2,pass2,phone2;
    DatabaseReference databaseemployee2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeremployer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setTitle("Sign Up");


        nameedit2=(EditText)findViewById(R.id.nameedit2);
        passedit2=(EditText)findViewById(R.id.passedit2);
        emailedit2=(EditText)findViewById(R.id.emailedit2);
        phedit2=(EditText)findViewById(R.id.phedit2);
        donebutton2=(Button)findViewById(R.id.donebutton2);
        choosebutton=(Button)findViewById(R.id.choosebutton);
        uploadbutton=(Button)findViewById(R.id.uploadebutton);
        imageView=(ImageView)findViewById(R.id.imageView);

        auth2=FirebaseAuth.getInstance();
        prgdlg2=new ProgressDialog(this);
        databaseemployee2= FirebaseDatabase.getInstance().getReference().child("Users");


        storageReference= FirebaseStorage.getInstance().getReference();

        choosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i10=new Intent();
                i10.setType("image/*");
                i10.setAction(i10.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i10,"select an Image"),PICK_Image_Request);



            }
        });

        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(filepath!=null) {
                    final ProgressDialog progressDialog=new ProgressDialog(Registeremployer.this);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    progressDialog.dismiss();
               /*     StorageReference riversRef = storageReference.child("images/Confirmation_Id.jpg");

                    riversRef.putFile(filepath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                     progressDialog.dismiss();
                                    @SuppressWarnings("VisibleForTests") Uri dowloaduri11= taskSnapshot.getDownloadUrl();
                                    String uid11 = auth2.getCurrentUser().getUid();
                                    DatabaseReference current_user_db11 = databaseemployee2.child(uid11);
                                    current_user_db11.child("Identity Pic").setValue(dowloaduri11.toString() );

                                    Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG);
                                    progressDialog.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG);
                                }
                            });*/


                }else {

                    Toast.makeText(getApplicationContext(),"please upload again",Toast.LENGTH_LONG);
                }
            }
        });



        donebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addemployer();


            }
        });


    }

    public void addemployer()
    {
        name2 = nameedit2.getText().toString().trim();
        pass2 = passedit2.getText().toString().trim();
        email2 = emailedit2.getText().toString().trim();

        phone2 = phedit2.getText().toString();


        if (TextUtils.isEmpty(name2)) {
            Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email2)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass2)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass2.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone2.length() < 10) {
            Toast.makeText(getApplicationContext(), "Enter 10 digit phone number", Toast.LENGTH_SHORT).show();
            return;
        }



        prgdlg2.setMessage("Signing In");
        prgdlg2.show();

       // final Intent i4=new Intent(Registeremployer.this,HomeForEmployer.class);
       // startActivity(i4);

        //for Authentication

        auth2.createUserWithEmailAndPassword(email2, pass2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                final ProgressDialog progressDialog0=new ProgressDialog(Registeremployer.this);
                if (task.isSuccessful()) {
                    String uid = auth2.getCurrentUser().getUid();
                    DatabaseReference current_user_db = databaseemployee2.child(uid);
                    current_user_db.child("Name").setValue(name2);
                    current_user_db.child("Phone_no").setValue(phone2);

                    StorageReference riversRef = storageReference.child("images/Confirmation_Id.jpg");

                    riversRef.putFile(filepath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog0.dismiss();
                                    @SuppressWarnings("VisibleForTests") Uri dowloaduri11= taskSnapshot.getDownloadUrl();
                                    String uid11 = auth2.getCurrentUser().getUid();
                                    DatabaseReference current_user_db11 = databaseemployee2.child(uid11);
                                    current_user_db11.child("Identity Pic").setValue(dowloaduri11.toString() );

                                    Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG);
                                    progressDialog0.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    progressDialog0.dismiss();
                                    Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG);
                                }
                            });
                    Log.e("AS", " PROCEED finnal");
                    prgdlg2.dismiss();
                    Toast.makeText(getApplicationContext(), "Registered Successfull", Toast.LENGTH_SHORT).show();

                    final Intent i4=new Intent(Registeremployer.this,Tab1PostActivity.class);
                    startActivity(i4);
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_Image_Request&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            filepath=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        else
        {

            Toast.makeText(getApplicationContext(),"Please select Image",Toast.LENGTH_LONG);

        }
    }
}
