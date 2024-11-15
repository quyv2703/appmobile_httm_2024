package com.ltq27.baotrimaylanh.activity.admin;

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
import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityGoiDichVuBinding;
import com.ltq27.baotrimaylanh.model.GoiDichVu;

import com.ltq27.baotrimaylanh.recyclerview.RcvGoiDichVuAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoiDichVuActivity extends AppCompatActivity {
    private List<GoiDichVuResponse> goiDichVuList;
    private ActivityGoiDichVuBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityGoiDichVuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvGoiDV.setHasFixedSize(true);
        bd.rcvGoiDV.setLayoutManager(new LinearLayoutManager(this));
        setEvent();

    }

    private void setEvent() {
        bd.back.setOnClickListener(v->finish());
        bd.btnThemGDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoiDichVuActivity.this, ThemGoiDichVuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<List<GoiDichVuResponse>>> call=apiInterface.getListGoiDichVuQuanLi();
        call.enqueue(new Callback<ApiResponse<List<GoiDichVuResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<GoiDichVuResponse>>> call, Response<ApiResponse<List<GoiDichVuResponse>>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<List<GoiDichVuResponse>> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getResult() != null) {
                        goiDichVuList = apiResponse.getResult();
                        RcvGoiDichVuAdapter rcvGoiDichVuAdapter = new RcvGoiDichVuAdapter(goiDichVuList, GoiDichVuActivity.this);
                        bd.rcvGoiDV.setAdapter(rcvGoiDichVuAdapter);
                    } else {
                        Toast.makeText(GoiDichVuActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GoiDichVuActivity.this, "Lỗi: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<GoiDichVuResponse>>> call, Throwable t) {
                Toast.makeText(GoiDichVuActivity.this, "Gọi api thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}