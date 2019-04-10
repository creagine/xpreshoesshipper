package com.creaginetech.xpresshoesshipper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.creaginetech.xpresshoesshipper.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private ProfileFragment profileFragment;
    private HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.mainBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();


        replaceFragment(homeFragment);



    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container,fragment);
        fragmentTransaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment;

            switch (menuItem.getItemId()){

                case R.id.home:
                    fragment = new HomeFragment();
                    replaceFragment(fragment);
                    return true;

                case R.id.profile:
                    fragment = new ProfileFragment();
                    replaceFragment(fragment);
                    return true;

            }


            return false;
        }
    };


}
