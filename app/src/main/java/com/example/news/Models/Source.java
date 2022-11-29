package com.example.news.Models;

import java.io.Serializable;

public class Source implements Serializable {

   // These object name should be define same as inside the api..
    String id = "";
    String name = "";

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
