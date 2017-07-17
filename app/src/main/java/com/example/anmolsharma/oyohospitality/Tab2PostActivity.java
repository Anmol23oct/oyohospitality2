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

public class Tab2PostActivity extends AppCompatActivity {


    ImageButton imageselect25;
    EditText edittitle25,editdescrption25,editlink25;
    Button submitbutton25,rightposttitle25,submitallbutton25;
    Uri imageuri25;
    String title25,link25,desc25,uid25,imp25,key25;
    private StorageReference mstorage25,filepath250;
    ImageView imaegeselectview25;
    private FirebaseAuth auth25;
    FirebaseUser user25= FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog mprogress25;
    private DatabaseReference mdatabase25,currentuser_db25,finalreference25;
    private Uri filepath25;

    private final static int galleryrequest25=1;

    public void Tab2PostActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2_post);

        imageselect25=(ImageButton)findViewById(R.id.imageselect25);
        edittitle25=(EditText)findViewById(R.id.edittitle25);
        editlink25=(EditText)findViewById(R.id.editlink25);
        editdescrption25=(EditText)findViewById(R.id.editdescription25);
        submitbutton25=(Button)findViewById(R.id.donebutton25);
        rightposttitle25=(Button)findViewById(R.id.rightbuttonposttitle25);
        submitallbutton25=(Button)findViewById(R.id.donebutton025);
        imaegeselectview25=(ImageView)findViewById(R.id.imageselectview25);
        auth25=FirebaseAuth.getInstance();
        mstorage25= FirebaseStorage.getInstance().getReference();
        mdatabase25= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference25=mdatabase25.child("Posts Final Tab2");
        currentuser_db25=mdatabase25.child(user25.getUid()).child("Posts");
        mprogress25=new ProgressDialog(this);
        uid25=user25.getUid();
        key25=   finalreference25.child("Blog Image").push().getKey();

        imageselect25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageselect25.setVisibility(View.INVISIBLE);
                imaegeselectview25.setVisibility(View.VISIBLE);
                Intent galleryintent6=new Intent();
                galleryintent6.setType("image/*");
                galleryintent6.setAction(galleryintent6.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryintent6,"select an Image"),galleryrequest25);

            }
        });

        rightposttitle25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title25 = edittitle25.getText().toString().trim();
                desc25=editdescrption25.getText().toString().trim();
                link25=editlink25.getText().toString().trim();

                if (TextUtils.isEmpty(title25)) {
                    Toast.makeText(getApplication(), "Enter Title", Toast.LENGTH_LONG).show();
                    return;

                } else {

                    if(filepath25!=null) {
                        final ProgressDialog progressDialog=new ProgressDialog(Tab2PostActivity.this);
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();


                        StorageReference riversRef0 = mstorage25.child("images/Blog Image.jpg");

                        riversRef0.putFile(filepath25)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        progressDialog.dismiss();
                                        @SuppressWarnings("VisibleForTests") final Uri  dowloaduri11= taskSnapshot.getDownloadUrl();
                                        String uid11 = auth25.getCurrentUser().getUid();
                                        DatabaseReference current_user_db10 = mdatabase25.child(uid11).child("Posts");
                                        imp25=dowloaduri11.toString();

                                        finalreference25.child(key25).child("Blog Title").setValue(title25);
                                        finalreference25.child(key25).child("Description Blog").setValue(desc25);
                                        finalreference25.child(key25).child("Link Blog").setValue(link25);





                                        finalreference25.child(key25).child("Blog Image").setValue(imp25);


                                        currentuser_db25.child("Blog Title").setValue(title25);
                                        currentuser_db25.child("Description Blog").setValue(desc25);
                                        currentuser_db25.child("Link Blog").setValue(link25);
                                        currentuser_db25.child("Blog Image").setValue(imp25);

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




                    submitbutton25.setVisibility(View.VISIBLE);
                    submitbutton25.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startposting25();
                            Intent i17=new Intent(Tab2PostActivity.this,LoginActivity.class);
                            startActivity(i17);

                        }
                    });
                }
            }
        });



    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==galleryrequest25&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            filepath25=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath25);
                imaegeselectview25.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else
        {

            Toast.makeText(getApplicationContext(),"Please select Image",Toast.LENGTH_LONG);

        }


    }

    private void startposting25()
    {
        mprogress25.setMessage("Posting to Blog....");
        mprogress25.show();

        if (TextUtils.isEmpty(desc25)) {
            Toast.makeText(getApplication(), "Enter Description of post", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(link25)) {
            Toast.makeText(getApplication(), "Enter Complete Link", Toast.LENGTH_LONG).show();
            return;

        }


        mprogress25.dismiss();



    }



}
