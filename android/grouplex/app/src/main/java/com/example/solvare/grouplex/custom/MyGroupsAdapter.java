package com.example.solvare.grouplex.custom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;

import java.util.ArrayList;
import java.util.List;

public class MyGroupsAdapter extends RecyclerView.Adapter<MyGroupsAdapter.MyViewHolder> {

    ArrayList<MyGroups> mData;
    private Context context;


    public MyGroupsAdapter(Context context, ArrayList<MyGroups> data) {
        super();
        this.context = context;
        this.mData = data;
    }

    @Override
    public MyGroupsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_groups, parent, false);
        MyViewHolder holder = new MyViewHolder(view, context, mData);
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


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView groupname, level, num_members;
        int position;
        MyGroups groups;
        ImageView imgDelete;
        Context ctx;
        List<MyGroups> mData = new ArrayList<>();

        public MyViewHolder(View itemView, Context ctx, ArrayList<MyGroups> mData) {
            super(itemView);
            this.ctx = ctx;
            this.mData = mData;
            itemView.setOnClickListener(this);
            groupname = (TextView) itemView.findViewById(R.id.group_name);
            level = (TextView) itemView.findViewById(R.id.your_level);
            num_members = (TextView) itemView.findViewById(R.id.total_members);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_row_delete);
        }

        public void setData(MyGroups groups, int position) {
            this.groupname.setText(groups.get_groupnames().get(position));
            Log.d("Messages", groups.get_groupnames().get(position));
            this.level.setText(groups.getLevel().get(position));
            this.num_members.setText(groups.getNumMembers().get(position));
            this.position = position;
            this.groups = groups;
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            MyGroups groups = this.mData.get(position);
            Intent intent = new Intent(this.ctx, MessageDetails.class);
            Log.d("position1", Integer.toString(Integer.parseInt(groups.getIds().get(position))));
            int groupId = Integer.parseInt(groups.getIds().get(position));
            String groupName = groups.get_groupnames().get(position);
            String userLevel = groups.getLevel().get(position);
            intent.putExtra("integerValue", groupId);
            intent.putExtra("groupName", groupName);
            intent.putExtra("userLevel", userLevel);
            this.ctx.startActivity(intent);
            Urls url = new Urls();
            url.setGroupId(groupId);

        }
    }

}