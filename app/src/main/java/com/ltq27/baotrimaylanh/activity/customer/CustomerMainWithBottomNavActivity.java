package com.ltq27.baotrimaylanh.activity.customer;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ltq27.baotrimaylanh.R;



import com.ltq27.baotrimaylanh.fragment.customer.CustomerInforFragment;
import com.ltq27.baotrimaylanh.fragment.customer.CustomerManagerFragment;

public class CustomerMainWithBottomNavActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_main_with_bottom_nav);
        bottomNavigation = findViewById(R.id.bottom_navigation_customer);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_manage) {
                    loadFragment(new CustomerManagerFragment());
                    return true;
                } else if (itemId == R.id.navigation_user) {
                    loadFragment(new CustomerInforFragment());
                    return true;
                }
                return false;
            }
        });

        // Load ManageFragment by default and select the corresponding item
        loadFragment(new CustomerManagerFragment());
        bottomNavigation.setSelectedItemId(R.id.navigation_manage);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}