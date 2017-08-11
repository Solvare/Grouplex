package com.example.solvare.grouplex.custom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.solvare.grouplex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishabh on 11/8/17.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder>{

    ArrayList<MyMessages> mData;
    private Context context1;


    public MessagesAdapter(Context context, ArrayList<MyMessages> data) {
        super();
        context1 = context;
        this.mData = data;
    }
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_message, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyMessages posts = mData.get(position);
        holder.setData(posts, position);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView messages;
        int position;
        MyMessages posts;

        public MyViewHolder(View itemView){
            super(itemView);
            messages=(TextView)itemView.findViewById(R.id.textView1);
        }

        public void setData(MyMessages posts,int position){
            this.messages.setText(posts.getPost());
            //Log.d("SIZE", Integer.toString(mData.size()));
            //Log.d("POST",posts.getMessages().get(1));
            this.position=position;
            this.posts=posts;
        }
    }
}