package com.example.mobileappgit.Search;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/*import com.example.courseswebservicesapp.model.Course;
import com.example.courseswebservicesapp.model.CourseContent;*/
import com.example.mobileappgit.R;
import com.example.mobileappgit.data.Item.Item;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link CourseListActivity}
 * in two-pane mode (on tablets) or a {@link CourseDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            /*mItem = CourseContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));// TODO - undo
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)
                    activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getmItemId());
            }*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.course_detail, container, false); // TODO - undo

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.item_detail)).setText(
            //mItem.getmItemShortDesc());
            /*((TextView) rootView.findViewById(R.id.item_detail_id)).setText( // TODO - undo
                    mItem.getmItemId());
            ((TextView) rootView.findViewById(R.id.item_detail_short_desc)).setText(
                    mItem.getmItemId());
            ((TextView) rootView.findViewById(R.id.item_detail_long_desc)).setText(
                    mItem.getmItemId());
            ((TextView) rootView.findViewById(R.id.item_detail_prereqs)).setText(
                    mItem.getmItemId());*/
        }

        // return rootView; // TODO - undo
        return null;
    }
}