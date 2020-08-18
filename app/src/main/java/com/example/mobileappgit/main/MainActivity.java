package com.example.mobileappgit.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.mobileappgit.CommunicateFragment;
import com.example.mobileappgit.PostItem.PostItemFragment;
import com.example.mobileappgit.Profile.ProfileFragment;
import com.example.mobileappgit.R;
import com.example.mobileappgit.Search.SearchFragment;
import com.example.mobileappgit.authenticate.SignInActivity;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.data.Item.ItemDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements PostItemFragment.AddListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener); // 11:30

        // TODO - set starting fragment page
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            SharedPreferences sharedPreferences =
                    getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false)
                    .commit();

            Intent i = new Intent(this, SignInActivity.class); // Replaced oldSignInActivity.class with SignInActivity.class
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
/*                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();*/
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
/*                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();*/
                            break;
                        case R.id.nav_plus:
                            selectedFragment = new PostItemFragment();
/*                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();*/
                            break;

                        case R.id.nav_communicate:
                            selectedFragment = new CommunicateFragment();
/*                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();*/
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
/*                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();*/
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    // ---------------------------------------------------------------------------------------------
    // -------------------------- EVERYTHING BELOW IS FROM PostItemActivity.java -------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------


    /**
     * A simple {@link Fragment} subclass.

     The login fragment has a listener which waits until the SIGN IN button has been pressed
     -   If the email address doesn't have a '@' symbol in it a toast message is sent to the
     screen when the 'SIGN IN' button is pressed
     -   If the password has less then 6 characters in it a toast message is sent to the
     screen when the 'SIGN IN' button is pressed
     */

/*    public class PostItemActivity extends AppCompatActivity
            implements *//*LoginFragment.LoginFragmentListener,*//*
            PostItemFragment.AddListener {*/

        public static final String ADD_ITEM = "add_item"; // is referencing urls.xml // TODO - need to change ???
        public static final String GET_ITEM = "get_item"; // is referencing urls.xml
        private JSONObject mItemJSON;

        // login private boolean mLoginMode;
        //private String mEmail;
        //private boolean mRemember;

        // login private JSONObject mLoginJSON;
        //private JSONObject mItemJSON;

        // TODO - DONT NEED
        public final static String SIGN_IN_FILE_PREFS = "edu.uw.tacoma.menakaapp.sign_in_file_prefs"; // TODO - Needs to change
        public final static String EMAIL = "email";
        public final static String REMEMBER = "remember";
        private SharedPreferences mSharedPreferences;


        @Override
        public void addItem(Item item) {
            StringBuilder url = new StringBuilder(getString(R.string.add_item)); // url supposed to be url?

            // Construct a JSONObject to build a formatted message to send.
            mItemJSON = new JSONObject();
            try {
                // Must be in this order (because its the order of the table)
                mItemJSON.put(Item.TITLE, item.getTitle());
                mItemJSON.put("category", "sports" /*Item.CATEGORY, item.getCategory()*/); // TODO - need to uncomment when category is created
                mItemJSON.put(Item.DESCRIPTION, item.getDescription());
                mItemJSON.put(Item.USERNAME, item.getUsername());
                mItemJSON.put(Item.CONDITION, item.getCondition());
                mItemJSON.put(Item.PRICE, item.getPrice());
                mItemJSON.put(Item.TRADE, item.getTrade());
                mItemJSON.put(Item.TRADEFOR, item.getTradeFor());

                new AddItemAsyncTask().execute(url.toString());
            } catch (JSONException e) { // is there a reason its 'e'
                Toast.makeText(this, "Error with JSON creation on adding a item: "
                                + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }


        // Stuff that connects to the back when Register? is clicked
        private class AddItemAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... urls) {
                String response = "";
                HttpURLConnection urlConnection = null;
                for (String url : urls) {
                    try {
                        URL urlObject = new URL(url);
                        urlConnection = (HttpURLConnection) urlObject.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setRequestProperty("Content-Type", "application/json");
                        urlConnection.setDoOutput(true);
                        OutputStreamWriter wr =
                                new OutputStreamWriter(urlConnection.getOutputStream());

                        // For Debugging
                        Log.i(ADD_ITEM, mItemJSON.toString());
                        wr.write(mItemJSON.toString());
                        wr.flush();
                        wr.close();

                        InputStream content = urlConnection.getInputStream();

                        BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                        String s = "";
                        while ((s = buffer.readLine()) != null) {
                            response += s;
                        }

                    } catch (Exception e) {
                        response = "Unable to add the new item, Reason: "
                                + e.getMessage();
                    } finally {
                        if (urlConnection != null)
                            urlConnection.disconnect();
                    }
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s.startsWith("Unable to add the new item")) {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getBoolean("success")) {
                        Toast.makeText(getApplicationContext(), "Item Added successfully"
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Item couldn't be added: " // error even though all fields are filled in error still pops up
                                        + jsonObject.getString("error")
                                , Toast.LENGTH_LONG).show();
                        Log.e(ADD_ITEM, jsonObject.getString("error"));
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "JSON Parsing error on Adding item"
                                    + e.getMessage()
                            , Toast.LENGTH_LONG).show();
                    Log.e(ADD_ITEM, e.getMessage());
                }
            }
        }


    // ---------------------------------------------------------------------------------------------
    // -------------------------- EVERYTHING BELOW IS FOR SearchFragment.java ----------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------


    /*public static final String ADD_ITEM = "add_item"; // is referencing urls.xml // TODO - need to change ???
    public static final String GET_ITEM = "get_item"; // is referencing urls.xml
    private JSONObject mItemJSON;*/

    // login private boolean mLoginMode;
    //private String mEmail;
    //private boolean mRemember;

    // login private JSONObject mLoginJSON;
    //private JSONObject mItemJSON;

    // TODO - DONT NEED
