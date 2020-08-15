package com.example.mobileappgit.PostItem;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobileappgit.CommunicateFragment;
import com.example.mobileappgit.Profile.ProfileFragment;
import com.example.mobileappgit.R;
import com.example.mobileappgit.SearchFragment;
import com.example.mobileappgit.main.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GetCategoryFragment {
    /*private boolean mMoviesBooksandMusic =false;  // maybe set all to false
    private boolean mGaming;
    private boolean mElectronics;

    private boolean mAutomobiles;
    private boolean mOtherVehicles;
    private boolean mVehicleParts;

    private boolean mServices;
    private boolean mJobs;
    private boolean mHousingForRentSale;

    private boolean mSportsAndOutdoors;
    private boolean mFashionAndAccessories;
    private boolean mBabyAndChild;

    private boolean mHomeAndAppliances;
    private boolean mToolsAndGardening;
    private boolean mOther;*/

    private String category;


// do I need to change the top portion of this switch?

    /*private BottomNavigationView.OnNavigationItemSelectedListener navListener =  // return void? How should I change BottomNavigationView?
            new BottomNavigationView.OnNavigationItemSelectedListener() {*/

  // private BottomNavigationView.OnNavigationItemSelectedListener category(){

                //@Override
                public String categorySelection(@NonNull MenuItem item) {
                //public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    boolean selectedCategory = false; // is this ok?
                    switch (item.getItemId()) { //prob change
                        case R.id.moviesEct:
                            category = "Movies";
                            //selectedCategory = mMoviesBooksandMusic;
                            break;
                        case R.id.gaming:
                            category = "Gaming";
                            //selectedCategory = mGaming;
                            break;
                        case R.id.electronics:
                            category = "Electronic";
                            //selectedCategory = mElectronics;
                            break;
                        case R.id.automobiles:
                            category = "Automobile";
                            //selectedCategory = mAutomobiles;
                            break;
                        case R.id.otherVehicles:
                            category = "OVehicles";
                            //selectedCategory = mOtherVehicles; // the best of the best
                            break;
                        case R.id.vehicleParts:
                            category = "VehicleParts";
                            //selectedCategory = mVehicleParts;
                            break;
                        case R.id.services:
                            category = "Services";
                            //selectedCategory = mServices;
                            break;
                        case R.id.jobs:
                            category = "Jobs";
                            //selectedCategory = mJobs;
                            break;
                        case R.id.housingForRentSale:
                            category = "Housing";
                            //selectedCategory = mHousingForRentSale;
                            break;
                        case R.id.sportsAndOutdoors:
                            category = "Sports_Outdoors";
                            //selectedCategory = mSportsAndOutdoors;
                            break;
                        case R.id.fashionAndAccessories:
                            category = "Fashion_Accessories";
                            //selectedCategory = mFashionAndAccessories;
                            break;
                        case R.id.babyAndChild:
                            category = "Baby_Child";
                            //selectedCategory = mBabyAndChild;
                            break;
                        case R.id.homeAndAppliances:
                            category = "Home_Appliances";
                            //selectedCategory = mHomeAndAppliances;
                            break;
                        case R.id.toolsAndGardening:
                            category = "Tools_Gardening";
                            //selectedCategory = mToolsAndGardening;
                            break;
                        case R.id.Other:
                            category = "Other";
                            //selectedCategory = mOther; // that's where I came from! wow
                            break;
                    }

                    // Change to save category instead of going to new fragment
                    Fragment frog = new PostItemFragment();


                    return category;
                }
            };


//}
