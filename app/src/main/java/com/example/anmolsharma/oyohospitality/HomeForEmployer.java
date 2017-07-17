package com.example.anmolsharma.oyohospitality;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeForEmployer extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    // private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    FirebaseAuth auth3 = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_employer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        // mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), HomeForEmployer.this);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setTitle("Home");

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab0 = tabLayout.getTabAt(i);
            tab0.setCustomView(pagerAdapter.getTabView(i));
        }

    }

    public void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_for_employer, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        };

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent i5 = new Intent(this, UpdateProfileEmployer.class);
            startActivity(i5);

            return true;
        } else if (id == R.id.action_add4) {
            auth3.signOut();

// this listener will be called when there is change in firebase user session
            FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(HomeForEmployer.this, Secondpage.class));
                        finish();
                    }
                }
            };

            return true;
        } else if (id == R.id.action_add5) {

            Intent i0 = new Intent(HomeForEmployer.this, Tab1PostActivity.class);
            startActivity(i0);

            return true;

        }


        return super.onOptionsItemSelected(item);
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        Context context;
        String tabtitles[] = {"DRIVER", "HOSPITALITY", "HOUSEKEEPING", "OTHERS"};

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;

                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;

                case 2:
                    Tab3 tab3 = new Tab3();
                    return tab3;

                case 3:
                    Tab4 tab4 = new Tab4();
                    return tab4;
                default:
                    return null;
            }
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DRIVER";
                case 1:
                    return "HOSPITALITY";
                case 2:
                    return "HOUSEKEEPING";
                case 3:
                    return "OTHERS";
            }
            return null;
        }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
  /*  public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Tab1 tab1=new Tab1();
                    return tab1;

                case 1:
                    Tab2 tab2=new Tab2();
                    return tab2;

                case 2:
                    Tab3 tab3=new Tab3();
                    return tab3;

                case 3:
                    Tab4 tab4=new Tab4();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DRIVER";
                case 1:
                    return "HOSPITALITY";
                case 2:
                    return "HOUSEKEEPING";
                case 3:
                    return "OTHERS";
            }
            return null;
        }
    }*/

        public View getTabView(int position) {
            View tab = LayoutInflater.from(HomeForEmployer.this).inflate(R.layout.custom_tab, null);

            TextView tv = (TextView) tab.findViewById(R.id.customtest);
            tv.setText(tabtitles[position]);
            return tab;

        }
    }
}