package com.example.mobileappgit.authenticate.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileappgit.R;
import com.example.mobileappgit.authenticate.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    private AddListener mAddListener;
    //private AddListener mSignInReturnListener; // for sign in button
    //private Button signInReturn;

    public interface AddListener {
        void addUser(User user);
    }

    public RegisterFragment(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddListener = (AddListener) getActivity();
        //mSignInReturnListener = (
        //signInReturn = (Button).findViewById(R.id.login_return_id)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container
                , false);
        getActivity().setTitle("Add a New Course");
        final EditText userFirstNameEditText = v.findViewById(R.id.editTextFirstName);
        final EditText userLastNameEditText = v.findViewById(R.id.editTextLastName);
        final EditText userEmailEditText = v.findViewById(R.id.editTextEmail);
        final EditText userUsernameEditText = v.findViewById(R.id.editTextUsername);
        final EditText userPasswordEditText = v.findViewById(R.id.editTextPassword);


        // What happens when "Add User" button is pressed
        Button addUserButton = v.findViewById(R.id.register_id);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstname = userFirstNameEditText.getText().toString();    // needs to be same order as user.java
                String Lastname = userLastNameEditText.getText().toString();
                String Email = userEmailEditText.getText().toString();
                String Username = userUsernameEditText.getText().toString();
                String Password = userPasswordEditText.getText().toString();

                // Does it look like a valid email
                if(TextUtils.isEmpty(Email) || !Email.contains("@")) {
                    Toast.makeText(v.getContext(),"Enter valid email address",
                            Toast.LENGTH_SHORT)
                            .show();
                    userEmailEditText.requestFocus();
                }

                // does it meet password requirements?
                else if (TextUtils.isEmpty(Password) || Password.length() < 6) {
                    Toast.makeText(v.getContext(), "Enter a valid password( atleast 6 characters)"
                            , Toast.LENGTH_SHORT)
                            .show();
                    userPasswordEditText.requestFocus();
                }

                // if all other conditions are met
                else {
                    User user = new User(Firstname, Lastname, Email, Username, Password); // who's order does it need to follow?
                    if (mAddListener != null) {
                        mAddListener.addUser(user);
                    }
                }
            }
        }






        /*// What happens when "Return to Login" button is pressed
        Button returnToLoginButton = v.findViewById(R.id.login_return_id);

            //Button returnToLoginButton = v.findViewById(R.id.login_return_id);
        addUserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Firstname = userFirstNameEditText.getText().toString();    // needs to be same order as user.java
                    String Lastname = userLastNameEditText.getText().toString();
                    String Email = userEmailEditText.getText().toString();
                    String Username = userUsernameEditText.getText().toString();
                    String Password = userPasswordEditText.getText().toString();
                    User user = new User(Firstname, Lastname, Email, Username, Password); // who's order does it need to follow?
                    if (mAddListener != null) {
                        mAddListener.addUser(user);
                    }
                }
            }*/


        );
        return v;
    }

}