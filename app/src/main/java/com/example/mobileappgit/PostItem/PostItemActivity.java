package com.example.mobileappgit.PostItem;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;

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
        implements PostItemFragment.AddListener {

    public static final String ADD_ITEM = "add_item"; // is referencing urls.xml
    public static final String GET_ITEM = "get_item"; // is referencing urls.xml
    private JSONObject mItemJSON;


    /**
     * Creates the page
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.post_item_fragment_container, new PostItemFragment())
                .commit();
    }

    @Override
    public void addItem(Item item) {
        StringBuilder url = new StringBuilder(getString(R.string.add_item));

        // Construct a JSONObject to build a formatted message to send
        mItemJSON = new JSONObject();
        try {
            // Must be in this order (because its the order of the table)
            mItemJSON.put(Item.TITLE, item.getTitle());
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
                    Toast.makeText(getApplicationContext(), "Item couldn't be added: "
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
