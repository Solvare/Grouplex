package com.example.solvare.grouplex.custom;

/**
 * Created by rajat on 8/4/17.
 */

public class About {

    private String heading, content;

    public About() {
    }

    public About(String heading, String content) {
        this.heading = heading;
        this.content = content;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
