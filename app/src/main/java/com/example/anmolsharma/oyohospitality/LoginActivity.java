package com.example.anmolsharma.oyohospitality;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    String items[]={"Register As Employee","Register As Employer"};
    String emailgiv,passgiv;
    int finalposition;
    EditText emailtex,passtex;
    TextView forget21;
    Button donebutton21;
    FirebaseAuth auth21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new AlertDialog.Builder(this)
                .setSingleChoiceItems(items, 0, null)
                .setPositiveButton(R.string.common_signin_button_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        Toast.makeText(getApplicationContext(),"You Want to Login as "+items[selectedPosition],Toast.LENGTH_LONG);
                        // Do something useful withe the position of the selected radio button
                        finalposition=selectedPosition;
                    }
                })
                .show();

        emailtex=(EditText)findViewById(R.id.emailedit21);
        passtex=(EditText)findViewById(R.id.passedit21);
        donebutton21=(Button)findViewById(R.id.donebutton21);
        forget21=(TextView)findViewById(R.id.forget21);


        auth21=FirebaseAuth.getInstance();



        final ProgressDialog progressDialog21=new ProgressDialog(LoginActivity.this);

        forget21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailgiv=emailtex.getText().toString().trim();
                progressDialog21.setTitle("Uploading...");
                progressDialog21.show();
                auth21.sendPasswordResetEmail(emailgiv)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog21.dismiss();
                                    Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    progressDialog21.dismiss();
                                    Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });





            }
        });




    }

    public void done21(View v){

        emailgiv=emailtex.getText().toString().trim();
        passgiv=passtex.getText().toString().trim();

        if (TextUtils.isEmpty(emailgiv)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passgiv)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }


           auth21.signInWithEmailAndPassword(emailgiv, passgiv)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("yo", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if(finalposition==0){
                            Intent i21=new Intent(LoginActivity.this,HomeForEmployees.class);
                            startActivity(i21);
                        }
                        else if(finalposition==1){

                            Intent i21=new Intent(LoginActivity.this,HomeForEmployer.class);
                            startActivity(i21);


                        }


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("yo", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Error21",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }
}
