package com.example.shdavido.tripplanner.Model;


import android.net.Uri;

import org.json.simple.JSONObject;

import java.net.URI;

public class User {

    private String name = null;
    private Integer age = null;
    private Uri photoUrl = null;
    private String gmail = null;

    public User () {

    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setGmail(String mailAdress) {
        this.gmail = mailAdress;
    }

    public void setUri(Uri uri) {
        this.photoUrl = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public JSONObject parseUser() {
        JSONObject userJSON = new JSONObject();

        userJSON.put("Name", this.name);
        userJSON.put("Age", this.age);

        return userJSON;
    }
}
