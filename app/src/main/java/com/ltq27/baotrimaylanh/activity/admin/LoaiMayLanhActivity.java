package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityLoaiMayLanhBinding;
import com.ltq27.baotrimaylanh.model.LoaiMayLanh;
import com.ltq27.baotrimaylanh.recyclerview.RcvLoaiMayLanhAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiMayLanhActivity extends AppCompatActivity {
    private ActivityLoaiMayLanhBinding bd;
    private List<LoaiMayLanh> loaiMayLanhList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityLoaiMayLanhBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvLML.setHasFixedSize(true);
        bd.rcvLML.setLayoutManager(new LinearLayoutManager(this));
        setEvent();
    }

    private void setEvent() {
        bd.back.setOnClickListener(v -> finish());
        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<LoaiMayLanh>>> call = apiInterface.layDanhSachLoaiMayLanh();
        call.enqueue(new Callback<ApiResponse<List<LoaiMayLanh>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<LoaiMayLanh>>> call, Response<ApiResponse<List<LoaiMayLanh>>> response) {
                if (response.isSuccessful()){
                    ApiResponse<List<LoaiMayLanh>> apiResponse = response.body();
                    loaiMayLanhList=apiResponse.getResult();
                    RcvLoaiMayLanhAdapter adapter=new RcvLoaiMayLanhAdapter(loaiMayLanhList,LoaiMayLanhActivity.this);
                    bd.rcvLML.setAdapter(adapter);
                }else {

                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<LoaiMayLanh>>> call, Throwable t) {

            }
        });
    }
}