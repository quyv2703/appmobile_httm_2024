package com.ltq27.baotrimaylanh.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityThemGoiDichVuBinding;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemGoiDichVuActivity extends AppCompatActivity {
    private ActivityThemGoiDichVuBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityThemGoiDichVuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        setEvent();
    }

    private void setEvent() {
        bd.back.setOnClickListener(v -> finish());
        bd.btnThem.setOnClickListener(v -> {
            String name = bd.edtName.getText().toString().trim();
            String moTa = bd.edtMota.getText().toString().trim();

            // Kiểm tra điều kiện
            if (name.isEmpty()) {
                Toast.makeText(ThemGoiDichVuActivity.this, "Tên gói dịch vụ không được để trống", Toast.LENGTH_SHORT).show();
                return;
            }

            if (moTa.isEmpty()) {
                Toast.makeText(ThemGoiDichVuActivity.this, "Mô tả không được để trống", Toast.LENGTH_SHORT).show();
                return;
            }

            GoiDichVuResponse goiDichVu = new GoiDichVuResponse();
            goiDichVu.setName(name);
            goiDichVu.setDescription(moTa);

            ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
            Call<ApiResponse<GoiDichVuResponse>> call = apiInterface.taoGoiDichVu(goiDichVu);
            call.enqueue(new Callback<ApiResponse<GoiDichVuResponse>>() {
                @Override
                public void onResponse(Call<ApiResponse<GoiDichVuResponse>> call, Response<ApiResponse<GoiDichVuResponse>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Intent intent = new Intent(ThemGoiDichVuActivity.this, GoiDichVuActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(ThemGoiDichVuActivity.this, "Thêm gói dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ThemGoiDichVuActivity.this, "Thêm gói dịch vụ không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<GoiDichVuResponse>> call, Throwable t) {
                    Toast.makeText(ThemGoiDichVuActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
