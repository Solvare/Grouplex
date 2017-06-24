package com.example.solvare.grouplex.custom;

import java.util.ArrayList;

public class JoinGroup {

    private ArrayList<MyGroups> groups;

    private ArrayList<String> ids;
    private ArrayList<String> names;
    private ArrayList<String> members;

    private String id;
    private String name;
    private String member;

    public ArrayList<MyGroups> getGroups() {

        return groups;
    }

    public void setGroups(ArrayList<MyGroups> groups){

        this.groups=groups;
    }

    public ArrayList<String> getIds() {

        return ids;
    }

    public void setIds(ArrayList<String> ids){

        this.ids=ids;
    }

    public ArrayList<String> getNames(){

        return names;
    }

    public void setNames(ArrayList<String> names){

        this.names=names;
    }

    public ArrayList<String> getMembers() {

        return members;
    }

    public void setMembers(ArrayList<String> members){

        this.members=members;
    }

    public String getId() {

        return id;
    }

    public void setId(String id){

        this.id=id;
    }

    public String getName(){

        return  name;
    }
    public void setName(String name){

        this.name=name;
    }

    public String getMember() {

        return member;
    }

    public void setMember(String member){

        this.member=member;
    }

}