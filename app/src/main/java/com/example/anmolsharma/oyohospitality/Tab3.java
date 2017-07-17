package com.example.anmolsharma.oyohospitality;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anmolsharma on 02/07/17.
 */

public class Tab3 extends Fragment {

    private RecyclerView blog_list27;
    private RecyclerView.Adapter rvadapter27;
    private RecyclerView.LayoutManager rvLayoutmanager27;
    String titlem27,descm27,linkm27,imagem27;
    private List<Blog> blg27 = new ArrayList<>();
    Context c27;

    private static final String ARG_SECTION_NUMBER = "position";
    private DatabaseReference mdatabase027,logReference027, finalreference027;
    private FirebaseAuth auth27;
    private FirebaseUser user27;

    public static Tab3 newInstance(int position) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab3() {

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        Context c3=getActivity();
        mdatabase027= FirebaseDatabase.getInstance().getReference().child("Users");
        auth27=FirebaseAuth.getInstance();
        user27=auth27.getCurrentUser();
        logReference027=mdatabase027.child(user27.getUid()).child("Posts");



        blog_list27 = (RecyclerView) rootView.findViewById(R.id.blog_list27);

        mdatabase027 = FirebaseDatabase.getInstance().getReference().child("Users");
        c27=getContext();
        MyAdapter adapter=new MyAdapter(blg27,c27);
        blog_list27.setAdapter(adapter);



        blog_list27.setLayoutManager(new LinearLayoutManager(getActivity()));

        initializedata27();


        return rootView;
    }
    private void initializedata27(){

        auth27=FirebaseAuth.getInstance();
        user27=auth27.getCurrentUser();
        mdatabase027= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference027=mdatabase027.child("Posts Final Tab3");
        blg27 = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(blg27,c27);

        finalreference027.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    titlem27 = child.child("Blog Title").getValue().toString();
                    descm27 = child.child("Description Blog").getValue().toString();
                    linkm27 = child.child("Link Blog").getValue().toString();
                    imagem27 = child.child("Blog Image").getValue().toString();
                    blg27.add(new Blog(titlem27, descm27, linkm27, imagem27));
                    Log.e("ggggg", imagem27);

                }


                MyAdapter m=new MyAdapter(blg27,c27);
                blog_list27.setAdapter(m);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}


