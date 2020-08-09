package com.example.mobileappgit.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mobileappgit.R;
import com.example.mobileappgit.main.MainMenuActivity;

/**
 * Currently is just a placeholder
 * But will be used to let the user put down information about who they are and what they
 * are doing on the app, what they are interested in buying/selling/trading
 */
public class ProfileFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container,false);


    }



}
