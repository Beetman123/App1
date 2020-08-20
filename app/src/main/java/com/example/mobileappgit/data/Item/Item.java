package com.example.mobileappgit.data.Item;

import com.example.mobileappgit.data.User.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Item class is a class that represents an Item that is created by the user to be sold / traded
 * it holds all the variables of the item and all its functions
 */

public class Item implements Serializable {

    private String mTitle;
    private String mCategory;
    private String mDescription;
    private String mUsername;
    private String mCondition;
    private String mPrice;
    private String mTrade;
    private String mTradeFor;
    private String mDate;

    public static final String TITLE    = "title";
    public static final String CATEGORY     = "category";
    public static final String DESCRIPTION  = "description";
    public static final String USERNAME     = "username";
    public static final String CONDITION     = "condition";
    public static final String PRICE        = "price";
    public static final String TRADE        = "trade";
    public static final String TRADEFOR     = "tradefor";
    public static final String DATE     = "inputdate";



    // This allows us to convert a JSON string into a list of item objects
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
                String condition, String price, String trade, String tradeFor, String date) {
        mTitle          = title;
        mCategory     = category;
        mDescription    = description;
        mUsername       = username; //User.USERNAME
        mCondition      = condition;
        mPrice           = price;
        mTrade           = trade;
        mTradeFor        = tradeFor;
        mDate            = date;

        if (title == null) {
            throw new NullPointerException("title is null");
        }
        if (category == null) {
            throw new NullPointerException("category is null");
        }
        if (description == null) {
            throw new NullPointerException("description is null");
        }
        if (username == null) {
            throw new NullPointerException("username is null");
        }
        if (condition == null) {
            throw new NullPointerException("condition is null");
        }
        if (price == null) {
            throw new NullPointerException("price is null");
        }
        if (trade == null) { //always y or n
            throw new NullPointerException("trade is null");
        }

        if (tradeFor == null && trade == "y")           // Could be a problem, as this is not inforced
        {
            throw new NullPointerException("trade yes, but tradeFor is null");
        }
        // tradeFor can be null
        if (date == null) {
            throw new NullPointerException("mDate is null");
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
}