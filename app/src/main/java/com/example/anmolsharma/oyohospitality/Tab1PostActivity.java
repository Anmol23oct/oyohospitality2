package com.example.anmolsharma.oyohospitality;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Tab1PostActivity extends AppCompatActivity {

    ImageButton imageselect;
            EditText edittitle,editdescrption,editlink;
    Button submitbutton5,rightposttitle5,submitallbutton;
    Uri imageuri6;
    String title5,link5,desc5,uid5,imp1,key;
    private StorageReference mstorage5,filepath5;
    ImageView imaegeselectview0;
    private FirebaseAuth auth5;
    FirebaseUser user5= FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog mprogress5;
    private DatabaseReference mdatabase5,currentuser_db,finalreference;
    private Uri filepath0;

    private final static int galleryrequest=1;

    public void Tab1PostActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1_post);

        imageselect=(ImageButton)findViewById(R.id.imageselect);
        edittitle=(EditText)findViewById(R.id.edittitle5);
        editlink=(EditText)findViewById(R.id.editlink6);
        editdescrption=(EditText)findViewById(R.id.editdescription5);
        submitbutton5=(Button)findViewById(R.id.donebutton5);
        rightposttitle5=(Button)findViewById(R.id.rightbuttonposttitle5);
        submitallbutton=(Button)findViewById(R.id.donebutton2);
        imaegeselectview0=(ImageView)findViewById(R.id.imageselectview0);
        auth5=FirebaseAuth.getInstance();
        mstorage5= FirebaseStorage.getInstance().getReference();
        mdatabase5= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference=mdatabase5.child("Posts Final Tab1");
        currentuser_db=mdatabase5.child(user5.getUid()).child("Posts");
        mprogress5=new ProgressDialog(this);
        uid5=user5.getUid();
        key=   finalreference.child("Blog Image").push().getKey();

        imageselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageselect.setVisibility(View.INVISIBLE);
                imaegeselectview0.setVisibility(View.VISIBLE);
                Intent galleryintent6=new Intent();
                galleryintent6.setType("image/*");
                galleryintent6.setAction(galleryintent6.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryintent6,"select an Image"),galleryrequest);

            }
        });

        rightposttitle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //   rightposttitle5.setVisibility(View.INVISIBLE);
                title5 = edittitle.getText().toString().trim();
                desc5=editdescrption.getText().toString().trim();
                link5=editlink.getText().toString().trim();

                if (TextUtils.isEmpty(title5)) {
                    Toast.makeText(getApplication(), "Enter Title", Toast.LENGTH_LONG).show();
                    return;

                } else {
                   //  key=   finalreference.child("Blog Image").push().getKey();

                    if(filepath0!=null) {
                        final ProgressDialog progressDialog=new ProgressDialog(Tab1PostActivity.this);
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();


                        StorageReference riversRef0 = mstorage5.child("images/Blog Image.jpg");

                        riversRef0.putFile(filepath0)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        progressDialog.dismiss();
                                        @SuppressWarnings("VisibleForTests") final Uri  dowloaduri11= taskSnapshot.getDownloadUrl();
                                        String uid11 = auth5.getCurrentUser().getUid();
                                        DatabaseReference current_user_db10 = mdatabase5.child(uid11).child("Posts");
                                        imp1=dowloaduri11.toString();
                                        // key=   finalreference.child("Blog Image").push().getKey();
                                     //   finalreference.child(key).child("Blog Image").setValue(imp1);
//   key=   finalreference.push().getKey();
                                        finalreference.child(key).child("Blog Image").setValue(imp1);
                                        finalreference.child(key).child("Blog Title").setValue(title5);
                                        finalreference.child(key).child("Description Blog").setValue(desc5);
                                        finalreference.child(key).child("Link Blog").setValue(link5);
                                    //    finalreference.child(key).child("Blog Image").setValue(imp1);
                                        //finalreference.child(key).child("Blog Image").setValue(imp1);


                                        currentuser_db.child("Blog Title").setValue(title5);
                                        currentuser_db.child("Description Blog").setValue(desc5);
                                        currentuser_db.child("Link Blog").setValue(link5);
                                        currentuser_db.child("Blog Image").setValue(imp1);

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




                    submitbutton5.setVisibility(View.VISIBLE);
                    submitbutton5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startposting();
                            //submitbutton5.setVisibility(View.INVISIBLE);
                           // submitallbutton.setVisibility(View.VISIBLE);
                            Intent i17=new Intent(Tab1PostActivity.this,LoginActivity.class);
                            startActivity(i17);

                        }
                    });
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

     /* if(requestCode==galleryrequest&&requestCode==RESULT_OK){

          imageuri6=data.getData();

         / try {

              InputStream imageStream = getContentResolver().openInputStream(imageuri6);
              Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

              selectedImage = getResizedBitmap(selectedImage, 344);// 400 is for example, replace with desired size

             imageselect.setImageBitmap(selectedImage);


          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
          
      }*/


        if(requestCode==galleryrequest&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            filepath0=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath0);
                imaegeselectview0.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else
        {

            Toast.makeText(getApplicationContext(),"Please select Image",Toast.LENGTH_LONG);

        }


    }

    private void startposting()
    {
        mprogress5.setMessage("Posting to Blog....");
        mprogress5.show();

        if (TextUtils.isEmpty(desc5)) {
            Toast.makeText(getApplication(), "Enter Description of post", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(link5)) {
            Toast.makeText(getApplication(), "Enter Complete Link", Toast.LENGTH_LONG).show();
            return;

        }

       /* filepath5=mstorage5.child("Blogs Images").child(imageuri6.getLastPathSegment());
        filepath5.putFile(imageuri6).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                @SuppressWarnings("VisibleForTests") Uri dowloaduri5= taskSnapshot.getDownloadUrl();
                currentuser_db.child("Blog Title").setValue(title5);
                currentuser_db.child("Description Blog").setValue(desc5);
                currentuser_db.child("Link Blog").setValue(link5);
                currentuser_db.child("Blog Image").setValue(dowloaduri5.toString());
                Intent i17=new Intent(Tab1PostActivity.this,Tab1.class);
                startActivity(i17);
                mprogress5.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                mprogress5.dismiss();
                Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG);
            }
        });;*/

       /* key=   finalreference.push().getKey();
        finalreference.child(key).child("Blog Title").setValue(title5);
        finalreference.child(key).child("Description Blog").setValue(desc5);
        finalreference.child(key).child("Link Blog").setValue(link5);
        finalreference.child(key).child("Blog Image").setValue(imp1);


        currentuser_db.child("Blog Title").setValue(title5);
        currentuser_db.child("Description Blog").setValue(desc5);
        currentuser_db.child("Link Blog").setValue(link5);
        currentuser_db.child("Blog Image").setValue(imp1);*/


      /*  if(filepath0!=null) {
            final ProgressDialog progressDialog=new ProgressDialog(Tab1PostActivity.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            StorageReference riversRef0 = mstorage5.child("images/Blog Image.jpg");

            riversRef0.putFile(filepath0)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            @SuppressWarnings("VisibleForTests") final Uri  dowloaduri11= taskSnapshot.getDownloadUrl();
                            String uid11 = auth5.getCurrentUser().getUid();
                            DatabaseReference current_user_db10 = mdatabase5.child(uid11).child("Posts");
                            imp1=dowloaduri11.toString();
                            currentuser_db.child("Blog Image").setValue(dowloaduri11.toString());
                            // key=   finalreference.child("Blog Image").push().getKey();
                            //  finalreference.child("Blog Image").child(key).setValue(dowloaduri11.toString());

                            finalreference.child(key).child("Blog Image").setValue(dowloaduri11.toString());

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
        }*/

       /* if(filepath0!=null) {
            final ProgressDialog progressDialog=new ProgressDialog(Tab1PostActivity.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            StorageReference riversRef0 = mstorage5.child("images/Blog Image.jpg");

            riversRef0.putFile(filepath0)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            @SuppressWarnings("VisibleForTests") final Uri  dowloaduri11= taskSnapshot.getDownloadUrl();
                            String uid11 = auth5.getCurrentUser().getUid();
                            DatabaseReference current_user_db10 = mdatabase5.child(uid11).child("Posts");
                            currentuser_db.child("Blog Image").setValue(dowloaduri11.toString());



                          /*  currentuser_db.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    Log.e("error","done");
                                   // getupdates(dataSnapshot);
                                    Blog b=new Blog();
                                    b.setDesc0(desc5);
                                    b.setLink0(link5);
                                    b.setTitle0(title5);
                                    b.setImage0(dowloaduri11.toString());

                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                    Blog b=new Blog();
                                    b.setDesc0(desc5);
                                    b.setLink0(link5);
                                    b.setTitle0(title5);
                                    b.setImage0(dowloaduri11.toString());
                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });








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
        }*/

        mprogress5.dismiss();



    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }



    }
