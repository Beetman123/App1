package com.example.mobileappgit.data.Item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mobileappgit.R;

import java.util.ArrayList;
import java.util.List;


public class ItemDB {

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "Item.db";      // What is this?

    private ItemDBHelper mItemDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    class ItemDBHelper extends SQLiteOpenHelper {

        private final String CREATE_ITEM_SQL;

        private final String DROP_ITEM_SQL;

        public ItemDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_ITEM_SQL = context.getString(R.string.CREATE_ITEM_SQL); //R.string.CREATE_User_SQL // TODO - this
            DROP_ITEM_SQL = context.getString(R.string.DROP_ITEM_SQL); //R.string.DROP_User_SQL   // TODO - and this
            // TODO - ask what this does - is in res > values > sql_strings.xml
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_ITEM_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_ITEM_SQL);
            onCreate(sqLiteDatabase);
        }
    }


    public ItemDB(Context context) {
        mItemDBHelper = new ItemDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mItemDBHelper.getWritableDatabase();

    }

    //CLASS IS NOT COMPLETE

  /*  *//**
     * Inserts the course into the local sqlite table. Returns true if successful, false otherwise.
     * @param title
     * @param category
     * @param description
     * @param username
     * @param condition
     * @param price
     * @param trade
     * @param tradeFor
     * @
     * @return true or false
     */


    public boolean insertItem(String title, String category, String description, String username,
                              String condition, String price, String trade, String tradeFor, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("category", category);
        contentValues.put("description", description);
        contentValues.put("username", username);
        contentValues.put("condition", condition);
        contentValues.put("price", price);
        contentValues.put("trade", trade);
        contentValues.put("tradeFor", tradeFor);
        contentValues.put("date", date);

        long rowId = mSQLiteDatabase.insert("Items", null, contentValues);
        return rowId != -1;
    }

    /**
     * Delete all the data from the Items
     */
    public void deleteItems() {
        mSQLiteDatabase.delete("Items", null, null);
    }



    	/**
     * Returns the list of courses from the local Course table.
     * @return list
     */
    public List<Item> getItems() {

        String[] columns = {
                "title", "category", "description", "username", "condition", "price",
                "trade", "tradeFor", "date"
        };

        Cursor c = mSQLiteDatabase.query(
                "Items",  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<Item> list = new ArrayList<Item>();
        for (int i=0; i<c.getCount(); i++) {
/*            String firstname = c.getString(0);
            String lastname = c.getString(1);
            String username = c.getString(2);
            String email = c.getString(3);
            String password = c.getString(3);
            Item Item = new Item(firstname, lastname, username, email, password);
            list.add(Item);
            c.moveToNext();*/


            String title = c.getString(0);
            String category = c.getString(1);
            String description = c.getString(2);
            String username = c.getString(3);
            String condition = c.getString(4);
            String price = c.getString(5);
            String trade = c.getString(6);
            String tradeFor = c.getString(7);
            String date = c.getString(8);

            Item item = new Item(title, category, description, username, condition, price, trade, // TODO - add category
                    tradeFor, date);
            list.add(item);
            c.moveToNext();
        }

        return list;
    }



}
