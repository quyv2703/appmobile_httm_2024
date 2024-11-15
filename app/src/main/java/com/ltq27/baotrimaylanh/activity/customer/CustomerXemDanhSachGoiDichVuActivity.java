package com.ltq27.baotrimaylanh.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.databinding.ActivityCustomerXemDanhSachGoiDichVuBinding;
import com.ltq27.baotrimaylanh.model.GoiDichVu;
import com.ltq27.baotrimaylanh.recyclerview.RcvGoiBaoTriAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerXemDanhSachGoiDichVuActivity extends AppCompatActivity  implements ItemClick, RcvGoiBaoTriAdapter.ButtonClick{
    private ActivityCustomerXemDanhSachGoiDichVuBinding bd;
    private List<GoiDichVu> listGoiDVCustomerViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityCustomerXemDanhSachGoiDichVuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvGoiBaoTri.setHasFixedSize(true);
        bd.back.setOnClickListener(v -> finish());
        setEvent();

    }

    private void setEvent() {

        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<GoiDichVu>> call=apiInterface.getListGoiDichVu();
        call.enqueue(new Callback<List<GoiDichVu>>() {
            @Override
            public void onResponse(Call<List<GoiDichVu>>  call, Response<List<GoiDichVu>> response) {
                if (response.isSuccessful()){
                    List<GoiDichVu>  list=response.body();
                    listGoiDVCustomerViews=list;
                    RcvGoiBaoTriAdapter rcvGoiBaoTriAdapter=new
                            RcvGoiBaoTriAdapter(listGoiDVCustomerViews,CustomerXemDanhSachGoiDichVuActivity.this,
                            CustomerXemDanhSachGoiDichVuActivity.this, CustomerXemDanhSachGoiDichVuActivity.this);
                    bd.rcvGoiBaoTri.setAdapter(rcvGoiBaoTriAdapter);
                    bd.rcvGoiBaoTri.setLayoutManager(new LinearLayoutManager(CustomerXemDanhSachGoiDichVuActivity.this));
                }
                else {
                    Toast.makeText(CustomerXemDanhSachGoiDichVuActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GoiDichVu>>  call, Throwable t) {
                Toast.makeText(CustomerXemDanhSachGoiDichVuActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onButtonClick(Long goiDichVuId) {
        Intent intent=new Intent(CustomerXemDanhSachGoiDichVuActivity.this,NhanXetActivity.class);
        intent.putExtra("id",goiDichVuId);
        startActivity(intent);
    }

    @Override
    public void onClick(Long id) {
        Intent intent=new Intent(CustomerXemDanhSachGoiDichVuActivity.this,DatLichActivity.class);
        intent.putExtra("id",id);

        startActivity(intent);
    }
}