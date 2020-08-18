package com.example.mobileappgit.data.Item;

import com.example.mobileappgit.data.User.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private String mTitle;
    private String mCategory;
    private String mDescription;
    private String mUsername; // dont need to get from somewhere else
    private String mCondition;
    private String mPrice; // or string
    private String mTrade;
    private String mTradeFor;
    private String mDate; // TODO - change date to a integer

    public static final String TITLE    = "title"; // have to be the same as webstorm > "green ones"
    public static final String CATEGORY     = "category";
    public static final String DESCRIPTION  = "description";
    public static final String USERNAME     = "username";
    public static final String CONDITION     = "condition";
    public static final String PRICE        = "price";
    public static final String TRADE        = "trade";
    public static final String TRADEFOR     = "tradeFor";
    public static final String DATE    = "date"; // DONT NEED DATE


    // WHAT DOES THIS DO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // IT ALLOWS US TO CONVERT A JSON STRING INTO A LIST OF ITEM OBJECTS !!!!!!!!!!!!!!!!!!!!!!
    public static List<Item> parseItemJson(String itemJson) throws JSONException {
        List<Item> itemList = new ArrayList<>();


        if(itemJson != null){
            JSONArray arr = new JSONArray(itemJson);

            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Item item = new Item(obj.getString(Item.TITLE), obj.getString(Item.CATEGORY),
                        obj.getString(Item.DESCRIPTION), obj.getString(Item.USERNAME),
                        obj.getString(Item.CONDITION), obj.getString(Item.PRICE),
                        obj.getString(Item.TRADE), obj.getString(Item.TRADEFOR),
                        obj.getString(Item.DATE));

                itemList.add(item);
            }
        }

        return itemList;
    }


    public Item(String title, String category, String description, String username,
                String condition, String price, String trade, String tradeFor, String date) {
        mTitle = title;
        mCategory = category;
        mDescription = description;
        mUsername = username;
        mCategory = category;
        mPrice = price;
        mTrade = trade;
        mTradeFor = tradeFor;
        mDate = date;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUsername() {
        return mUsername;
    }

    public String getmCondition() {
        return mCondition;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmTrade() {
        return mTrade;
    }

    public String getmTradeFor() {
        return mTradeFor;
    }

    public String getmDate() {
        return mDate;
    }
    /*// Checks if the email is valid, if so it saves the email
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

        // If most functionality is done you can chose to force items to use capital letters, #'s, etc
        if (password == null
                || password.length() < 6) {
            throw new IllegalArgumentException("Password needs 6+ characters");
        }
        // Should there be an else ? !!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //else {
            this.mPassword = password;
        //}
    }*/

    // sets the firstname ... to mFirstname
    public Item(String title, /*String category,*/ String description, String username,
                String condition, String price, String trade, String tradeFor) {
        mTitle          = title;
        //mCategory     =
        mDescription    = description;
        mUsername       = User.USERNAME;
        mCondition      = condition;
        mPrice           = price;
        mTrade           = trade;
        mTradeFor        = tradeFor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getCondition() {
        return mCondition;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getTrade() {
        return mTrade;
    }

    public String getTradeFor() {
        return mTradeFor;
    }

    // if you need getters and setters right click > chose 'Generate ..' > ...
}




