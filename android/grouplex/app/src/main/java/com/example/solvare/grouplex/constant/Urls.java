package com.example.solvare.grouplex.constant;

import com.example.solvare.grouplex.startup.SharedPrefManager;

/**
 * Created by rishabh on 6/1/17.
 */

public class Urls {
    //static String id=SharedPrefManager.userid();
    //public static final String URL_GROUPS=ROOT_URL+id+"/groups";
    public static final String ROOT_URL="http://192.168.42.131:8080/grouplex/api/v1/";
    public static final String URL_REGISTER=ROOT_URL+"user/register";
    public static final String URL_LOGIN=ROOT_URL+"user/login";
    public static final String URL_CREATE_GROUP=ROOT_URL+"creategroup";
    public static final String URL_SEARCH=ROOT_URL+"search";
    public static final String URL_CHANGE_NAME=ROOT_URL+"update/uname";
    public static final String URL_CHANGE_PASS=ROOT_URL+"update/upass";
}
