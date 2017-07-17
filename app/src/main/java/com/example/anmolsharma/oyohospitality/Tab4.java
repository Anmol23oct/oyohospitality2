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

public class Tab4 extends Fragment {



    private RecyclerView blog_list29;
    private RecyclerView.Adapter rvadapter29;
    private RecyclerView.LayoutManager rvLayoutmanager29;
    String titlem29,descm29,linkm29,imagem29;
    private List<Blog> blg29 = new ArrayList<>();
    Context c29;

    private static final String ARG_SECTION_NUMBER = "position";
    private DatabaseReference mdatabase029,logReference029, finalreference029;
    private FirebaseAuth auth29;
    private FirebaseUser user29;

    public static Tab4 newInstance(int position) {
        Tab4 fragment = new Tab4();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab4() {

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4, container, false);


        Context c3=getActivity();
        mdatabase029= FirebaseDatabase.getInstance().getReference().child("Users");
        auth29=FirebaseAuth.getInstance();
        user29=auth29.getCurrentUser();
        logReference029=mdatabase029.child(user29.getUid()).child("Posts");



        blog_list29 = (RecyclerView) rootView.findViewById(R.id.blog_list29);

        mdatabase029 = FirebaseDatabase.getInstance().getReference().child("Users");
        c29=getContext();
        MyAdapter adapter=new MyAdapter(blg29,c29);
        blog_list29.setAdapter(adapter);



        blog_list29.setLayoutManager(new LinearLayoutManager(getActivity()));

        initializedata29();





        return rootView;
    }


    private void initializedata29(){

        auth29=FirebaseAuth.getInstance();
        user29=auth29.getCurrentUser();
        mdatabase029= FirebaseDatabase.getInstance().getReference().child("Users");
        finalreference029=mdatabase029.child("Posts Final Tab4");
        blg29 = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(blg29,c29);

        finalreference029.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {

                    titlem29 = child.child("Blog Title").getValue().toString();
                    descm29 = child.child("Description Blog").getValue().toString();
                    linkm29 = child.child("Link Blog").getValue().toString();
                    imagem29 = child.child("Blog Image").getValue().toString();
                    blg29.add(new Blog(titlem29, descm29, linkm29, imagem29));
                    Log.e("ggggg", imagem29);

                }


                MyAdapter m=new MyAdapter(blg29,c29);
                blog_list29.setAdapter(m);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
