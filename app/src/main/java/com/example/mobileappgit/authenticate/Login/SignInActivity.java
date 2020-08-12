package com.example.mobileappgit.authenticate.Login;

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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
import com.example.mobileappgit.authenticate.Registration.RegisterFragment;
import com.example.mobileappgit.main.MainActivity;

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
        RegisterFragment.RegisterFragmentListener {

    private boolean mLoginMode;
    private String mEmail;
    private boolean mRemember;
    public final static String SIGN_IN_FILE_PREFS = "edu.uw.tacoma.menakaapp.sign_in_file_prefs"; // Needs to change
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mSharedPreferences = getSharedPreferences(SIGN_IN_FILE_PREFS, Context.MODE_PRIVATE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sign_in_fragment_container, new LoginFragment())
                .commit();
    }


    /**
     * Required empty public constructor
     */
/*    public SignInActivity() {
        // Required empty public constructor
    }*/

    @Override
    public void launchRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sign_in_fragment_container, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void login(String email, String pwd, boolean shouldRemember) {
        mLoginMode = true;
        mEmail = email;
        mRemember = shouldRemember;



        // TODO - Create the json object for passing with the login url




        // Execute Async Task with login url
        new AuthenticateAsyncTask().execute("");
        // TODO - The following code should happen in the onPostExecute
        // once the login is authenticated
    }

    @Override
    public void register(String email, String pwd) {

/*        getSupportFragmentManager().beginTransaction()
                .add(R.id.sign_in_fragment_container, new LoginFragment())
                .commit();*/


        // TODO - Create the json object for passing with the register url
        // Execute Async Task with register url
        // TODO - The following code should happen in the onPostExecute
        // once the login is authenticated
        new AuthenticateAsyncTask().execute("");
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
                        , MainActivity.class);                   //TODO - Check where this takes me, THIS COULD BE WRONG !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                startActivity(intent);
                finish();
            } else {
                // else register messages
                // Next navigation to launch Loginfragment
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
    }



/*    *//**
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
    }*/

}