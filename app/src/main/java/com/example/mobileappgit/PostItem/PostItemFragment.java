package com.example.mobileappgit.PostItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;

/**
 * The PostItemFragment is used to let the item post/create new trades to the Search bar
 *
 *
 */
public class PostItemFragment extends Fragment {
    private AddListener mAddListener;

    public interface AddListener {
        void addItem(Item item);
    }

    public PostItemFragment(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddListener = (AddListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_postitem, container
                , false);
        getActivity().setTitle("Create Item");
        final EditText itemName = v.findViewById(R.id.item_name);
        final EditText itemDescription = v.findViewById(R.id.description_id);
        final EditText itemPrice = v.findViewById(R.id.sell_amount_id);
        final Switch ifItemTrade = v.findViewById(R.id.trade_item_switch_id);
        final EditText itemTradeFor = v.findViewById(R.id.trade_want_id);
        final EditText itemCondition = v.findViewById(R.id.condition_id);

        Button addItemButton = v.findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = itemName.getText().toString();
                String Description = itemDescription.getText().toString();
                String Category = "category"; //itemCategory.getText().toString();
                String Price = itemPrice.getText().toString();
                String Condition = itemCondition.getText().toString();
/*                Integer Date = Calendar.DATE;
                String date = Timestamp.t*/
                String Username = Item.USERNAME;
                String Date = "1";

                String Trade;
                String TradeFor = "";
                if(ifItemTrade.isChecked())
                {
                    // if Checked = want to trade?
                    Trade = "y";
                    TradeFor = itemTradeFor.getText().toString();
                }
                else
                {
                    Trade = "n";
                }

                Item item = new Item(Title, Category, Description, Username, // "" (for username)
                        Condition, Price, Trade, TradeFor, Date);
                if (mAddListener != null) {
                    mAddListener.addItem(item);
                }
            }
        });

        return v;
    }
}
