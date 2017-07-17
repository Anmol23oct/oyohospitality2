package com.example.anmolsharma.oyohospitality;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Secondpage extends AppCompatActivity {


    Button b1,b2,b3,b4;
 //   private FirebaseAuth auth;
 //   private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);


        Log.e("AS", " PROCEED");
        b1 = (Button) findViewById(R.id.Rempb);
        b2 = (Button) findViewById(R.id.Remrb);
        b3 = (Button) findViewById(R.id.Rrefb);
        b4 = (Button) findViewById(R.id.logb);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("AS", " PROCEED Final");
                final Intent i = new Intent(Secondpage.this, Registeremployee.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent i1=new Intent(Secondpage.this,Registeremployer.class);
                startActivity(i1);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent i17=new Intent(Secondpage.this,LoginActivity.class);
                startActivity(i17);
            }
        });
    //    auth = FirebaseAuth.getInstance();

    //    authStateListener = new FirebaseAuth.AuthStateListener() {
          //  @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

  //              if (firebaseAuth.getCurrentUser() == null) {


                 /*   b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final Intent i1=new Intent(this,Registeremployer.class);
                            startActivity(i1);
                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final Intent i2=new Intent(this,Registerrefferal.class);
                            startActivity(i2);

                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final Intent i3=new Intent(this,Login.class);
                            startActivity(i3);
                        }
                    });*/

      //          } else {

                    //if user has already registered

        //        }

        //    }


     //   };
      //  auth.addAuthStateListener(authStateListener);
    }

  /*  public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }*/


}