/*
    public final static String SIGN_IN_FILE_PREFS = "edu.uw.tacoma.menakaapp.sign_in_file_prefs"; // TODO - Needs to change
    public final static String EMAIL = "email";
    public final static String REMEMBER = "remember";
    private SharedPreferences mSharedPreferences;
*/
    // COMMENTED OUT @Override because it was causing an error !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //@Override // WHAT NOW
    public void getItems(Item item) {
        StringBuilder url = new StringBuilder(getString(R.string.get_item)); // url supposed to be url?

        // Construct a JSONObject to build a formatted message to send.
        mItemJSON = new JSONObject();
        try {
            // Must be in this order (because its the order of the table)
            mItemJSON.put(Item.TITLE, item.getTitle());
            mItemJSON.put("category", "sports" /*Item.CATEGORY, item.getCategory()*/); // TODO - need to uncomment when category is created
            mItemJSON.put(Item.DESCRIPTION, item.getDescription());
            mItemJSON.put(Item.USERNAME, item.getUsername());
            mItemJSON.put(Item.CONDITION, item.getCondition());
            mItemJSON.put(Item.PRICE, item.getPrice());
            mItemJSON.put(Item.TRADE, item.getTrade());
            mItemJSON.put(Item.TRADEFOR, item.getTradeFor());

            new AddItemAsyncTask().execute(url.toString());
        } catch (JSONException e) { // is there a reason its 'e'
            Toast.makeText(this, "Error with JSON creation on adding a item: "
                            + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    // Stuff that connects to the back when Register? is clicked
    private class GetItemAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setDoOutput(true);
                    OutputStreamWriter wr =
                            new OutputStreamWriter(urlConnection.getOutputStream());

                    // For Debugging
                    Log.i(ADD_ITEM, mItemJSON.toString());
                    wr.write(mItemJSON.toString());
                    wr.flush();
                    wr.close();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to add the new item, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.startsWith("Unable to add the new item")) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getBoolean("success")) {
                    Toast.makeText(getApplicationContext(), "Item Added successfully"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Item couldn't be added: " // error even though all fields are filled in error still pops up
                                    + jsonObject.getString("error")
                            , Toast.LENGTH_LONG).show();
                    Log.e(ADD_ITEM, jsonObject.getString("error"));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on Adding item"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e(ADD_ITEM, e.getMessage());
            }
        }
    }


    //`1234567890

    private ItemDB mItemDB;

    private List<Item> mItemList;

    private class CoursesTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to download the list of items, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;

        }

        @Override
        protected void onPostExecute(String s) {
            if (s.startsWith("Unable to")) {
                Toast.makeText(getApplicationContext(), "Unable to download" + s, Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);

                if (jsonObject.getBoolean("success")) {
                    mItemList = Item.parseItemJson(
                            jsonObject.getString("courses")); // Test. was names. This change eleminates the popup errors, but still nothing shows up. Great turmoil wells up within me, my darling...   (just for your info that's somewhat from The Clone Wars... yep)
                    if(mItemDB == null){
                        mItemDB = new ItemDB(getApplicationContext());
                    }

                    //Delete old data so that you can refresh the local
                    //database with the network data.
                    mItemDB.deleteItems();

                    //Also, add to the local database
                    for(int i=0; i<mItemList.size(); i++){
                        Item item = mItemList.get(i);
                        mItemDB.insertItem(item.getTitle(), item.getCategory(), item.getDescription(), item.getUsername(),
                                item.getCondition(), item.getPrice(), item.getTrade(), item.getTradeFor());
                    }
                    /*if (!mItemList.isEmpty()) {
                        setupRecyclerView((RecyclerView) mRecyclerView);
                    }*/
                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Error: " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }


        //these last 2 were cut off or should be above :)
    }

    //`1234567890



}


// ---------------------------------------------------------------------------------------------
// -------------------------- EVERYTHING BELOW IS FOR SearchFragment.java -------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------



/*public static List<Item> parseItemJson (String itemJson) throws JSONException {
    List<Item> itemList = new ArrayList<>();
    if(itemJson != null){
        JSONArray arr = new JSONArray(itemJson);

        for (int i = 0; i < arr.length(); i++) {
            JSONArray obj = arr.getJSONObject(i);
            Item item = new Item(obj.getString(Item. ...) // TODO - need to finish: data > Item.js
            );
        }
    }
}*/

































