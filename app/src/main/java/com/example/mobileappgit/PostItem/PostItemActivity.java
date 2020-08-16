package com.example.mobileappgit.PostItem;

/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobileappgit.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }
}*/

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
import com.example.mobileappgit.authenticate.LoginFragment;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.

 The login fragment has a listener which waits until the SIGN IN button has been pressed
 -   If the email address doesn't have a '@' symbol in it a toast message is sent to the
 screen when the 'SIGN IN' button is pressed
 -   If the password has less then 6 characters in it a toast message is sent to the
 screen when the 'SIGN IN' button is pressed
 */
public class PostItemActivity extends AppCompatActivity
        implements /*LoginFragment.LoginFragmentListener,*/
        PostItemFragment.AddListener {

    public static final String ADD_ITEM = "add_item"; // is referencing urls.xml // TODO - need to change ???
    public static final String GET_ITEM = "get_item"; // is referencing urls.xml
    private JSONObject mItemJSON;

    // login private boolean mLoginMode;
    private String mEmail;
    private boolean mRemember;

    // login private JSONObject mLoginJSON;
    //private JSONObject mItemJSON;

    public final static String SIGN_IN_FILE_PREFS = "edu.uw.tacoma.menakaapp.sign_in_file_prefs"; // TODO - Needs to change
    public final static String EMAIL = "email";
    public final static String REMEMBER = "remember";
    private SharedPreferences mSharedPreferences;


    /*    *//**
     * The login fragment's listener
     *//*
    private SignInActivityListener mSignInActivityListener;
    //private LoginFragmentListener mLoginFragmentListener;*/

    /**
     * Creates the page
     */
    // login
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //mSharedPreferences = getSharedPreferences(SIGN_IN_FILE_PREFS, Context.MODE_PRIVATE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sign_in_fragment_container, new LoginFragment())
                .commit();
    }*/


    /**
     * Required empty public constructor
     */
/*    public SignInActivity() {
        // Required empty public constructor
    }*/

    // login
    // (is to run the Register activity after login finishes)
    /*@Override
    public void launchPostItemFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sign_in_fragment_container, new PostItemFragment())
                .addToBackStack(null)
                .commit();
    }*/

    // login
    // Method / Function that is called when the "Sign In" button is pressed
    /*@Override
    public void login(String email, String pwd, boolean shouldRemember) { // email could later become either email or itemname
        mLoginMode = true;
        mEmail = email; // is there a reason for this? if so what about pwd
        mRemember = shouldRemember;

        StringBuilder loginUrl = new StringBuilder(getString(R.string.get_item)); // gets (url) string from urls.xml


        // TODO - Create the json object for passing with the login url
        // Execute Async Task with login url

        mLoginJSON = new JSONObject();

        try {
            // needs to be the same order as the table
            //mLoginJSON.put(Item.FIRSTNAME, "");   // Don't have/need this info
            //mLoginJSON.put(Item.LASTNAME, "");    // Don't have/need this info
            //mLoginJSON.put(Item.ITEMNAME, "");    // may have to check latter but currently don't need
            mLoginJSON.put(Item.EMAIL, mEmail*//*email*//*);          // put in email information
            mLoginJSON.put(Item.PASSWORD, pwd);         // check password information
            //mLoginJSON.put(Item.loginMode, mLoginMode); // dont have somewhere to store yet

            new AuthenticateAsyncTask().execute(loginUrl.toString());
        } catch (JSONException e) {
            Toast.makeText(this, "Error with JSON creation on adding a item: "
                            + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void addItem(Item item) {
        StringBuilder url = new StringBuilder(getString(R.string.add_item)); // url supposed to be url?

        // Construct a JSONObject to build a formatted message to send.
        mItemJSON = new JSONObject();
        try {
            // Must be in this order (because its the order of the table)
            mItemJSON.put(Item.TITLE, item.getTitle());
            //mItemJSON.put(Item.CATEGORY, item.getCategory()); // TODO - need to uncomment when category is created
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



    // TODO - Need to edit !!!
    // login
    /*private class AuthenticateAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = ""; // TODO
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {

                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();
                    urlConnection.setRequestMethod("POST");//urlConnection.setRequestMethod("GET"); // Switched to GET (from POST)
                    urlConnection.setRequestProperty("Content-Type", "application/json"); // TODO - ??
                    urlConnection.setDoOutput(true);
                    //urlConnection.setDoOutput(false); // Changed to false
                    OutputStreamWriter wr =
                            new OutputStreamWriter(urlConnection.getOutputStream());

                    // error happens here !!!!!!!!!!!!!!

                    // For Debugging
                    Log.i(GET_ITEM, mLoginJSON.toString()); // should I have mItemJSON
                    wr.write(mLoginJSON.toString());
                    wr.flush();
                    wr.close();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to authenticate login, Reason: " // fixed this string
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) { // error = "JSON Parsing error on Adding itemNo value for error"

            if (s.startsWith("Unable to authenticate login")) { // TODO - change message's
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getBoolean("success")) {
                    Toast.makeText(getApplicationContext(), "Item logged in successfully"
                            , Toast.LENGTH_SHORT).show();

                    // open MainActivity.java
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "Item couldn't be added: " // error even though all fields are filled in error still pops up
                            , Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on Adding item"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e(GET_ITEM, e.getMessage());
            }
        }
    }*/
}


    /**
     * Calls the login function
     *//*
    public interface SignInActivityListener {
        public void login(String email, String pwd);
    }*/


    /**
     The system calls this method to draw the Fragment UI for the first time
     */
    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getActivity().setTitle("Sign In");
        mSignInActivityListener = (SignInActivityListener) getActivity();


        final EditText emailText = view.findViewById(R.id.email_address_id);
        final EditText pwdText = view.findViewById(R.id.password_id);

        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            *//**
             -   If the email address doesn't have a '@' symbol in it a toast message is sent to the
             screen when the 'SIGN IN' button is pressed
             -   If the password has less then 6 characters in it a toast message is sent to the
             screen when the 'SIGN IN' button is pressed
             *//*
            @Override
            public void onClick(View v){
                String email = emailText.getText().toString();
                String pwd = pwdText.getText().toString();
                if(TextUtils.isEmpty(email) || !email.contains("@")) {
                    Toast.makeText(v.getContext(),"Enter valid email address",
                            Toast.LENGTH_SHORT)
                            .show();
                    emailText.requestFocus();
                }
                else if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
                    Toast.makeText(v.getContext(),"Enter a valid password( atleast 6 characters)"
                            , Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();


                }
                else{
                    mLoginFragmentListener.login(emailText.getText().toString(), pwdText.getText().toString());
                }}
        });

        return  view;
    }

}*/