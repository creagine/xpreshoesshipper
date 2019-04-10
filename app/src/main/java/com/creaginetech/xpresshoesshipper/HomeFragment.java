package com.creaginetech.xpresshoesshipper;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creaginetech.xpresshoesshipper.adapter.SectionsPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);



        setupViewPager();

        return view;
    }

    private void setupViewPager() {

        //adapter for the vewpager
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager());
        //add fragments to your adapter
        adapter.addFragment(new PickupFragment());
        adapter.addFragment(new DeliveryFragment());
        //init viewpager
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        //set adapter
        viewPager.setAdapter(adapter);

        //init tablayout
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        //pass the ciewpager to the tab layout
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorTab));
        tabLayout.setTabTextColors(getResources().getColor(R.color.tabTextWhite),getResources().getColor(R.color.tabTextBlack));

        //set the tabs index and set text, icon etc for your tabs
        tabLayout.getTabAt(0).setText("Pickup");
        tabLayout.getTabAt(1).setText("Delivery");


    }

}
