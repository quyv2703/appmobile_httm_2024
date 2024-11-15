package com.ltq27.baotrimaylanh.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.admin.GoiDichVuActivity;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.apiresponse.ListGoiDVCustomerView;
import com.ltq27.baotrimaylanh.databinding.ActivityCustomerMainBinding;
import com.ltq27.baotrimaylanh.model.GoiDichVu;
import com.ltq27.baotrimaylanh.model.LoaiMayLanh;
import com.ltq27.baotrimaylanh.recyclerview.RcvGoiBaoTriAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerMainActivity extends AppCompatActivity {
    private ActivityCustomerMainBinding bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityCustomerMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        setEvent();

    }

    private void setEvent() {
       bd.btnDanhSachGoiDichVu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(CustomerMainActivity.this, CustomerXemDanhSachGoiDichVuActivity.class);
               startActivity(intent);
           }
       });
       bd.btnXemHoaDon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(CustomerMainActivity.this, DanhSachHoaDonActivity.class);
               startActivity(intent);
           }
       });
    }


}