package com.example.a0103pr1519.data;

import java.io.Serializable;

public class Orders implements Serializable {
    private int id;
    private int idUser;
    private int idService;
    private String date;

    public Orders(int idUser, int idService, String date) {
        this.idUser = idUser;
        this.idService = idService;
        this.date = date;
    }

    public Orders(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
