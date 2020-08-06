package com.example.mobileappgit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.mobileappgit.R;
import com.example.mobileappgit.authenticate.User;


public class UserDB {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Member.db";

    private UserDBHelper mUserDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public UserDB(Context context) {
        mUserDBHelper = new UserDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mUserDBHelper.getWritableDatabase();

    }
    //CLASS IS NOT COMPLETE

    /**
     * Inserts the course into the local sqlite table. Returns true if successful, false otherwise.
     * @param username
     * @param password
     * @param firstName
     * @return true or false
     */
    public boolean insertUser(String firstName, String lastName, String username, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long rowId = mSQLiteDatabase.insert("User", null, contentValues);
        return rowId != -1;
    }

    /**
     * Delete all the data from the Courses
     */
    public void deleteCourses() {
        mSQLiteDatabase.delete("User", null, null);
    }

    	/**
     * Returns the list of courses from the local Course table.
     * @return list
     */
    public List<User> getCourses() {

        String[] columns = {
                "firstName", "lastName", "username", "email", "password"
        };

        Cursor c = mSQLiteDatabase.query(
                "User",  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<User> list = new ArrayList<User>();
        for (int i=0; i<c.getCount(); i++) {
            String firstName = c.getString(0);
            String lastName = c.getString(1);
            String email = c.getString(2);
            String username = c.getString(3);
            String password = c.getString(4);
            User user = new User(firstName, lastName, email, username, password);
            list.add(user);
            c.moveToNext();
        }

        return list;
    }

    class UserDBHelper extends SQLiteOpenHelper {

        private final String CREATE_USER_SQL;

        private final String DROP_USER_SQL;

        public UserDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_USER_SQL = context.getString(R.string.CREATE_USER_SQL);
            DROP_USER_SQL = context.getString(R.string.DROP_USER_SQL);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_USER_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_USER_SQL);
            onCreate(sqLiteDatabase);
        }
    }

}
