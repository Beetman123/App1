package com.example.mobileappgit.authenticate;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappgit.HomeFragment;
import com.example.mobileappgit.MainActivity;
import com.example.mobileappgit.R;
import com.example.mobileappgit.main.MainMenuActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity
        implements LoginFragment.LoginFragmentListener,
        RegisterFragment.RegisterFragmentListener{


    private boolean mLoginMode;
    private String mEmail;
    private boolean mRemember;
    public final static String EMAIL = "email";
    public final static String REMEMBER = "remember";
    private SharedPreferences mSharedPreferences;

    public static final String GET_USER = "GET_USER";
    private JSONObject mLoginJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        if(!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)){

            getSupportFragmentManager().beginTransaction().add(R.id.sign_in_fragment_container, new LoginFragment()).commit();

        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void launchRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sign_in_fragment_container, new RegisterFragment())
                // .addToBackStack(null)
                .commit();
    }

    @Override
    public void login(String email, String pwd, boolean shouldRemember) {
        mLoginMode = true;
        mEmail = email;
        mRemember = shouldRemember;
        mLoginJSON = new JSONObject();



        mSharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), true).commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
        new AuthenticateAsyncTask().execute("");
    }



    @Override
    public void register(String firstName, String lastName, String username, String email, String pwd) {
        // TODO - Create the json object for passing with the register url
        JSONObject register = new JSONObject();
        // Execute Async Task with register url
        // TODO - The following code should happen in the onPostExecute
        // once the login is authenticated
        // new AuthenticateAsyncTask().execute("");
    }


    private class AuthenticateAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // TODO - Check for success
            if (mLoginMode) {
                // If login mode, show login message
                // If failed, show the messages
                // If successful, here's where the shared preferences
                // must be update to remember the user
                // Navigate to the next activity
                if (mRemember) {
                    mSharedPreferences.edit()
                            .putString(EMAIL, mEmail)
                            .putBoolean(REMEMBER, mRemember)
                            .commit();
                } else {
                    mSharedPreferences.edit()
                            .clear()
                            .commit();
                }
                Intent intent = new Intent(getApplicationContext()
                        , MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // else register messages
                // Next navigation to launch Loginfragment
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
    }


}

