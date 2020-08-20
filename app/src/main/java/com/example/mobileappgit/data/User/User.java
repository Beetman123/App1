package com.example.mobileappgit.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String mUsername;
    private String mPassword;
    private String mEmail;
    private String mFirstname;
    private String mLastname;

    private boolean mRemember;

    public static final String FIRSTNAME    = "first";
    public static final String LASTNAME     = "last";
    public static final String EMAIL        = "email";
    public static final String USERNAME     = "username";
    public static final String PASSWORD     = "password";


    public static List<User> parseCourseJson(String courseJson) throws JSONException {
        List<User> userList = new ArrayList<>();


        if(courseJson != null){
            JSONArray arr = new JSONArray(courseJson);

            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                User user = new User(obj.getString(User.FIRSTNAME), obj.getString(User.LASTNAME),
                        obj.getString(User.EMAIL), obj.getString(User.USERNAME),
                        obj.getString(User.PASSWORD));

                userList.add(user);
            }
        }

        return userList;
    }


    // Checks if the email is valid, if so it saves the email
    public void setEmail(String email) {
        if (email == null
                || email.length() < 6
                || !email.contains("@")) {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.mEmail = email;
    }



    // Checks if the password is valid, if so it saves the password
    public void setPassword(String password) {
        if (password == null
                || password.length() < 6) {
            throw new IllegalArgumentException("Password needs 6+ characters");
        }
        this.mPassword = password;
    }

    // sets the firstname ... to mFirstname
    public User(String firstname, String lastname, String email, String username, String password) {
        mFirstname = firstname;
        mLastname = lastname;
        mEmail = email;
        mUsername = username;
        mPassword = password;
    }

    public String getmUsername(){
        return mUsername;
    }

    public String getmPassword(){
        return mPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public boolean isRemember() {
        return mRemember;
    }

    public void setRemember(boolean remember) {
        this.mRemember = remember;
    }
}




