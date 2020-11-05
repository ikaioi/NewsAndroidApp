package com.example.news.model;


import com.google.gson.annotations.Expose;

public class NewsSource {

    @Expose
    private String id;

    @Expose
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
