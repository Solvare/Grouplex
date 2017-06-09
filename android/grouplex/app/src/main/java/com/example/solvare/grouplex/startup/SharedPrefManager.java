package com.example.solvare.grouplex.startup;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.solvare.grouplex.startup.RequestHandler;


/**
 * Created by rishabh on 25/3/17.
 */

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID="user_id";
    public static final String KEY_USERNAME="full_name";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean userLogin(String email,String username,String id){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_ID,id);
        editor.apply();

        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_EMAIL,null)!=null){
            return true;
        }
        return false;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    /*public static String userid(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID,null);
    }*/
}
