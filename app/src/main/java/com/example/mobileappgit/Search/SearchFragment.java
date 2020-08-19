package com.example.mobileappgit.Search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.data.Item.ItemDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Currently is just a placeholder
 * But will be used to search through the available trades / offers that other users have posted
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
    }

    private RecyclerView recyclerView;
    List<Item> itemList;
    private ItemDB mItemDB;
//Listener similar , getItems

    @Override
    public View onCreateView(/*@NonNull*/ LayoutInflater inflater, /*@Nullable*/ ViewGroup container, /*@Nullable*/ Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mItemDB = new ItemDB(getContext());
        ArrayList<Item> list = (ArrayList<Item>) mItemDB.getItems();
        ItemAdapter adapter = new ItemAdapter(list); // was initData()
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }

/*    private List<Model> initData() {                 // Create Items Prototype for Model

        List<Model> modelList = new ArrayList<>();
        modelList.add(new Model(R.drawable.sokka_welcomes_you, "I am Pants", "They turn the user into batman"));
        modelList.add(new Model(R.drawable.ic_launcher_background, "I am Headphones", "Sick beats dude!"));
        modelList.add(new Model(R.drawable.sokka_welcomes_you, "I am a box of oreos", "Wait, why am I trading these?"));
        modelList.add(new Model(R.drawable.ic_launcher_background, "I am Halt the Ranger", "Supper is important!"));
        modelList.add(new Model(R.drawable.sokka_welcomes_you, "I am Obi Wan Kenobi", "Hello there"));
        modelList.add(new Model(R.drawable.ic_launcher_background, "I am a  light saber", "Fwwwwip Zing Slash!"));
        modelList.add(new Model(R.drawable.sokka_welcomes_you, "I am a video game", "pew pew pew pew pew pew"));
        modelList.add(new Model(R.drawable.ic_launcher_background, "I am Duel of the Fates", " dun dun dundundun..."));
        modelList.add(new Model(R.drawable.sokka_welcomes_you, "I am Avatar: TLA", "Airbending Slice!"));
        modelList.add(new Model(R.drawable.ic_launcher_background, "I am lots of candy", "Very Yummy! YumYumYum!"));

        return modelList;
    }*/

}
