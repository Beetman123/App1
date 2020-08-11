package com.example.mobileappgit.authenticate.Registration;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappgit.R;
import com.example.mobileappgit.authenticate.Registration.RegisterFragment;
import com.example.mobileappgit.authenticate.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignInActivity extends AppCompatActivity
        implements RegisterFragment.AddListener {

    public static final String ADD_USER = "add_user"; // should 'add_user' be all caps?
    private JSONObject mUserJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in); //

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sign_in_fragment_container, new RegisterFragment())
                .commit();
    }


    @Override
    public void addUser(User user) {
        StringBuilder url = new StringBuilder(getString(R.string.add_user)); // url supposed to be url?

        // Construct a JSONObject to build a formatted message to send.
        mUserJSON = new JSONObject();
        try {
            // Must be in this order (because its the order of the table)
            mUserJSON.put(User.FIRSTNAME, user.getFirstname());
            mUserJSON.put(User.LASTNAME, user.getLastname());
            mUserJSON.put(User.USERNAME, user.getmUsername());
            mUserJSON.put(User.EMAIL, user.getEmail());
            mUserJSON.put(User.PASSWORD, user.getmPassword());

            new AddCourseAsyncTask().execute(url.toString());
        } catch (JSONException e) { // is there a reason its 'e'
            Toast.makeText(this, "Error with JSON creation on adding a course: "
                            + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }



    private class AddCourseAsyncTask extends AsyncTask<String, Void, String> {
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
                }
                else {
                    Toast.makeText(getApplicationContext(), "User couldn't be added: " // error even though all fields are filled in error still pops up
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

}
