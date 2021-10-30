package com.example.agenda.objetos;

import java.util.ArrayList;

public class User {
    private int code;
    private String name;
    private String pass;
    private boolean rememberMe;
    private ArrayList<Task> tasks = new ArrayList();

    public User(int code, String name, String pass, boolean rememberMe) {
        this.code=code;
        this.name = name;
        this.pass = pass;
        this.rememberMe = rememberMe;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public ArrayList<Task> shareTasks(){
        return this.tasks;

    }
    public void addTask(Task task){
        tasks.add(task);
    }

    public void eraseAllTasks(){
        tasks.clear();
    }

    public void eraseOneTask(Task task){
      for(int i = 0 ; i<this.tasks.size(); i++){
          if ((this.tasks.get(i).getCode()) == task.getCode()){
              this.tasks.remove(i);
          }
      }
    }

    public void orderTasks(){

    }
}
