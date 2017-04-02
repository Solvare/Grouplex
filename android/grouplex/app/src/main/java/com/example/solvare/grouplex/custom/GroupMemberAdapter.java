package com.example.solvare.grouplex.custom;
 
import java.util.ArrayList;
import java.util.List;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solvare.grouplex.R;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.MyViewHolder> {

    List<GroupMembers> mData;
    private Context context;

    public GroupMemberAdapter(Context context, List<GroupMembers> data) {
        super();
        this.context = context;
        this.mData = data;
    }
    @Override
    public GroupMemberAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_groups, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(GroupMemberAdapter.MyViewHolder holder, int position) {
        GroupMembers members = mData.get(position);
        holder.setData(members, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView groupname,level,num_members;
        int position;
        GroupMembers members;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupname=(TextView)itemView.findViewById(R.id.group_name);
            level=(TextView)itemView.findViewById(R.id.your_level);
            num_members=(TextView)itemView.findViewById(R.id.total_members);
        }
        public void setData(GroupMembers members,int position){
            this.groupname.setText(members.get_groupnames().get(position));
            this.level.setText(members.getLevel().get(position));
            this.num_members.setText(members.getNumMembers().get(position));
            this.position=position;
            this.members=members;
        }
    }

}