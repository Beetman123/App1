package com.example.mobileappgit.authenticate.Login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappgit.R;
import com.example.mobileappgit.authenticate.Registration.RegisterFragment;
import com.example.mobileappgit.authenticate.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    //private RegisterFragment.AddListener mAddListener;
    private LoginFragmentListener mLoginFragmentListener; // Added this !!! is this correct?
    //private LoginListener mLoginListener;

    /*public interface LoginListener {
        public void login (String email_address_id, String password_id, boolean login_check_box);
    }*/

    public interface LoginFragmentListener {
        void launchRegisterFragment(); // Why is this here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        void login(String email, String pwd, boolean shouldRemember);
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginFragmentListener = (LoginFragmentListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView registerTextView = view.findViewById(R.id.register_text_view);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginFragmentListener) getActivity()).launchRegisterFragment();
            }

        });/*final EditText loginEmail = view.findViewById(R.id.email_address_id);
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

        final EditText emailEditText = view.findViewById(R.id.email_address_id);
        final EditText loginPassword = view.findViewById(R.id.password_id);

        final CheckBox rememberCheckBox = view.findViewById(R.id.login_check_box);
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((LoginFragmentListener) getActivity()).login
                        (emailEditText.getText().toString(),
                                loginPassword.getText().toString(),
                                rememberCheckBox.isChecked());

            }
        });
        return view;
    }
}