package com.example.mobileappgit.Search;

public class Model {       // This is the Item Blueprint Class

    private  int imageIcon;
    String title;
    String body;

    public Model(int imageIcon, String title, String body) {
        this.imageIcon = imageIcon;
        this.title = title;
        this.body = body;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
