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
import com.example.mobileappgit.authenticate.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

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


        final EditText emailEditText = view.findViewById(R.id.email_address_id);
        final CheckBox rememberCheckBox = view.findViewById(R.id.login_check_box);
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {                                                                   // TODO - Put 5 arguments into User
/*                    User user = new User(emailEditText.getText().toString()
                            , rememberCheckBox.isChecked());*/

                    ((LoginFragmentListener) getActivity())
                            .login(emailEditText.getText().toString(), ""
                                    , rememberCheckBox.isChecked());
                } catch(IllegalArgumentException e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        return view;
    }
}