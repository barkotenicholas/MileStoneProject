package com.example.gbv.models;

public class Reports {

    String name;
    String id;
    String date;
    String location;
    String desc;

    public Reports(String name, String id, String date, String location, String desc) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.location = location;
        this.desc = desc;
    }


    public Reports() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
