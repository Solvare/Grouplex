package com.example.solvare.grouplex.custom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.solvare.grouplex.R;

import java.util.List;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.MyViewHolder> {

    private List<About> aboutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView heading, content;

        public MyViewHolder(View view) {
            super(view);
            heading = (TextView) view.findViewById(R.id.textview_about_heading);
            content = (TextView) view.findViewById(R.id.textview_about_content);
        }
    }


    public AboutAdapter(List<About> aboutList) {
        this.aboutList = aboutList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_about, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        About about = aboutList.get(position);
        holder.heading.setText(about.getHeading());
        holder.content.setText(about.getContent());
    }

    @Override
    public int getItemCount() {
        return aboutList.size();
    }
}