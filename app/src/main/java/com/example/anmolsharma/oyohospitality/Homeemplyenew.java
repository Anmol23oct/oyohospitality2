package com.example.anmolsharma.oyohospitality;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Homeemplyenew extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    private TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeemplyenew);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setElevation(0f);//from this we are avoiding  colliding our action bar with tabs

        tabs=(TabLayout)findViewById(R.id.tabs);

        tabs.addTab(
                tabs.newTab().setText("Driver")
        );

        tabs.addTab(
                tabs.newTab().setText("Hospitality")
        );

        tabs.addTab(
                tabs.newTab().setText("HouseKeeping")
        );

        tabs.setTabTextColors(ContextCompat.getColor(this,android.R.color.white),ContextCompat.getColor(this,R.color.colorPrimary));

        tabs.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));

        tabs.addOnTabSelectedListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
