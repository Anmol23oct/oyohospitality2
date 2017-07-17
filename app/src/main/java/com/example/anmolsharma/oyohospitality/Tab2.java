package com.example.anmolsharma.oyohospitality;

/**
 * Created by anmolsharma on 02/07/17.
 */

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


public class Tab2 extends Fragment {

    private RecyclerView blog_list25;
    private RecyclerView.Adapter rvadapter25;
    private RecyclerView.LayoutManager rvLayoutmanager25;
    String titlem25,descm25,linkm25,imagem25;
    private List<Blog> blg25 = new ArrayList<>();
    Context c25;

    private static final String ARG_SECTION_NUMBER = "position";
    private DatabaseReference mdatabase025,logReference025, finalreference025;
    private FirebaseAuth auth25;
    private FirebaseUser user25;

    public static Tab2 newInstance(int position) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab2() {

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        Context c3=getActivity();
        mdatabase025= FirebaseDatabase.getInstance().getReference().child("Users");
        auth25=FirebaseAuth.getInstance();
        user25=auth25.getCurrentUser();
        logReference025=mdatabase025.child(user25.getUid()).child("Posts");



        blog_list25 = (RecyclerView) rootView.findViewById(R.id.blog_list25);

        mdatabase025 = FirebaseDatabase.getInstance().getReference().child("Users");
        c25=getContext();
        MyAdapter adapter=new MyAdapter(blg25,c25);
        blog_list25.setAdapter(adapter);



        blog_list25.setLayoutManager(new LinearLayoutManager(getActivity()));

        initializedata25();






        return rootView;

    }


    private void initializedata25(){

        auth25=FirebaseAuth.getInstance();
        user25=auth25.getCurrentUser();
        mdatabase025= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference025=mdatabase025.child("Posts Final Tab2");
        blg25 = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(blg25,c25);

        finalreference025.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    titlem25 = child.child("Blog Title").getValue().toString();
                    descm25 = child.child("Description Blog").getValue().toString();
                    linkm25 = child.child("Link Blog").getValue().toString();
                    imagem25 = child.child("Blog Image").getValue().toString();
                    blg25.add(new Blog(titlem25, descm25, linkm25, imagem25));
                    Log.e("ggggg", imagem25);

                }


                MyAdapter m=new MyAdapter(blg25,c25);
                blog_list25.setAdapter(m);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
