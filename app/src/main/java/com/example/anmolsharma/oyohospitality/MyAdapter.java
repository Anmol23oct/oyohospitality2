package com.example.anmolsharma.oyohospitality;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static java.security.AccessController.getContext;

/**
 * Created by anmolsharma on 12/07/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   // private ArrayList<Blog> Blogs;
    //private String mdataset[];
   static List<Blog> skill;
  //  ArrayList<Blog> bl;
    Context c4;
    Uri uri;
    static int pos;
    InputStream image_stream;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView mCardview;
        public TextView mTextTitle,mTextdesc,mTestlink,mTextView;
        final public ImageView mimage;
        Context c5;
        List<Blog> skills0= new List<Blog>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Blog> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(Blog blog) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Blog> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Blog> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Blog get(int index) {
                return null;
            }

            @Override
            public Blog set(int index, Blog element) {
                return null;
            }

            @Override
            public void add(int index, Blog element) {

            }

            @Override
            public Blog remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Blog> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Blog> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Blog> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        public MyViewHolder(View v,Context c5,List<Blog> skills0){
            super(v);
            this.skills0=skills0;
            this.c5=c5;
            v.setOnClickListener(this);
           mCardview=(CardView)v.findViewById(R.id.card_view);
            mTextView=(TextView)v.findViewById(R.id.post_title13);
           //mTextTitle=(TextView)v.findViewById(R.id.post_title13);
            mTextdesc=(TextView)v.findViewById(R.id.post_desc13);
            mTestlink=(TextView)v.findViewById(R.id.post_link13);
            mimage=(ImageView) v.findViewById(R.id.post_image13);

        }


        @Override
        public void onClick(View v) {

            int position =getAdapterPosition();
            Blog blog=this.skills0.get(position);
            String link19=blog.getLink0();
            Log.e("Click Event ",link19);
            if (!link19.startsWith("http://") && !link19.startsWith("https://"))
                link19 = "http://" + link19;

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link19));
            c5.startActivity(browserIntent);

        }
    }


    public MyAdapter(List<Blog> skills, Context c4){

        this.skill=skills;
        this.c4=c4;
        notifyDataSetChanged();
    }

    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab1_blogrow,parent,false);
        MyViewHolder vh=new MyViewHolder(v,c4,skill);
        Log.e("jam","uri isaaaa" +uri);



        return vh;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mTextView.setText(skill.get(position).getTitle0());

        //String stringUri;
        uri = Uri.parse(skill.get(position).getImage0());
        Log.e("jam","uri is" +uri);
        //try {
         //   image_stream = c4.getContentResolver().openInputStream(uri);
        //} catch (FileNotFoundException e) {
         //   e.printStackTrace();
       // }
     //   Bitmap bitmap = BitmapFactory.decodeStream(image_stream);*/
        // holder.mTextTitle.setText(Blogs.get(position).getTitle0());
        holder.mTextdesc.setText(skill.get(position).getDesc0());
        holder.mTestlink.setText(skill.get(position).getLink0());
        //this.pos=position;
        //holder.mimage.setImageBitmap(bitmap);
        // Picasso.with().load(skill.get(position).getImage0()).into(mimage);

        Glide
                .with(c4)
                .load(uri) // the uri you got from Firebase
                .centerCrop()
                .into(holder.mimage); //Your imageView variable*/
    }

    @Override
    public int getItemCount() {
        return skill.size();
    }



}
