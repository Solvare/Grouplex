package com.example.solvare.grouplex.custom;

import android.util.Log;

import com.example.solvare.grouplex.R;

import java.util.ArrayList;

public class GroupMembers {

    private ArrayList<String> level;
    private ArrayList<GroupMembers> groupmembers;
    private String s_level;
    private ArrayList<String> group_name;
    private String s_group_name;

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
    public ArrayList<GroupMembers> getMembers() {

        return groupmembers;
    }
    public void setMembers(ArrayList<GroupMembers> groupmembers){

        this.groupmembers=groupmembers;
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