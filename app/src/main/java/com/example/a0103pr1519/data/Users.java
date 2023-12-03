package com.example.a0103pr1519.data;

import java.io.Serializable;

public class Users implements Serializable {
    private int id;
    private String login;
    private String password;
    private String secondName;
    private String firstName;
    private String surName;
    private String dateBirth;

    public Users(String login, String password, String secondName, String firstName, String surName, String dateBirth) {
        this.login = login;
        this.password = password;
        this.secondName = secondName;
        this.firstName = firstName;
        this.surName = surName;
        this.dateBirth = dateBirth;
    }

    public Users(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }
}
