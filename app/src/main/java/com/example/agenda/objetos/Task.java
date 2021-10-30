package com.example.agenda.objetos;

public class Task {
    private int code;
    private String name;
    private String description;
    private String date;
    private String cost;
    private String Priority;
    //Este podria ser un enum
    private boolean done;

    Task(){}

    public Task(int cide, String name, String description, String date, String cost, String priority, boolean done) {
        this.code=code;
        this.name = name;
        this.description = description;
        this.date = date;
        this.cost = cost;
        Priority = priority;
        this.done = done;
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

    public String getPriority() {
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

    public void setPriority(String priority) {
        Priority = priority;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}