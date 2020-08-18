package com.example.mobileappgit.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mobileappgit.CommunicateFragment;
import com.example.mobileappgit.PostItem.PostItemFragment;
import com.example.mobileappgit.Profile.ProfileFragment;
import com.example.mobileappgit.R;
import com.example.mobileappgit.Search.Model;
import com.example.mobileappgit.Search.SearchFragment;
import com.example.mobileappgit.authenticate.LoginFragment;
import com.example.mobileappgit.authenticate.SignInActivity;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.data.Item.ItemDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements PostItemFragment.AddListener {


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

   /* @Override
    public void addItem(Item item) {

    }
}*/

    // ---------------------------------------------------------------------------------------------
    // -------------------------- EVERYTHING BELOW IS FROM PostItemActivity.java -------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------


    /**
     * /*  * A simple {@link Fragment} subclass.
     * <p>
     * The login fragment has a listener which waits until the SIGN IN button has been pressed
     * -   If the email address doesn't have a '@' symbol in it a toast message is sent to the
     * screen when the 'SIGN IN' button is pressed
     * -   If the password has less then 6 characters in it a toast message is sent to the
     * screen when the 'SIGN IN' button is pressed
     */

/*        public class PostItemActivity extends AppCompatActivity
            implements LoginFragment.LoginFragmentListener,
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
    /*public final static String SIGN_IN_FILE_PREFS = "edu.uw.tacoma.menakaapp.sign_in_file_prefs"; // TODO - Needs to change
    public final static String EMAIL = "email";
    public final static String REMEMBER = "remember";
    private SharedPreferences mSharedPreferences;*/


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
}
/*

// ---------------------------------------------------------------------------------------------
// -------------------------- EVERYTHING BELOW IS FOR SearchFragment.java -------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------

// may not need, already in Item.java

*//*public static List<Item> parseItemJson (String itemJson) throws JSONException {
    List<Item> itemList = new ArrayList<>();
    if(itemJson != null){
        JSONArray arr = new JSONArray(itemJson);

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Item item = new Item(obj.getString(Item.) // TODO - need to finish: data > Item.js
            );
        }
    }
}*//*

// ---------------------------------------------------------------------------------------------
// -------------------------- EVERYTHING BELOW IS FOR ItemListActivity.java -------------------
// --------------------------------- Items version of CourseListActivity  -------------------------
// ---------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------

    // PRESTONS STUFF

   *//* private List<Item> mItemList;
    private RecyclerView mRecyclerView;


    private class GetItemsAsyncTask extends AsyncTask<String, Void, String> {

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
                    response = "Unable to download the list of courses, Reason: "
                            + e.getMessage();
                }
                finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }
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
                        jsonObject.getString("Items")); // Test. was names. This change eleminates the popup errors, but still nothing shows up. Great turmoil wells up within me, my darling...   (just for your info that's somewhat from The Clone Wars... yep)
                *//**//*if(mItemDB == null){
                    mItemDB = new ItemDB(getApplicationContext());           mItemDB*******
                }*//**//*

                //Delete old data so that you can refresh the local
                //database with the network data.
                //mItemDB.deleteItems();

                //Also, add to the local database
                *//**//*for(int i=0; i<mItemList.size(); i++){        mItemDB************
                    Item course = mItemList.get(i);
                    mItemDB.insertItem(Item.get,
                            course.getItemShortDesc(),
                            course.getItemLongDesc(),
                            course.getItemPrereqs());
                }*//**//*
                if (!mItemList.isEmpty()) {
                    setupRecyclerView((RecyclerView) mRecyclerView);
                }
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "JSON Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }*//*


// JAEDAN'S STUFF



   *//* *//**//**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     *//*
    private boolean mTwoPane; // do we need this???

    private ItemDB mItemDB; // DO we need to create the ItemDB class ?!

    private List<Item> mItemList;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);//        setContentView(R.layout.activity_Item_list);

        //Not using for now
*//*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());*//*

        // TODO - will use this when chosing what happens when you click a item in the recycler view
*//*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }*//*


        mRecyclerView = findViewById(R.id.recyclerView); // item_list. this is recyclerView id from fragment_search
        assert mRecyclerView != null;
        setupRecyclerView((RecyclerView) mRecyclerView);
    }

    @Override
    protected  void onResume() {
        super.onResume();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // USES another permisson !!!
        if(networkInfo != null && networkInfo.isConnected()) {
            if(mItemList == null) {
                new GetItemsAsyncTask().execute(getString(R.string.get_item));
            }
        }
        else{
            Toast.makeText(this,
                    "No network connection available. Displaying locally stored data",
                    Toast.LENGTH_SHORT).show();

            if(mItemDB == null) {
                mItemDB = new ItemDB(this);
            }
            if(mItemList == null){
                mItemList = mItemDB.getItems();
                setupRecyclerView(mRecyclerView);
            }
        }
    }
        *//*new GetItemsAsyncTask().execute(getString(R.string.get_courses));
        setupRecyclerView(mRecyclerView);
    }*//*

    private void setupRecyclerView(@NonNull RecyclerView recyclerView){
        if (mItemList != null) {
            mRecyclerView.setAdapter(new SimpleItemRecyclerViewAdapter
                    (this, mItemList, mTwoPane)); // TODO - NEEDS ItemListActivity  ??!!
        }
    }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        private List<Model> itemList;

        // TODO - will use this when chosing what happens when you click a item in the recycler view
      *//*  private final ItemListActivity mParentActivity;
        private final List<Item> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = (Item) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable(ItemDetailFragment.ARG_ITEM_ID, item);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);

                    context.startActivity(intent);
                }
            }
        };

        // line :)
        // line :)

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<Item> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }*//*

        public SimpleItemRecyclerViewAdapter(List<Model> modelList) {
            this.itemList = modelList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.rowitem, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewholder, int position) {

            // TODO - (maybe) will use this when chosing what happens when you click a item in the recycler view
            *//*holder.mIdView.setText(mValues.get(position).getItemId());
            holder.mContentView.setText(mValues.get(position).getItemShortDesc());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);*//*

            int resource = itemList.get(position).getImageIcon();            // Model Items ***
            String title = itemList.get(position).getTitle();
            String body = itemList.get(position).getBody();
            viewholder.setData(resource, title, body);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;         // Layout Items ***
            private TextView title;
            private TextView body;

            public Viewholder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
                title = itemView.findViewById(R.id.textTitle);
                body = itemView.findViewById(R.id.textBody);
            }

            private void setData(int imageResource, String titleText, String bodyText) {

                imageView.setImageResource(imageResource);        // Layout Items
                title.setText(titleText);
                body.setText(bodyText);
            }
        }
    }

    private class GetItemsAsyncTask extends AsyncTask<String, Void, String> {
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
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;

        }
    }


        //these last 2 were cut off or should be above :)
    }


*/


























