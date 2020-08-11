package com.example.mobileappgit.authenticate.Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
/**
 * A simple {@link Fragment} subclass.

    The login fragment has a listener which waits until the SIGN IN button has been pressed
    -   If the email address doesn't have a '@' symbol in it a toast message is sent to the
        screen when the 'SIGN IN' button is pressed
    -   If the password has less then 6 characters in it a toast message is sent to the
        screen when the 'SIGN IN' button is pressed
 */
public class LoginFragment extends Fragment {

    /**
     * The login fragment's listener
     */
    private LoginFragmentListener mLoginFragmentListener;

    /**
     * Required empty public constructor
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Calls the login function
     */
    public interface LoginFragmentListener {
        public void login(String email, String pwd);
    }


    /**
     * Creates the page
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     The system calls this method to draw the Fragment UI for the first time
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getActivity().setTitle("Sign In");
        mLoginFragmentListener = (LoginFragmentListener) getActivity();
        final EditText emailText = view.findViewById(R.id.email_address_id);
        final EditText pwdText = view.findViewById(R.id.password_id);

        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             -   If the email address doesn't have a '@' symbol in it a toast message is sent to the
             screen when the 'SIGN IN' button is pressed
             -   If the password has less then 6 characters in it a toast message is sent to the
             screen when the 'SIGN IN' button is pressed
             */
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
    }

}