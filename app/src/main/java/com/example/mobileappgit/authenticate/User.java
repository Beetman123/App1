package com.example.mobileappgit.authenticate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mUsername;
    private String mPassword;


    public static final String FIRSTNAME = "firstName";
    public static final String LASTNATME = "lastName";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public static String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public static String getLASTNATME() {
        return LASTNATME;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static List<User> parseCourseJson(String courseJson) throws JSONException {
        List<User> userList = new ArrayList<>();

        if(courseJson != null){
            JSONArray arr = new JSONArray(courseJson);

            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                User user = new User(obj.getString(User.FIRSTNAME),obj.getString(User.LASTNATME),
                        obj.getString(User.USERNAME), obj.getString(User.EMAIL), obj.getString(User.PASSWORD)
                        );
                userList.add(user);
            }
        }

        return userList;
    }

    public User(String firstName, String lastName, String email, String username, String password) {
       mFirstName = firstName;
       mLastName = lastName;
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

}




