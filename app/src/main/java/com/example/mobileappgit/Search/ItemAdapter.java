package com.example.mobileappgit.Search;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.data.Item.ItemDetailFragment;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Viewholder> {
    private List<Item> itemList; //private final List<Course> mValues;
    Dialog myDialog;
    ItemDetailFragment itemDetailFragment;
    private int[] images = {R.drawable.sokka_welcomes_you, R.drawable.jeep_wrangler1, R.drawable.jeep_wrangler2};



    public ItemAdapter(List<Item> itemList/*, int[] images*/) {
        this.itemList = itemList;
        //this.images = images; // delete?
    } // was modelList

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        final LayoutInflater inflater = null; // MAY CAUSE AN ERROR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_item_details, viewGroup, false); // mContext was viewGroup.getContext(). rowitem was fragment_item_details
        final Viewholder vHolder = new Viewholder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        String titleText = itemList.get(position).getTitle();
        String descriptionText = itemList.get(position).getDescription(); //.getBody();
        String categoryText = itemList.get(position).getCategory();
       // String usernameText = itemList.get(position).getUsername();
        String conditionText = itemList.get(position).getCondition();
        String tradeForText = itemList.get(position).getTradeFor();
        String priceText = itemList.get(position).getPrice().toString();

        int image_id = images[2]; // there are 3 images (0-2) to chose from top images list

        viewholder.setData(titleText, "Description: " + descriptionText,
                "Category: \n" + categoryText,
                "Condition: \n" + conditionText, "$" + priceText,
                "Trade for: \n" + tradeForText, image_id);
        // TODO - if tradeFor = 'n' then make a different layout saying that they don't want to trade
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{ // sees object layout and properties, probably
        private TextView title;
        private TextView description;
        private TextView category;
        private TextView condition;
        private TextView price;
        private TextView tradeFor;
        //private String date;
        private ImageView image;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            category = itemView.findViewById(R.id.textViewCategory);
            condition = itemView.findViewById(R.id.textViewCondition);
            tradeFor = itemView.findViewById(R.id.textViewTradeFor);
            image = itemView.findViewById(R.id.imageViewSokka);
            price = itemView.findViewById(R.id.textViewPrice);
        }

        private void setData(String titleText, String descriptionText, String categoryText,
                             String conditionText, String priceText, String tradeForText, int imageResource) {
            title.setText(titleText);
            description.setText(descriptionText);
            category.setText(categoryText);
            //username.setText(usernameText);
            condition.setText(conditionText);
            tradeFor.setText(tradeForText);
            image.setImageResource(imageResource);
            price.setText(priceText);
        }
    }

    }
