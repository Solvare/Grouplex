package com.example.solvare.grouplex.custom;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.solvare.grouplex.R;

public class JoinGroupAdapter extends RecyclerView.Adapter<JoinGroupAdapter.MyViewHolder> {

    List<JoinGroup> mData;
    private Context context;

    public JoinGroupAdapter(Context context, List<JoinGroup> data) {
        super();
        this.context = context;
        this.mData = data;
    }
    @Override
    public JoinGroupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_join_group_new, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(JoinGroupAdapter.MyViewHolder holder, int position) {
        JoinGroup groups = mData.get(position);
        holder.setData(groups, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, members;
        int position;
        JoinGroup groups;

        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.join_group_name);
            members=(TextView)itemView.findViewById(R.id.join_num_members);
        }

        public void setData(JoinGroup groups,int position){
            this.name.setText(groups.getNames().get(position));
            this.members.setText(groups.getMembers().get(position) + " members");
            this.position=position;
            this.groups=groups;
        }
    }

}