package com.example.mobileappgit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Currently is just a placeholder
 * But will be used to keep track of communication threads between users
 * as well as send messages/offers through the app
 */
public class CommunicateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Communicate");
        return inflater.inflate(R.layout.fragment_communicate, container,false);
    }
}
