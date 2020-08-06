package com.example.mobileappgit.authenticate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;

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
 */
public class RegisterFragment extends Fragment {
    public static final String ADD_COURSE = "ADD_COURSE";
    private JSONObject mUserJSON;
    public interface RegisterFragmentListener {
        //String firstName, String lastName, String username,
        void register(String firstName, String lastName, String username, String email, String pwd);
    }

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button registerButton = view.findViewById(R.id.register_id);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterFragmentListener) getActivity()).register("","","","","");
            }
        });

        return view;
    }
}
//    @Override
//    public void addCourse(User user) {
//        StringBuilder url = new StringBuilder(getString(R.string.add_user));
//
//
//         mUserJSON = new JSONObject();
//        try{
//            mUserJSON.put(User.FIRSTNAME, user.getmFirstName();
//            mUserJSON.put(User.LASTNATME, user.getmLastName();
//            mUserJSON.put(User.USERNAME, user.getmUsername();
//            mUserJSON.put(User.EMAIL, user.getmEmail();
//            mUserJSON.put(User.PASSWORD, user.getmPassword();
//            new AddCourseAsyncTask().execute(url.toString());
//        }catch(JSONException e){
//            Toast.makeText(this,"Error with JSON creation on addng a course: "
//                    + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private class AddCourseAsyncTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//            String response = "";
//            HttpURLConnection urlConnection = null;
//            for (String url : urls) {
//                try {
//                    URL urlObject = new URL(url);
//                    urlConnection = (HttpURLConnection) urlObject.openConnection();
//                    urlConnection.setRequestMethod("POST");
//                    urlConnection.setRequestProperty("Content-Type", "application/json");
//                    urlConnection.setDoOutput(true);
//                    OutputStreamWriter wr =
//                            new OutputStreamWriter(urlConnection.getOutputStream());
//
//                    // For Debugging
//                    Log.i(ADD_COURSE, mUserJSON.toString());
//                    wr.write(mUserJSON.toString());
//                    wr.flush();
//                    wr.close();
//
//                    InputStream content = urlConnection.getInputStream();
//
//                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
//                    String s = "";
//                    while ((s = buffer.readLine()) != null) {
//                        response += s;
//                    }
//
//                } catch (Exception e) {
//                    response = "Unable to add the new course, Reason: "
//                            + e.getMessage();
//                } finally {
//                    if (urlConnection != null)
//                        urlConnection.disconnect();
//                }
//            }
//            return response;
//        }
//



//@Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_register, container, false);
//        getActivity().setTitle("Register New Account");
//        final EditText firstNameEdit = view.findViewById(R.id.editTextFirstName);
//        final EditText laseNameEdit = view.findViewById(R.id.editTextLastName);
//        final EditText usernameEdit = view.findViewById(R.id.editTextUsername);
//        final EditText emailEdit = view.findViewById(R.id.editTextEmail);
//        final EditText passwordEdit = view.findViewById(R.id.editTextPassword);
//        Button registerButton = view.findViewById(R.id.register_id);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  ((RegisterFragmentListener) getActivity()).register("", "","","","");
//                String firsName = firstNameEdit.getText().toString();
//                String lastName = laseNameEdit.getText().toString();
//                String username = usernameEdit.getText().toString();
//                String email = emailEdit.getText().toString();
//                String password = passwordEdit.getText().toString();
//                User user = new User(firsName, lastName, username , email,password );
//            }
//        });
//
//        return view;
//    }