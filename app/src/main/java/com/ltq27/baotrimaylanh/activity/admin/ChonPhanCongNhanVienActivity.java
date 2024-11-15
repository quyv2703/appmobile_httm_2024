package com.ltq27.baotrimaylanh.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.databinding.ActivityChonPhanCongNhanVienBinding;
import com.ltq27.baotrimaylanh.model.Employee;
import com.ltq27.baotrimaylanh.model.PhanCong;
import com.ltq27.baotrimaylanh.recyclerview.RcvDanhSachChonPhanCongNhanVienAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonPhanCongNhanVienActivity extends AppCompatActivity implements ItemClick {
    private List<Employee> employeeList;
    private ActivityChonPhanCongNhanVienBinding binding;
    private Long idDonDatLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChonPhanCongNhanVienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
        getIntentData();
        loadEmployeeList();
    }

    private void setupRecyclerView() {
        binding.rcvPhanCongNhanVien.setHasFixedSize(true);
        binding.rcvPhanCongNhanVien.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getIntentData() {
        Intent intent = getIntent();
        idDonDatLich = intent.getLongExtra("idDonDatLich", 0);
    }

    private void loadEmployeeList() {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<Employee>> call = apiInterface.danhSachNhanVienPhanCong(2L);
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Employee> list = response.body();
                    employeeList = list;
                    RcvDanhSachChonPhanCongNhanVienAdapter adapter = new RcvDanhSachChonPhanCongNhanVienAdapter(employeeList, ChonPhanCongNhanVienActivity.this, ChonPhanCongNhanVienActivity.this);
                    binding.rcvPhanCongNhanVien.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ChonPhanCongNhanVienActivity.this, "Không thể tải danh sách nhân viên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ChonPhanCongNhanVienActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(Long id) {
        binding.tvIdChon.setText("ID nhân viên: " + id);
        binding.btnChon.setOnClickListener(view -> createPhanCong(id));
    }

    private void createPhanCong(Long employeeId) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        PhanCong phanCong = new PhanCong();
        phanCong.setMaDatLich(idDonDatLich);
        phanCong.setMaNhanVien(employeeId);

        Call<PhanCong> call = apiInterface.taoPhanCong(phanCong);
        call.enqueue(new Callback<PhanCong>() {
            @Override
            public void onResponse(Call<PhanCong> call, Response<PhanCong> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChonPhanCongNhanVienActivity.this, "Tạo phân công thành công", Toast.LENGTH_SHORT).show();
                    navigateToDanhSachDatLichActivity();
                } else {
                    Toast.makeText(ChonPhanCongNhanVienActivity.this, "Tạo phân công thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PhanCong> call, Throwable t) {
                Toast.makeText(ChonPhanCongNhanVienActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToDanhSachDatLichActivity() {
        Intent intent = new Intent(ChonPhanCongNhanVienActivity.this, DanhSachDatLichActivity.class);
        startActivity(intent);
        finish();
    }


}