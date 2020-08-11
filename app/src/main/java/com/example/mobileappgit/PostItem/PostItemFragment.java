package com.example.mobileappgit.PostItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;

/**
 * Currently is just a placeholder
 * But will be used to let the user post/create new trades to the Search bar
 *
 *
 * Actually ItemListFragment.java may be what PostItemFragment is supposed to be
 *
 *
 * should add a 'switch'Listener and if it is set to false we set the visibility of trades to gone
 * if set to true we show them.
 */
public class PostItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_postitem, container,false);
    }
}
