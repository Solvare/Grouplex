package com.example.solvare.grouplex.custom;

import android.util.Log;

import com.example.solvare.grouplex.R;

import java.util.ArrayList;

public class MyGroups {

    private ArrayList<String> ids;
    private String id;
    private ArrayList<String> level;
    private ArrayList<MyGroups> groups;
    private String s_level;
    private ArrayList<String> group_name;
    private String s_group_name;
    private ArrayList<String> num_members;
    private String s_num_members;

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
/*public ArrayList<String> getLevel() {

        return level;
    }
    public void setLevel(ArrayList<String> level){

        this.level=level;
    }
    public ArrayList<String> get_groupnames(){

        return group_name;
    }
    public void set_groupnames(ArrayList<String> group_name){

        this.group_name=group_name;
    }*/



    /*public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public ArrayList<String> getgroup_names(){
        return  group_name;
    }
    public void setgroup_names(ArrayList<String> group_name){
        this.group_name=group_name;
    }*/
    public ArrayList<MyGroups> getGroups() {

        return groups;
    }
    public void setGroups(ArrayList<MyGroups> groups){

        this.groups=groups;
    }
    public ArrayList<String> getLevel() {

        return level;
    }
    public void setLevel(ArrayList<String> level){

        this.level=level;
    }
    public ArrayList<String> get_groupnames(){

        return group_name;
    }
    public void set_groupnames(ArrayList<String> group_name){

        this.group_name=group_name;
    }

    public String s_getLevel() {

        return s_level;
    }

    public void s_setLevel(String s_level){

        this.s_level=s_level;
    }
    public String s_get_groupnames(){

        return  s_group_name;
    }
    public void s_set_groupnames(String s_group_name){

        this.s_group_name=s_group_name;
    }


    public ArrayList<String> getNumMembers() {

        return num_members;
    }
    public void setNumMembers(ArrayList<String> num_members){

        this.num_members=num_members;
    }

    public String s_getNumMembers() {

        return s_num_members;
    }

    public void s_setNumMembers(String s_num_members){

        this.s_num_members=s_num_members;
    }



        /*public String s_getLevel() {

        return s_level;
    }

    public void s_setLevel(String s_level){

        this.s_level=s_level;
    }
    public String s_get_groupnames(){

        return  s_group_name;
    }
    public void s_set_groupnames(String s_group_name){

        this.s_group_name=s_group_name;
    }*/

    /*public static ArrayList<GroupMembers> getData() {

        ArrayList<GroupMembers> dataList = new ArrayList<>();

        String level[]={};
        String groupNames[]={};
        GroupMembers members = new GroupMembers();
        for(int i=0;i<members.getLevel().size();i++){
            level[i]=members.getLevel().get(i);
            System.out.println(level[i]);
            Log.d("myTag", "This is my message");
        }
        for(int i=0;i<members.get_groupnames().size();i++){
            groupNames[i]=members.get_groupnames().get(i);
        }
        for (int i = 0; i < level.length; i++) {
            members.s_set_groupnames(groupNames[i]);
            members.s_setLevel(level[i]);
            dataList.add(members);
        }
        return dataList;
    }*/
}