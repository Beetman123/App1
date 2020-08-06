package com.example.mobileappgit.authenticate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String mUsername;
    private String mPassword;

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static List<User> parseCourseJson(String courseJson) throws JSONException {
        List<User> userList = new ArrayList<>();

        if(courseJson != null){
            JSONArray arr = new JSONArray(courseJson);

            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                User user = new User(obj.getString(User.USERNAME), obj.getString(User.PASSWORD)
                        );
                userList.add(user);
            }
        }

        return userList;
    }

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public String getmUsername(){
        return mUsername;
    }

    public String getmPassword(){
        return mPassword;
    }

}




