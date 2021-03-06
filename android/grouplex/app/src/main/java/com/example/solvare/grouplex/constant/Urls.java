package com.example.solvare.grouplex.constant;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

/**
 * Created by rishabh on 6/1/17.
 */

public class Urls {

    String id;
    public String URL_GROUPS, URL_SEARCH;
    public String URL_READ_MESSAGE;
    public static int groupId;

    public Urls() {
    }

    public Urls(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(KEY_ID, null);
        URL_GROUPS = ROOT_URL + id + "/groups";
        URL_SEARCH = ROOT_URL + "/search/" + id;

    }

    public static int getGroupId() {
        return groupId;
    }

    public static void setGroupId(int groupId) {
        Urls.groupId = groupId;
    }

    public String readUrl() {
        //URL_READ_MESSAGE="http://192.168.43.140:8081/api/v1/"+getGroupId()+"/messages";
        URL_READ_MESSAGE = "http://grouplex.000webhostapp.com/api/v1/" + getGroupId() + "/messages";
        return URL_READ_MESSAGE;
    }

    public static final String ROOT_URL = "http://grouplex.000webhostapp.com/api/v1/";
    //public static final String ROOT_URL="http://grouplex.esy.es/api/v1/";
    //public static final String ROOT_URL="http://192.168.43.140:8081/api/v1/";
    public static final String URL_REGISTER = ROOT_URL + "user/register";
    public static final String URL_LOGIN = ROOT_URL + "user/login";
    public static final String URL_CREATE_GROUP = ROOT_URL + "creategroup";
    public static final String URL_JOIN_GROUP = ROOT_URL + "joingroup";
    public static final String URL_CHANGE_NAME = ROOT_URL + "update/uname";
    public static final String URL_CHANGE_PASS = ROOT_URL + "update/upass";
    public static final String URL_POST_MESSAGE = ROOT_URL + "/message";
    public static final String URL_OTP_SEND = ROOT_URL + "/otp/send";
    public static final String URL_OTP_PASS = ROOT_URL + "/otp/password";
    public static final String URL_OTP_EMAIL = ROOT_URL + "/otp/email";
}
