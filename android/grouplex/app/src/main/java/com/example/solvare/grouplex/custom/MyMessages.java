package com.example.solvare.grouplex.custom;

import java.util.ArrayList;

/**
 * Created by rishabh on 11/8/17.
 */

public class MyMessages {
    private ArrayList<MyMessages> messages;
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public ArrayList<MyMessages> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MyMessages> messages) {
        this.messages = messages;
    }
}
