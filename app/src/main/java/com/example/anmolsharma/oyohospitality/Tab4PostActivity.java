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

public class Tab4PostActivity extends AppCompatActivity {

    ImageButton imageselect29;
    EditText edittitle29,editdescrption29,editlink29;
    Button submitbutton29,rightposttitle29,submitallbutton29;
    Uri imageuri29;
    String title29,link29,desc29,uid29,imp29,key29;
    private StorageReference mstorage29,filepath290;
    ImageView imaegeselectview29;
    private FirebaseAuth auth29;
    FirebaseUser user29= FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog mprogress29;
    private DatabaseReference mdatabase29,currentuser_db29,finalreference29;
    private Uri filepath29;

    private final static int galleryrequest29=1;

    public void Tab4PostActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4_post);


        imageselect29=(ImageButton)findViewById(R.id.imageselect29);
        edittitle29=(EditText)findViewById(R.id.edittitle29);
        editlink29=(EditText)findViewById(R.id.editlink29);
        editdescrption29=(EditText)findViewById(R.id.editdescription29);
        submitbutton29=(Button)findViewById(R.id.donebutton29);
        rightposttitle29=(Button)findViewById(R.id.rightbuttonposttitle29);
        submitallbutton29=(Button)findViewById(R.id.donebutton029);
        imaegeselectview29=(ImageView)findViewById(R.id.imageselectview29);
        auth29=FirebaseAuth.getInstance();
        mstorage29= FirebaseStorage.getInstance().getReference();
        mdatabase29= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference29=mdatabase29.child("Posts Final Tab4");
        currentuser_db29=mdatabase29.child(user29.getUid()).child("Posts");
        mprogress29=new ProgressDialog(this);
        uid29=user29.getUid();
        key29=   finalreference29.child("Blog Image").push().getKey();

        imageselect29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageselect29.setVisibility(View.INVISIBLE);
                imaegeselectview29.setVisibility(View.VISIBLE);
                Intent galleryintent6=new Intent();
                galleryintent6.setType("image/*");
                galleryintent6.setAction(galleryintent6.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryintent6,"select an Image"),galleryrequest29);

            }
        });

        rightposttitle29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title29 = edittitle29.getText().toString().trim();
                desc29=editdescrption29.getText().toString().trim();
                link29=editlink29.getText().toString().trim();

                if (TextUtils.isEmpty(title29)) {
                    Toast.makeText(getApplication(), "Enter Title", Toast.LENGTH_LONG).show();
                    return;

                } else {

                    if(filepath29!=null) {
                        final ProgressDialog progressDialog=new ProgressDialog(Tab4PostActivity.this);
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();


                        StorageReference riversRef0 = mstorage29.child("images/Blog Image.jpg");

                        riversRef0.putFile(filepath29)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        progressDialog.dismiss();
                                        @SuppressWarnings("VisibleForTests") final Uri  dowloaduri11= taskSnapshot.getDownloadUrl();
                                        String uid11 = auth29.getCurrentUser().getUid();
                                        DatabaseReference current_user_db10 = mdatabase29.child(uid11).child("Posts");
                                        imp29=dowloaduri11.toString();

                                        finalreference29.child(key29).child("Blog Title").setValue(title29);
                                        finalreference29.child(key29).child("Description Blog").setValue(desc29);
                                        finalreference29.child(key29).child("Link Blog").setValue(link29);





                                        finalreference29.child(key29).child("Blog Image").setValue(imp29);


                                        currentuser_db29.child("Blog Title").setValue(title29);
                                        currentuser_db29.child("Description Blog").setValue(desc29);
                                        currentuser_db29.child("Link Blog").setValue(link29);
                                        currentuser_db29.child("Blog Image").setValue(imp29);

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




                    submitbutton29.setVisibility(View.VISIBLE);
                    submitbutton29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startposting29();
                            Intent i17=new Intent(Tab4PostActivity.this,LoginActivity.class);
                            startActivity(i17);

                        }
                    });
                }
            }
        });




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==galleryrequest29&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            filepath29=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath29);
                imaegeselectview29.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else
        {

            Toast.makeText(getApplicationContext(),"Please select Image",Toast.LENGTH_LONG);

        }


    }

    private void startposting29()
    {
        mprogress29.setMessage("Posting to Blog....");
        mprogress29.show();

        if (TextUtils.isEmpty(desc29)) {
            Toast.makeText(getApplication(), "Enter Description of post", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(link29)) {
            Toast.makeText(getApplication(), "Enter Complete Link", Toast.LENGTH_LONG).show();
            return;

        }


        mprogress29.dismiss();



    }


}
