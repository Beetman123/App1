package com.example.mobileappgit.data.Item;

import com.example.mobileappgit.R;
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
    private String mDate;

    public static final String TITLE    = "title"; // have to be the same as webstorm > "green ones"
    public static final String CATEGORY     = "category";
    public static final String DESCRIPTION  = "description";
    public static final String USERNAME     = "username";
    public static final String CONDITION     = "condition";
    public static final String PRICE        = "price";
    public static final String TRADE        = "trade";
    public static final String TRADEFOR     = "tradefor";
    public static final String DATE     = "inputdate";


    // It allows us to convert a json string into a list of item objects
    public static List<Item> parseItemJson(String itemJson) throws JSONException {
        List<Item> itemList = new ArrayList<>();


        if(itemJson != null){
            JSONArray arr = new JSONArray(itemJson);

            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Item item = new Item(obj.getString(Item.TITLE), obj.getString(Item.CATEGORY),
                        obj.getString(Item.DESCRIPTION), obj.getString(Item.USERNAME),
                        obj.getString(Item.CONDITION), obj.getString(Item.PRICE),
                        obj.getString(Item.TRADE), obj.getString(Item.TRADEFOR), obj.getString(Item.DATE));

                itemList.add(item);
            }
        }

        return itemList;
    }

    // sets the firstname ... to mFirstname
    public Item(String title, String category, String description, String username,
                String condition, String price, String trade, String tradeFor, String mDate) {

        String exception;
        switch ()
        {
            case title == null:
                exception = "Error: title is empty";
                break;
            case category == null:
                exception = "Error: category is empty";
                break;
            case description == null:
                exception = "Error: description is empty";
                break;
            case username == null:
                exception = "Error: username is empty";
                break;
            case condition == null:
                exception = "Error: condition is empty";
                break;
            case price == null:
                exception = "Error: price is empty";
                break;
            case trade == null:
                exception = "Error: trade is empty";
                break;
            case tradeFor == null:
                exception = "Error: tradeFor is empty";
                break;
            case mDate == null:
                exception = "Error: mDate is empty";
                break;
        }
        if (exception != null)
        {
            throw ...
            "Error: " + exception
        }


        mTitle          = title;
        mCategory     = category;
        mDescription    = description;
        mUsername       = User.USERNAME;
        mCondition      = condition;
        mPrice           = price;
        mTradeFor        = tradeFor;

        if (mTrade != "n") {
            mTradeFor = trade;
        }
        else
        {
            mTradeFor = "NULL";
        }



    }

    public String getDate() {return mDate;}

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