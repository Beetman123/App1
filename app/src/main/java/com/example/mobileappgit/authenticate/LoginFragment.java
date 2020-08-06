package com.example.mobileappgit.authenticate;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
/**
 * A simple {@link Fragment} subclass.

 */
public class LoginFragment extends Fragment {
    private LoginFragmentListener mLoginFragmentListener;

    public LoginFragment() {
        // Required empty public constructor
    }
    public interface LoginFragmentListener {
        void launchRegisterFragment();
        void login(String email, String pwd, boolean shouldRemember);
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        });
        getActivity().setTitle("Sign In");
        mLoginFragmentListener = (LoginFragmentListener) getActivity();
        final EditText emailText = view.findViewById(R.id.email_address_id);
        final EditText pwdText = view.findViewById(R.id.password_id);
        final CheckBox rememberCheckBox = view.findViewById(R.id.login_check_box);
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
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
                mLoginFragmentListener.login(emailText.getText().toString(), pwdText.getText().toString(), rememberCheckBox.isChecked());
            }}
        });

        return  view;
    }

}