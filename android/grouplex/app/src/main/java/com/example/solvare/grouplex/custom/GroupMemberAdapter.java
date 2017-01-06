package com.example.solvare.grouplex.custom;
 
import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.solvare.grouplex.R;

public class GroupMemberAdapter extends ArrayAdapter<Items> {

    private final Context context;
    private final ArrayList<Items> itemsArrayList;

    public GroupMemberAdapter(Context context, ArrayList<Items> itemsArrayList) {

        super(context, R.layout.item_group_members, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.item_group_members, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.member_name);
        TextView valueView = (TextView) rowView.findViewById(R.id.member_email);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getTitle());
        valueView.setText(itemsArrayList.get(position).getDescription());

        // 5. retrn rowView
        return rowView;
    }
}