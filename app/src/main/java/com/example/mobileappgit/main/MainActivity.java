package com.example.mobileappgit.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.example.mobileappgit.CommunicateFragment;
import com.example.mobileappgit.PostItem.PostItemActivity;
import com.example.mobileappgit.PostItem.PostItemFragment;
import com.example.mobileappgit.Profile.ProfileFragment;
import com.example.mobileappgit.R;
import com.example.mobileappgit.Search.SearchFragment;
import com.example.mobileappgit.authenticate.LoginFragment;
import com.example.mobileappgit.authenticate.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

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
                            break;

                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_plus:
                            // open PostItemActivity.java
                            Intent i = new Intent(getApplicationContext(), PostItemActivity.class);
                            startActivity(i);
                            //finish(); // makes the app crash

                            /*Intent i = new Intent(this, SignInActivity.class); // Replaced oldSignInActivity.class with SignInActivity.class
                            startActivity(i);
                            finish();*/



                            ;/*final EditText loginEmail = view.findViewById(R.id.email_address_id);
        final EditText loginPassword = view.findViewById(R.id.password_id);

        // Added to code
        // What happens when "Login" button is pressed
        Button loginUserButton = view.findViewById(R.id.register_id);
        loginUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String Firstname = userFirstNameEditText.getText().toString();    // needs to be same order as user.java
                //String Lastname = userLastNameEditText.getText().toString();
                String Email = loginEmail.getText().toString(); // needs to be same order as user.java
                //String Username = userUsernameEditText.getText().toString();
                String Password = loginPassword.getText().toString();

                //User user = new User(Firstname, Lastname, Email, Username, Password); // who's order does it need to follow?

                // call login method


                if (mAddListener != null) {
                    mAddListener.addUser(user);
                }
            }
        }*/
/*

                            final EditText emailEditText = view.findViewById(R.id.email_address_id);
                            final EditText loginPassword = view.findViewById(R.id.password_id);

                            final CheckBox rememberCheckBox = view.findViewById(R.id.login_check_box);
                            Button loginButton = view.findViewById(R.id.login_button);
                            loginButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    ((LoginFragment.LoginFragmentListener) getActivity()).login
                                            (emailEditText.getText().toString(),
                                                    loginPassword.getText().toString(),
                                                    rememberCheckBox.isChecked());

                                }
                            });
                            return view;
                        }

*/


                            break;
                        case R.id.nav_communicate:
                            selectedFragment = new CommunicateFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }


                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}