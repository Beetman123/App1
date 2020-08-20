package com.example.mobileappgit.authenticate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileappgit.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * is the page that comes up first when the app is opened
 * the login fragment is where the user puts in their email and password and the login fragment
 * verifies if the login is correct
 */
public class LoginFragment extends Fragment {
    private LoginFragmentListener mLoginFragmentListener;

    public interface LoginFragmentListener {
        void launchRegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false); // changing from fragment_register
        TextView registerTextView = view.findViewById(R.id.register_text_view);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginFragmentListener) getActivity()).launchRegisterFragment();
            }

        });

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