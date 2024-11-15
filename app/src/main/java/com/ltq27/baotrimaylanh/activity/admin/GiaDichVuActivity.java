package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.databinding.ActivityGiaDichVuBinding;
import com.ltq27.baotrimaylanh.model.GoiDichVu;

import java.util.List;

public class GiaDichVuActivity extends AppCompatActivity {
    private ActivityGiaDichVuBinding bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityGiaDichVuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

    }
}