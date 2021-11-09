package com.example.agenda.objetos;

import java.util.ArrayList;

public class User {

    // Atributos
    private int code;
    private String name;
    private String pass;
    private boolean remember;

    // Constructor
    public User(int code, String name, String pass, boolean remember) {
        this.code = code;
        this.name = name;
        this.pass = pass;
        this.remember = remember;
    }

    public User() {

    }

    // Métodos get y set
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

}
