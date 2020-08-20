package com.example.mobileappgit.data.Item;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileappgit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {


    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_item_details, container, false);

        return v;
    }
}