package com.example.solvare.grouplex;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.solvare.grouplex.custom.GroupMemberAdapter;
import com.example.solvare.grouplex.custom.GroupMembers;
import com.example.solvare.grouplex.custom.SimpleDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.solvare.grouplex.R.id.recyclerView;

public class MyGroupsActivity extends AppCompatActivity {

    //private ArrayAdapter<String> groupsAdapter;
    private List<GroupMembers> mData;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);
       recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        /*recyclerView.addItemDecoration(new

                SimpleDividerItemDecoration(this)

        );*/

        recyclerView.setItemAnimator(new

                DefaultItemAnimator()

        ); // Even if we dont use it then also our items shows default animation. #Check Docs

        //mData=new ArrayList<>();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {



        StringRequest stringRequest = new StringRequest(Urls.URL_GROUPS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseData(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding request to the queue
        requestQueue.add(stringRequest);



    }
    GroupMembers members = new GroupMembers();
    private void parseData(String jsonStr) throws JSONException {

        ArrayList<GroupMembers> dataList = new ArrayList<>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> levels = new ArrayList<String>();
        JSONObject jsonObj = new JSONObject(jsonStr);
        //Log.d("TAG-CHECK", jsonStr);
        JSONArray jsonarray=jsonObj.getJSONArray("groups");
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            names.add(jsonobject.getString("name"));
            levels.add(jsonobject.getString("level"));
        }
        members.set_groupnames(names);
        members.setLevel(levels);
        //int a=members.getLevel().size();
        //Log.d("OUTPUT",Integer.toString(a));
        String[] level=new String[members.getLevel().size()];
        String[] groupsNames=new String[members.get_groupnames().size()];

        for(int i=0;i<members.getLevel().size();i++){

            level[i]=members.getLevel().get(i);
            //Log.d("myTag2", "This is my message");
        }
        for(int i=0;i<members.get_groupnames().size();i++){
            groupsNames[i]=members.get_groupnames().get(i);
        }
        for (int i = 0; i < members.get_groupnames().size(); i++) {

            members.s_set_groupnames(members.get_groupnames().get(i));
            members.s_setLevel(members.getLevel().get(i));
            //Log.d("OUTPUT",members.get_groupnames().get(i));
            dataList.add(members);



        }
        /*for (int i = 0; i < members.get_groupnames().size(); i++) {

            //Log.d("1",members.s_get_groupnames());
            //Log.d("2",members.s_getLevel());
            Log.d("OUTPUT", String.valueOf(dataList.get(i)));


            //int size=dataList.size();
            //Log.d("SIZE", Integer.toString(size));

        }*/
        int size=dataList.size();
        Log.d("SIZE", Integer.toString(size));
        adapter = new GroupMemberAdapter(this, getData(dataList));
        recyclerView.setAdapter(adapter);

    }
    public  ArrayList<GroupMembers> getData(ArrayList<GroupMembers> list) {
        ArrayList<GroupMembers> dataList = new ArrayList<>();
//---------------------------------------------------------------------------
        dataList=list;
        /*String[] groupsNames = {
                "CS_402","CS-203"
        };
        String[] level={
            "Admin","Admin"
        };*/


        //int a=members.getLevel().size();
        //String b=String(a);
        //Log.d("OUTPUT",Integer.toString(a));
        /*String[] level1=new String[members.getLevel().size()];
        String[] groupsNames1=new String[members.get_groupnames().size()];

        for(int i=0;i<members.getLevel().size();i++){

             level1[i]=members.getLevel().get(i);
            Log.d("myTag2", "This is my message");
        }
        for(int i=0;i<members.get_groupnames().size();i++){
            groupsNames[i]=members.get_groupnames().get(i);
        }*/

//--------------------------------------------------------------------------------------

        /*for (int i = 0; i < groupsNames.length; i++) {

            members.s_set_groupnames(groupsNames[i]);
            members.s_setLevel(level[i]);

            dataList.add(members);
            //int size=dataList.size();
            //Log.d("SIZE", Integer.toString(size));

        }*/
        return dataList;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //exitByBackKey();
            logout();
            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(LoginActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(LoginActivity.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(LoginActivity.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MyGroupsActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.join:
                joinGroup();
                return true;
            case R.id.create:
                createGroup();
                return true;
            case R.id.settings:
                settings();
                return true;
            case R.id.about:
                about();
                return true;
            case R.id.menuLogout:
                logout();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void joinGroup() {
        Intent intent = new Intent(MyGroupsActivity.this, JoinGroupActivity.class);
        startActivity(intent);
    }

    private void createGroup() {
        Intent intent = new Intent(MyGroupsActivity.this, CreateGroupActivity.class);
        startActivity(intent);
    }

    private void settings() {
        Intent intent = new Intent(MyGroupsActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

    private void about() {
        Intent intent = new Intent(MyGroupsActivity.this, AboutActivity.class);
        startActivity(intent);
    }

}
