package com.example.mobileappgit.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mobileappgit.CommunicateFragment;
import com.example.mobileappgit.PostItem.PostItemActivity;
import com.example.mobileappgit.PostItem.PostItemFragment;
import com.example.mobileappgit.Profile.ProfileFragment;
import com.example.mobileappgit.R;
import com.example.mobileappgit.Search.SearchFragment;
import com.example.mobileappgit.authenticate.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener); // 11:30
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
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
                            // TODO - run the PostItemActivity
                            //Intent intent = new Intent(this, PostItemActivity.class);
                            //EditText editText = (EditText) findViewById(R.id.editText);
                            //String message = editText.getText().toString();
                            //intent.putExtra(EXTRA_MESSAGE, message);
                            //startActivity(intent);

                            //Intent myIntent = new Intent(MainActivity.this.getActivity(), PostItemActivity.class);
                            //Intent myIntent = new Intent(v.getContext(), PostItemActivity.class);
                            startActivity( new Intent(MainActivity.this, PostItemActivity.class));

                            // selectedFragment = new PostItemFragment();
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