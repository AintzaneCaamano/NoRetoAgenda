package com.example.agenda.objetos;

public class Task {
    private int code;
    private String name;
    private String description;
    private String date;
    private String cost;
    private int Priority;
    //Este podria ser un enum
    private boolean done;
    private int userCode;

    public Task(){}

   /* public Task(int code, String name, String description, String date, String cost, int priority, boolean done) {
        this.code=code;
        this.name = name;
        this.description = description;
        this.date = date;
        this.cost = cost;
        Priority = priority;
        this.done = done;
    }*/
    public Task(int code, String name, String description, String date, String cost, int priority, boolean done, int user) {
        this.code=code;
        this.name = name;
        this.description = description;
        this.date = date;
        this.cost = cost;
        this.Priority = priority;
        this.done = done;
        this.userCode=user;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getCost() {
        return cost;
    }

    public int getPriority() {
        return Priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
