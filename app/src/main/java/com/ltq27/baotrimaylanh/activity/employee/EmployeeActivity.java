package com.ltq27.baotrimaylanh.activity.employee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.databinding.ActivityEmployeeBinding;
import com.ltq27.baotrimaylanh.fragment.admin.UserFragment;
import com.ltq27.baotrimaylanh.fragment.customer.CustomerInforFragment;
import com.ltq27.baotrimaylanh.fragment.customer.CustomerManagerFragment;
import com.ltq27.baotrimaylanh.fragment.employee.EmployeeManagerFragment;

public class EmployeeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employee);

        bottomNavigation = findViewById(R.id.bottom_navigation_employee);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_manage) {
                    loadFragment(new EmployeeManagerFragment());
                    return true;
                } else if (itemId == R.id.navigation_user) {
                    loadFragment(new UserFragment());
                    return true;
                }
                return false;
            }
        });

        // Load ManageFragment by default and select the corresponding item
        loadFragment(new EmployeeManagerFragment());
        bottomNavigation.setSelectedItemId(R.id.navigation_manage);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}