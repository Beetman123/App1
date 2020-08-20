package com.example.mobileappgit.Search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Viewholder> {
    private List<Item> itemList;
    private int[] images = {R.drawable.sokka_welcomes_you, R.drawable.jeep_wrangler1, R.drawable.jeep_wrangler2};



    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_item_details, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        String titleText = itemList.get(position).getTitle();
        String descriptionText = itemList.get(position).getDescription();
        String categoryText = itemList.get(position).getCategory();
        String conditionText = itemList.get(position).getCondition();
        String tradeForText = itemList.get(position).getTradeFor();
        String priceText = itemList.get(position).getPrice();

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

    class Viewholder extends RecyclerView.ViewHolder{ // TODO - make static ???
        private TextView title;
        private TextView description;
        private TextView category;
        private TextView condition;
        private TextView price;
        private TextView tradeFor;
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
            condition.setText(conditionText);
            tradeFor.setText(tradeForText);
            image.setImageResource(imageResource);
            price.setText(priceText);
        }
    }}
