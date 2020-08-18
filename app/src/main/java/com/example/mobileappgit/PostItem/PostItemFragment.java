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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Currently is just a placeholder
 * But will be used to let the item post/create new trades to the Search bar
 *
 *
 * Actually ItemListFragment.java may be what PostItemFragment is supposed to be
 *
 *
 * should add a 'switch'Listener and if it is set to false we set the visibility of trades to gone
 * if set to true we show them.
 */
public class PostItemFragment extends Fragment {
/*    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_postitem, container,false);
    }*/

    private AddListener mAddListener;
    //private AddListener mSignInReturnListener; // for sign in button
    //private Button signInReturn;

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
        //mSignInReturnListener = (
        //signInReturn = (Button).findViewById(R.id.login_return_id)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_postitem, container
                , false);
        getActivity().setTitle("Add a new item");
        final EditText itemName = v.findViewById(R.id.item_name);
        final EditText itemDescription = v.findViewById(R.id.description_id);
        //final EditText itemCategory = v.findViewById(R.id.category_spinner); // TODO - uncomment when this when this is
        final EditText itemPrice = v.findViewById(R.id.sell_amount_id);
        final Switch ifItemTrade = v.findViewById(R.id.trade_item_switch_id); // need to change to a 'y' or 'n'
        final EditText itemTradeFor = v.findViewById(R.id.trade_want_id);
        final EditText itemCondition = v.findViewById(R.id.condition_id);


        // What happens when "Add Item" button is pressed
        //Button addItemButton = v.findViewById(R.id.register_id); // !!!!!!!!!!!!!!!!!!!! // TODO - THIS WAS CAUSING THE ERROR
        Button addItemButton = v.findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = itemName.getText().toString();    // needs to be same order as item.java
                String Description = itemDescription.getText().toString();
                String Category = "category"; //itemCategory.getText().toString();
                String Price = itemPrice.getText().toString();
                String Condition = itemCondition.getText().toString();
/*                Integer Date = Calendar.DATE;
                String date = Timestamp.t*/
                String Itemname = Item.USERNAME;
                String Date = "1";

                String Trade;
                String TradeFor = "";
                if(ifItemTrade.isChecked()) // TODO - (later) could make the TradeFor disappear/appear when it is switched (would need a SwitchListener)
                {
                    // if Checked = want to trade?
                    Trade = "y";
                    TradeFor = itemTradeFor.getText().toString();
                }
                else
                {
                    Trade = "n";
                }


                // TODO - change below line
                    Item item = new Item(Title, Category, Description, "", // TODO - get rid of username requierment
                            Condition, Price, Trade, TradeFor, Date); // who's order does it need to follow?
                    if (mAddListener != null) {
                        mAddListener.addItem(item);
                    }
                //}
            }
        }

        /*// What happens when "Return to Login" button is pressed
        Button returnToLoginButton = v.findViewById(R.id.login_return_id);

            //Button returnToLoginButton = v.findViewById(R.id.login_return_id);
        addItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Firstname = itemName.getText().toString();    // needs to be same order as item.java
                    String Lastname = itemDescription.getText().toString();
                    String Email = itemEmailEditText.getText().toString();
                    String Username = itemUsernameEditText.getText().toString();
                    String Password = itemPasswordEditText.getText().toString();
                    Item item = new Item(Firstname, Lastname, Email, Username, Password); // who's order does it need to follow?
                    if (mAddListener != null) {
                        mAddListener.addItem(item);
                    }
                }
            }*/

        );
        return v;
    }
}
