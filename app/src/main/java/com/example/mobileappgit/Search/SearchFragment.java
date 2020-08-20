package com.example.mobileappgit.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.data.Item.ItemDB;
import com.example.mobileappgit.main.MainActivity;

import java.util.ArrayList;

/**
 * Currently is just a placeholder
 * But will be used to search through the available trades / offers that other users have posted
 */
public class SearchFragment extends Fragment {
    private String mEditTextSubject;
    private String mEditTextMessage;

    public SearchFragment() {
    }

    private RecyclerView recyclerView;
    private ItemDB mItemDB;

    public void onClick (View view) { // TODO - delete? never used
        Item item = (Item) view.getTag();

        Context context = view.getContext();
        Intent intent = new Intent (context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mItemDB = new ItemDB(getContext());
        final ArrayList<Item> list = (ArrayList<Item>) mItemDB.getItems();
        ItemAdapter adapter = new ItemAdapter(list);
        adapter.setOnButtonClickListener(new ItemAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position) {
                mEditTextSubject = "Look at what I found on BarterUp!";
                mEditTextMessage = "It is a " + list.get(position).getTitle() +
                        ". \n The price that " + list.get(position).getUsername() +
                        " is asking for is $" + list.get(position).getPrice();
                sendMail(mEditTextSubject, mEditTextMessage);
            }
        });

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }

    // for email button
    public void sendMail(String mEditTextSubject, String mEditTextMessage) {

        String subject = mEditTextSubject;//.getText().toString();
        String message = mEditTextMessage;//.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
