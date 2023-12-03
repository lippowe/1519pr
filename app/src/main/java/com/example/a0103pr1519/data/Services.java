package com.example.a0103pr1519.data;

import java.io.Serializable;

public class Services implements Serializable {
    private int id;
    private String name;
    private int cost;
    private String description;

    public Services(String name, int cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }
    public Services(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
