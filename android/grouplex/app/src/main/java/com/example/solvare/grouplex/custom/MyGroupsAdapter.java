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

public class MyGroupsAdapter extends RecyclerView.Adapter<MyGroupsAdapter.MyViewHolder> {

    List<MyGroups> mData;
    private Context context;

    public MyGroupsAdapter(Context context, List<MyGroups> data) {
        super();
        this.context = context;
        this.mData = data;
    }
    @Override
    public MyGroupsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_groups, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyGroupsAdapter.MyViewHolder holder, int position) {
        MyGroups groups = mData.get(position);
        holder.setData(groups, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView groupname,level,num_members;
        int position;
        MyGroups groups;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupname=(TextView)itemView.findViewById(R.id.group_name);
            level=(TextView)itemView.findViewById(R.id.your_level);
            num_members=(TextView)itemView.findViewById(R.id.total_members);
        }
        public void setData(MyGroups groups,int position){
            this.groupname.setText(groups.get_groupnames().get(position));
            this.level.setText(groups.getLevel().get(position));
            this.num_members.setText(groups.getNumMembers().get(position));
            this.position=position;
            this.groups=groups;
        }
    }

}