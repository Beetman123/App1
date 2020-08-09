package com.example.mobileappgit.authenticate;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappgit.MainActivity;
import com.example.mobileappgit.R;

/**
 * The Sign In Activity uses the Login Fragment Listener to check if the person would like
 * to stay logged in or to be logged out when they close the app
 */
public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener {


    private SharedPreferences mSharedPreferences;

    /**
     *
     *
     */
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
    public void login(String email, String pwd) {
        mSharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), true).commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}

