package com.example.mobileappgit.Search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappgit.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Viewholder> {
    private List<Model> modelList;

    public ItemAdapter(List<Model> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem, viewGroup, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

        int resource = modelList.get(position).getImageIcon();            // Model Items ***
        String title = modelList.get(position).getTitle();
        String body = modelList.get(position).getBody();
        viewholder.setData(resource, title, body);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{ // sees object layout and properties, probably

        private ImageView imageView;         // Layout Items ***
        private TextView title;
        private TextView body;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textTitle);
            body = itemView.findViewById(R.id.textBody);
        }

        private void setData(int imageResource, String titleText, String bodyText) {

            imageView.setImageResource(imageResource);        // Layout Items
            title.setText(titleText);
            body.setText(bodyText);
        }
    }



}
