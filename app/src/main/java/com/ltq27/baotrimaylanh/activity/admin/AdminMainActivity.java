package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.fragment.admin.ManageFragment;
import com.ltq27.baotrimaylanh.fragment.admin.UserFragment;

public class AdminMainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_manage) {
                    loadFragment(new ManageFragment());
                    return true;
                } else if (itemId == R.id.navigation_user) {
                    loadFragment(new UserFragment());
                    return true;
                }
                return false;
            }
        });

        // Load ManageFragment by default and select the corresponding item
        loadFragment(new ManageFragment());
        bottomNavigation.setSelectedItemId(R.id.navigation_manage);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}