package com.ltq27.baotrimaylanh.activity.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.admin.dto.BaoCaoDoanhThu;
import com.ltq27.baotrimaylanh.databinding.ActivityBaoCaoDoanhThuTheoNgayBinding;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaoCaoDoanhThuTheoNgayActivity extends AppCompatActivity {
    private ActivityBaoCaoDoanhThuTheoNgayBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityBaoCaoDoanhThuTheoNgayBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        Intent intent = getIntent();
        String ngayBD=intent.getStringExtra("startDate");
        String ngayKT=intent.getStringExtra("endDate");
        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        Call<BaoCaoDoanhThu> call=apiInterface.layBaoCaoDoanhThuTheoNgay(LocalDate.parse(ngayBD) , LocalDate.parse(ngayKT));
        call.enqueue(new Callback<BaoCaoDoanhThu>() {
            @Override
            public void onResponse(Call<BaoCaoDoanhThu> call, Response<BaoCaoDoanhThu> response) {
                if(response.isSuccessful()){
                    BaoCaoDoanhThu baoCaoDoanhThu=response.body();
                    bd.tvTotalRevenue.setText("Tổng doanh thu từ "+ngayBD+" đến "+ngayKT+":");
                    bd.tvTongDoanhThu.setText(String.valueOf(baoCaoDoanhThu.getTongDoanhThu()+" VND"));
                    bd.tvTongHoaDonDaThanhToan.setText(String.valueOf(baoCaoDoanhThu.getTongHoaDonDaThanhToan()));
                    bd.tvTongHoaDonChuaThanhToan.setText(String.valueOf("Chưa thanh toán: "+baoCaoDoanhThu.getSoHoaDonChuaThanhToan()));
                    bd.tvSoHoaDonHoanThanh.setText(String.valueOf("Đã thanh toán: "+baoCaoDoanhThu.getTongHoaDonDaThanhToan()));
                }
            }

            @Override
            public void onFailure(Call<BaoCaoDoanhThu> call, Throwable t) {

            }
        });
    }
}