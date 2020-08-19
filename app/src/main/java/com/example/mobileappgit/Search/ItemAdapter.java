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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;
import com.example.mobileappgit.data.Item.ItemDetailFragment;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Viewholder> {
    private List<Item> itemList; //private final List<Course> mValues;
    Dialog myDialog;
    ItemDetailFragment itemDetailFragment;



    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    } // was modelList

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        final LayoutInflater inflater = null; // MAY CAUSE AN ERROR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem, viewGroup, false); // mContext was viewGroup.getContext()
        final Viewholder vHolder = new Viewholder(view);

        // Dialog ini

        /*myDialog = new Dialog(viewGroup.getContext());


        myDialog.setContentView(R.layout.fragment_item_details);
        Window window = myDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);*/




        vHolder.rowitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(viewGroup.getContext(),"Test Click"+itemList.get(vHolder.getAdapterPosition()).getTitle(),Toast.LENGTH_SHORT).show();

                //Toast.makeText(viewGroup.getContext(),"Test Click"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();

                /*myDialog.setContentView(R.layout.fragment_item_details);
                Window window = myDialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);*/



                /*TextView Title = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewTitle);
                TextView category = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewCategory);
                TextView description = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewDescription);
                TextView username = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewUsername);
                TextView condition = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewCondition);
                TextView price = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewPrice);
                TextView trade = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewTrade);
                TextView tradeFor = (TextView) *//*myDialog*//*view.findViewById(R.id.textViewTradeFor);



                // sets the text
                Title.setText(itemList.get(vHolder.getAdapterPosition()).getTitle());
                category.setText(itemList.get(vHolder.getAdapterPosition()).getCategory());
                description.setText(itemList.get(vHolder.getAdapterPosition()).getDescription());
                username.setText(itemList.get(vHolder.getAdapterPosition()).getUsername());
                condition.setText(itemList.get(vHolder.getAdapterPosition()).getCondition());
                //price.setText(itemList.get(vHolder.getAdapterPosition()).getPrice());
                //trade.setText(itemList.get(vHolder.getAdapterPosition()).getTrade());
                tradeFor.setText(itemList.get(vHolder.getAdapterPosition()).getTradeFor());
                //myDialog.show();*/


                // run the fragment


                //view = inflater.inflate(R.layout.fragment_item_details, viewGroup,false);


                ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
            }
        });

        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        String title = itemList.get(position).getTitle();
        String descriptionText = itemList.get(position).getDescription(); //.getBody();
        viewholder.setData(title, descriptionText);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{ // sees object layout and properties, probably
        private TextView title;
        private TextView description;

        private ConstraintLayout rowitem;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            rowitem = (ConstraintLayout) itemView.findViewById(R.id.item_row);

            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
        }

        private void setData(String titleText, String descriptionText) {
            title.setText(titleText);
            description.setText(descriptionText);
        }
    }

    }
