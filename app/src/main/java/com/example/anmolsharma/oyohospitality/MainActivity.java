package com.example.anmolsharma.oyohospitality;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView welcometext;
    ProgressBar pbar;
    ImageButton oyobutton;
    int i;
    float f;
    CountDownTimer mCountDownTimer;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcometext = (TextView) findViewById(R.id.welcometext);
        pbar = (ProgressBar) findViewById(R.id.pbar);
        oyobutton = (ImageButton) findViewById(R.id.symbolbutton);

        i = 0;
        pbar.setMax(5);
        pbar.setProgress(i);
        mCountDownTimer = new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                pbar.setProgress(i);
                f = (float) (f + 0.2);
                // Log.i("pbarvalued =", String.valueOf(f));
                welcometext.setAlpha(f);
                oyobutton.setAlpha(f);
            }

            @Override
            public void onFinish() {
                i++;
                pbar.setProgress(i);
                f = (float) (f + 0.2);
                //   Log.i("pbarvalued =", String.valueOf(f));
                welcometext.setAlpha(f);
                oyobutton.setAlpha(f);
            }

        };
        mCountDownTimer.start();
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                   oyobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent(MainActivity.this, Secondpage.class);
                startActivity(intent);
            }
        });
                    Log.d("hh", "onAuthStateChanged:signed_in:" + user.getUid());


                } else {


                    oyobutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final Intent intent = new Intent(MainActivity.this, Secondpage.class);
                            startActivity(intent);
                        }
                    });
                    // User is signed out
                    Log.e("fff", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        auth.addAuthStateListener(authStateListener);

        /*oyobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent(MainActivity.this, Secondpage.class);
                startActivity(intent);
            }
        });*/
    }




       public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

}


