package com.example.anmolsharma.oyohospitality;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Tab3PostActivity extends AppCompatActivity {


    ImageButton imageselect27;
    EditText edittitle27,editdescrption27,editlink27;
    Button submitbutton27,rightposttitle27,submitallbutton27;
    Uri imageuri27;
    String title27,link27,desc27,uid27,imp27,key27;
    private StorageReference mstorage27,filepath270;
    ImageView imaegeselectview27;
    private FirebaseAuth auth27;
    FirebaseUser user27= FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog mprogress27;
    private DatabaseReference mdatabase27,currentuser_db27,finalreference27;
    private Uri filepath27;

    private final static int galleryrequest27=1;

    public void Tab3PostActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3_post);

        imageselect27=(ImageButton)findViewById(R.id.imageselect27);
        edittitle27=(EditText)findViewById(R.id.edittitle27);
        editlink27=(EditText)findViewById(R.id.editlink27);
        editdescrption27=(EditText)findViewById(R.id.editdescription27);
        submitbutton27=(Button)findViewById(R.id.donebutton27);
        rightposttitle27=(Button)findViewById(R.id.rightbuttonposttitle27);
        submitallbutton27=(Button)findViewById(R.id.donebutton027);
        imaegeselectview27=(ImageView)findViewById(R.id.imageselectview27);
        auth27=FirebaseAuth.getInstance();
        mstorage27= FirebaseStorage.getInstance().getReference();
        mdatabase27= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference27=mdatabase27.child("Posts Final Tab3");
        currentuser_db27=mdatabase27.child(user27.getUid()).child("Posts");
        mprogress27=new ProgressDialog(this);
        uid27=user27.getUid();
        key27=   finalreference27.child("Blog Image").push().getKey();

        imageselect27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageselect27.setVisibility(View.INVISIBLE);
                imaegeselectview27.setVisibility(View.VISIBLE);
                Intent galleryintent6=new Intent();
                galleryintent6.setType("image/*");
                galleryintent6.setAction(galleryintent6.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryintent6,"select an Image"),galleryrequest27);

            }
        });

        rightposttitle27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title27 = edittitle27.getText().toString().trim();
                desc27=editdescrption27.getText().toString().trim();
                link27=editlink27.getText().toString().trim();

                if (TextUtils.isEmpty(title27)) {
                    Toast.makeText(getApplication(), "Enter Title", Toast.LENGTH_LONG).show();
                    return;

                } else {

                    if(filepath27!=null) {
                        final ProgressDialog progressDialog=new ProgressDialog(Tab3PostActivity.this);
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();


                        StorageReference riversRef0 = mstorage27.child("images/Blog Image.jpg");

                        riversRef0.putFile(filepath27)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        progressDialog.dismiss();
                                        @SuppressWarnings("VisibleForTests") final Uri  dowloaduri11= taskSnapshot.getDownloadUrl();
                                        String uid11 = auth27.getCurrentUser().getUid();
                                        DatabaseReference current_user_db10 = mdatabase27.child(uid11).child("Posts");
                                        imp27=dowloaduri11.toString();

                                        finalreference27.child(key27).child("Blog Title").setValue(title27);
                                        finalreference27.child(key27).child("Description Blog").setValue(desc27);
                                        finalreference27.child(key27).child("Link Blog").setValue(link27);





                                        finalreference27.child(key27).child("Blog Image").setValue(imp27);


                                        currentuser_db27.child("Blog Title").setValue(title27);
                                        currentuser_db27.child("Description Blog").setValue(desc27);
                                        currentuser_db27.child("Link Blog").setValue(link27);
                                        currentuser_db27.child("Blog Image").setValue(imp27);

                                        Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG);
                                    }
                                });


                    }else {

                        Toast.makeText(getApplicationContext(),"please upload again",Toast.LENGTH_LONG);
                    }




                    submitbutton27.setVisibility(View.VISIBLE);
                    submitbutton27.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startposting27();
                            Intent i17=new Intent(Tab3PostActivity.this,LoginActivity.class);
                            startActivity(i17);

                        }
                    });
                }
            }
        });



    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==galleryrequest27&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            filepath27=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath27);
                imaegeselectview27.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else
        {

            Toast.makeText(getApplicationContext(),"Please select Image",Toast.LENGTH_LONG);

        }


    }

    private void startposting27()
    {
        mprogress27.setMessage("Posting to Blog....");
        mprogress27.show();

        if (TextUtils.isEmpty(desc27)) {
            Toast.makeText(getApplication(), "Enter Description of post", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(link27)) {
            Toast.makeText(getApplication(), "Enter Complete Link", Toast.LENGTH_LONG).show();
            return;

        }


        mprogress27.dismiss();



    }



}
