package com.example.mobileappgit.authenticate;

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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.User.User;
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
public class SignInActivity extends AppCompatActivity
        implements LoginFragment.LoginFragmentListener,
        RegisterFragment.AddListener {

    public static final String ADD_USER = "add_user"; // is referencing urls.xml
    public static final String GET_USER = "get_user"; // is referencing urls.xml
    private JSONObject mUserJSON;

    private boolean mLoginMode;
    private String mEmail;
    private boolean mRemember;

    private JSONObject mLoginJSON;

    public final static String SIGN_IN_FILE_PREFS = "com.example.mobileappget.sign_in_file_prefs";
    public final static String EMAIL = "email";
    public final static String REMEMBER = "remember";
    private SharedPreferences mSharedPreferences;


    /**
     * The login fragment's listener
     */

    /**
     * Creates the page
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences(SIGN_IN_FILE_PREFS, Context.MODE_PRIVATE);
        boolean ifLoggedIn = mSharedPreferences.getBoolean(REMEMBER, false);

        // if wants to be logged in automatically
        if (ifLoggedIn)
        {
            setContentView(R.layout.activity_main);

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        // if not logged in before or doesn't want to be logged in automatically
        else {
            setContentView(R.layout.activity_signin);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.sign_in_fragment_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void launchRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sign_in_fragment_container, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    // Method / Function that is called when the "Sign In" button is pressed
    @Override
    public void login(String email, String pwd, boolean shouldRemember) {
        mLoginMode = true;
        mEmail = email;
        mRemember = shouldRemember;

        // gets (url) string from urls.xml
        StringBuilder loginUrl = new StringBuilder(getString(R.string.get_user));

        mLoginJSON = new JSONObject();

        try {
            mLoginJSON.put(User.EMAIL, mEmail/*email*/);    // put in email information
            mLoginJSON.put(User.PASSWORD, pwd);             // check password information

            new AuthenticateAsyncTask().execute(loginUrl.toString());
        } catch (JSONException e) {
            Toast.makeText(this, "Error with JSON creation on logging in user: "
                            + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        // Save SharedPreferences (if told to)
        if(shouldRemember)
        {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(REMEMBER, shouldRemember);
            editor.putString(EMAIL, email);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Login preferences saved for " + email,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addUser(User user) {
        StringBuilder url = new StringBuilder(getString(R.string.add_user));

        // Construct a JSONObject to build a formatted message to send.
        mUserJSON = new JSONObject();
        try {
            // Must be in this order (because its the order of the table)
            mUserJSON.put(User.FIRSTNAME, user.getFirstname());
            mUserJSON.put(User.LASTNAME, user.getLastname());
            mUserJSON.put(User.USERNAME, user.getmUsername());
            mUserJSON.put(User.EMAIL, user.getEmail());
            mUserJSON.put(User.PASSWORD, user.getmPassword());

            new AddUserAsyncTask().execute(url.toString());
        } catch (JSONException e) {
            Toast.makeText(this, "Error with JSON creation on adding a course: "
                            + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    // Stuff that connects to the back when Register? is clicked
    private class AddUserAsyncTask extends AsyncTask<String, Void, String> {
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
                    Log.i(ADD_USER, mUserJSON.toString());
                    wr.write(mUserJSON.toString());
                    wr.flush();
                    wr.close();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to add the new course, Reason: "
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
            if (s.startsWith("Unable to add the new course")) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                return;
            }
                try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getBoolean("success")) {
                    Toast.makeText(getApplicationContext(), "User Added successfully"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User couldn't be added: "
                                    + jsonObject.getString("error")
                            , Toast.LENGTH_LONG).show();
                    Log.e(ADD_USER, jsonObject.getString("error"));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on Adding user"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e(ADD_USER, e.getMessage());
            }
        }
    }

    private class AuthenticateAsyncTask extends AsyncTask<String, Void, String> {
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
                    Log.i(GET_USER, mLoginJSON.toString());
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
                    response = "Unable to authenticate login, Reason: "
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

            if (s.startsWith("Unable to authenticate login")) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getBoolean("success")) {
                        Toast.makeText(getApplicationContext(), "User logged in successfully"
                                , Toast.LENGTH_SHORT).show();

                    // open MainActivity.java
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "User couldn't be logged in: "
                            , Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "JSON Parsing error on logging in user"
                                + e.getMessage()
                        , Toast.LENGTH_LONG).show();
                Log.e(GET_USER, e.getMessage());
            }
        }
    }
}
