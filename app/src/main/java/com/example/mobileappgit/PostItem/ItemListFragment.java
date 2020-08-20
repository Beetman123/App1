package com.example.mobileappgit.PostItem;

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

The item list fragment
 */

public class ItemListFragment extends Fragment {

    /**
     * The Item List fragment's listener
     */
    private ItemListFragmentListener mItemListFragmentListener;

    /**
     * Required empty public constructor
     */
    public ItemListFragment() {
        // Required empty public constructor
    }

    /**
     * Calls the ItemList function
     */
        public interface ItemListFragmentListener {
        void ItemList(String email, String pwd);
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

     View Visibility in Android could be used to hide or show the text using the switches to activate it
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_postitem, container, false);
        getActivity().setTitle("Add Item");
        mItemListFragmentListener = (ItemListFragmentListener) getActivity();

        final EditText emailText = view.findViewById(R.id.email_address_id);
        final EditText pwdText = view.findViewById(R.id.password_id);

        Button ItemListButton = view.findViewById(R.id.add_item_button);
        ItemListButton.setOnClickListener(new View.OnClickListener() {

            /**
             * NEEDS TO BE CHANGED
             *
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
                    Toast.makeText(v.getContext(),"Enter a valid password (at least 6 characters)"
                            , Toast.LENGTH_SHORT)
                            .show();
                    pwdText.requestFocus();


                }
                else{
                    mItemListFragmentListener.ItemList(emailText.getText().toString(), pwdText.getText().toString());
                }
            }
        });

        return  view;
    }
}
