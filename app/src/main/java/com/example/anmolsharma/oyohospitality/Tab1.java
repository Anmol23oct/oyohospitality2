package com.example.anmolsharma.oyohospitality;

/**
 * Created by anmolsharma on 02/07/17.
 */
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


public class Tab1 extends Fragment {


    private RecyclerView blog_list;
    private RecyclerView.Adapter rvadapter0;
    private RecyclerView.LayoutManager rvLayoutmanager0;
     String titlem,descm,linkm,imagem;
    private List<Blog> blg = new ArrayList<>();
    Context c1;

    private static final String ARG_SECTION_NUMBER = "position";
    private DatabaseReference mdatabase15,logReference, finalreference1;
    private FirebaseAuth auth9;
    private FirebaseUser user15;

    public static Tab1 newInstance(int position) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    public Tab1() {

    }

        // Required empty public constructor


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1, container, false);


        Context c3=getActivity();
        mdatabase15=FirebaseDatabase.getInstance().getReference().child("Users");
        auth9=FirebaseAuth.getInstance();
        user15=auth9.getCurrentUser();
        logReference=mdatabase15.child(user15.getUid()).child("Posts");

        //loadenteries();


     //   blog_list=(RecyclerView)rootView.findViewById(R.id.blog_list);
       // rvLayoutmanager0=new LinearLayoutManager(getActivity());
       // blog_list.setLayoutManager(rvLayoutmanager0);


       // rvadapter0=new MyAdapter(Blogs);
        //blog_list.setAdapter(rvadapter0);

    /*    Tab1PostActivity t=new Tab1PostActivity();
       final Blog b=new Blog();
        bl=new ArrayList<Blog>();

        b.setDesc0(t.desc5);
        b.setLink0(t.link5);
        b.setTitle0(t.title5);
        b.setImage0(t.imp);
        bl.add(b);
        Log.e("DESC",b.getDesc0());

      logReference.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              for(DataSnapshot d : dataSnapshot.getChildren())
              {
                  Blog contact = new Blog();
                  contact.setDesc0(d.getValue(Blog.class).getDesc0());
                  contact.setTitle0(d.getValue(Blog.class).getTitle0());
                  contact.setLink0(d.getValue(Blog.class).getLink0());
                  Log.e("DESC",b.getDesc0());

              }
          }

          @Override
          public void onChildChanged(DataSnapshot dataSnapshot, String s) {

          }

          @Override
          public void onChildRemoved(DataSnapshot dataSnapshot) {

          }

          @Override
          public void onChildMoved(DataSnapshot dataSnapshot, String s) {

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });*/



       blog_list = (RecyclerView) rootView.findViewById(R.id.blog_list);
        //blog_list.setHasFixedSize(true);
        //blog_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //auth9=FirebaseAuth.getInstance();
        //String uid9=auth9.getCurrentUser().getUid();
        mdatabase15 = FirebaseDatabase.getInstance().getReference().child("Users");
         c1=getContext();
        MyAdapter adapter=new MyAdapter(blg,c1);
        blog_list.setAdapter(adapter);

      //  LinearLayout llm=new LinearLayout(getActivity());
        //blog_list.setLayoutManager(llm);


        blog_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        initializedata();
        return rootView;

    }

   private void initializedata(){

      // mdatabase15=FirebaseDatabase.getInstance().getReference().child("Users");
       auth9=FirebaseAuth.getInstance();
       user15=auth9.getCurrentUser();
       mdatabase15= FirebaseDatabase.getInstance().getReference().child("Users");
       finalreference1=mdatabase15.child("Posts Final Tab1");
       blg = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(blg,c1);

     finalreference1.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {

             for (DataSnapshot child: dataSnapshot.getChildren()) {

                 titlem = child.child("Blog Title").getValue().toString();
                 descm = child.child("Description Blog").getValue().toString();
                 linkm = child.child("Link Blog").getValue().toString();
                 imagem = child.child("Blog Image").getValue().toString();
                 blg.add(new Blog(titlem, descm, linkm, imagem));
          /*   Uri uri = Uri.parse(MyAdapter.skill.get(MyAdapter.pos).getImage0());
             Glide
                     .with(getContext())
                     .load(uri) // the uri you got from Firebase
                     .centerCrop()
                     .into(MyAdapter.mimage); //Your imageView variable*/
                 Log.e("ggggg", imagem);

             }


             MyAdapter m=new MyAdapter(blg,c1);
             blog_list.setAdapter(m);

         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });

   }


   /* private void loadenteries(){
        Blogs=new ArrayList<>();
        logReference=mdatabase15.child(user15.getUid());
        ValueEventListener valueEventListener0=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Blogs.add(ds.getValue(Blog.class));

                }
                Blog spacecraft=dataSnapshot.getValue(Blog.class);
                Blogs.add(spacecraft);
                System.out.println();

                rvadapter0.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        logReference.addValueEventListener(valueEventListener0);




    }*/


   /* public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.tab1_blogrow,
                BlogViewHolder.class,
                mdatabase15
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(model.getTitle0());
                viewHolder.setdesc(model.getDesc0());
                viewHolder.setlink(model.getLink0());
                viewHolder.setimage(getContext(), model.getImage0());


            }
        };


        blog_list.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {


        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setTitle(String title) {

            TextView post_title = (TextView) mView.findViewById(R.id.post_title13);
            post_title.setText(title);


        }

        public void setdesc(String desc) {
            TextView post_Desc = (TextView) mView.findViewById(R.id.post_desc13);
            post_Desc.setText(desc);

        }

        public void setlink(String link) {
            TextView post_link = (TextView) mView.findViewById(R.id.post_link13);
            post_link.setText(link);

        }

        public void setimage(Context ctx, String image) {

            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image13);
            Picasso.with(ctx).load(image).into(post_image);

        }


    }*/


}
