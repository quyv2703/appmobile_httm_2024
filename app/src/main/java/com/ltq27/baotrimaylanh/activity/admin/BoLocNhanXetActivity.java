package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.ltq27.baotrimaylanh.activity.customer.NhanXetActivity;
import com.ltq27.baotrimaylanh.apiresponse.NhanXetResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityBoLocNhanXetBinding;
import com.ltq27.baotrimaylanh.recyclerview.RcvNhanXetAdapter;
import com.ltq27.baotrimaylanh.recyclerview.admin.RcvNhanXet_Admin_Adapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoLocNhanXetActivity extends AppCompatActivity {
    private ActivityBoLocNhanXetBinding bd;
    private List<NhanXetResponse> nhanXetResponseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd=ActivityBoLocNhanXetBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        setEvent();
        bd.rcvNhanXetAdmin.setHasFixedSize(true);
        bd.rcvNhanXetAdmin.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setEvent() {
        bd.imgBack.setOnClickListener(view -> finish());

        // Xử lý sự kiện cho nhãn Tích Cực
        bd.btnTichCuc.setOnClickListener(view -> fetchNhanXetByLabel("tích cực"));

        // Xử lý sự kiện cho nhãn Tiêu Cực
        bd.btnTieuCuc.setOnClickListener(view -> fetchNhanXetByLabel("tiêu cực"));

        // Xử lý sự kiện cho nhãn Trung Tính
        bd.btnTrungTinh.setOnClickListener(view -> fetchNhanXetByLabel("trung tính"));
    }
    private void fetchNhanXetByLabel(String label) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<NhanXetResponse>> call = apiInterface.boLocNhanXet(label);

        call.enqueue(new Callback<List<NhanXetResponse>>() {
            @Override
            public void onResponse(Call<List<NhanXetResponse>> call, Response<List<NhanXetResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    nhanXetResponseList = response.body();
                    RcvNhanXet_Admin_Adapter rcvNhanXetAdapter = new RcvNhanXet_Admin_Adapter(nhanXetResponseList, BoLocNhanXetActivity.this);
                    bd.rcvNhanXetAdmin.setAdapter(rcvNhanXetAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NhanXetResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });
    }

}