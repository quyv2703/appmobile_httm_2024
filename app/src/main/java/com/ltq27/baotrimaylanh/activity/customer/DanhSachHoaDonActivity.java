package com.ltq27.baotrimaylanh.activity.customer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.employee.HoaDonResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityDanhSachHoaDonBinding;
import com.ltq27.baotrimaylanh.recyclerview.RcvDanhSachHoaDonKhachHangAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachHoaDonActivity extends AppCompatActivity {
    private ActivityDanhSachHoaDonBinding bd;
    private List<HoaDonResponse> hoaDonResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityDanhSachHoaDonBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.imgBack.setOnClickListener(v -> finish());
        bd.rcvDanhSachHoaDonKhachHang.setHasFixedSize(true);
        bd.rcvDanhSachHoaDonKhachHang.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        UserSession userSession=new UserSession(this);
        Long id= Long.valueOf(userSession.getUserId());
        Call<List<HoaDonResponse>> call=apiInterface.layDanhSachHoaDon(id);
        call.enqueue(new Callback<List<HoaDonResponse>>() {
            @Override
            public void onResponse(Call<List<HoaDonResponse>> call, Response<List<HoaDonResponse>> response) {
                if (response.isSuccessful()){
                    List<HoaDonResponse> hoaDonResponses=response.body();
                    Collections.sort(hoaDonResponses, new Comparator<HoaDonResponse>() {
                        @Override
                        public int compare(HoaDonResponse o1, HoaDonResponse o2) {
                            return Long.compare(o2.getId(), o1.getId());
                        }
                    });

                    hoaDonResponseList = hoaDonResponses;
                    RcvDanhSachHoaDonKhachHangAdapter adapter = new RcvDanhSachHoaDonKhachHangAdapter(hoaDonResponseList, DanhSachHoaDonActivity.this);
                    bd.rcvDanhSachHoaDonKhachHang.setAdapter(adapter);
                }
                else {
                    Toast.makeText(DanhSachHoaDonActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HoaDonResponse>> call, Throwable t) {

            }
        });
    }
}