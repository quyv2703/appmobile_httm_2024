package com.ltq27.baotrimaylanh.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityDatLichBinding;
import com.ltq27.baotrimaylanh.model.DatLichDto;
import com.ltq27.baotrimaylanh.model.LoaiMayLanh;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatLichActivity extends AppCompatActivity {
    private ActivityDatLichBinding binding;
    private List<LoaiMayLanh> loaiMayLanhList;
    private Long selectedLoaiMayLanhId;
    private Long idDonGia;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatLichBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        Intent intent = getIntent();
        Long idGDV = intent.getLongExtra("id", -1);
        if (idGDV == -1) {
            Toast.makeText(this, "Không tìm thấy ID gói dịch vụ", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        binding.imgBack.setOnClickListener(view -> finish());
        setUpViews();
        setUpListeners(idGDV);
        layDanhSachLoaiMayLanh(idGDV);
    }

    private void setUpViews() {
        binding.tvNgay.setVisibility(View.GONE);
    }

    private void setUpListeners(Long idGDV) {
        binding.btnNgay.setOnClickListener(view -> showMaterialDatePicker());

        binding.btnDatLich.setOnClickListener(view -> {
            if (validateInputs()) {
                taoDonDatLich(idDonGia);
            }
        });

        binding.spinnerLoaiMayLanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                LoaiMayLanh selectedLoaiMayLanh = (LoaiMayLanh) adapterView.getItemAtPosition(position);
                selectedLoaiMayLanhId = selectedLoaiMayLanh.getId();
                layIdDonGia(idGDV, selectedLoaiMayLanhId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedLoaiMayLanhId = null;
            }
        });
    }

    private boolean validateInputs() {
        if (binding.tvNgay.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn ngày thực hiện", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.edtDiaChi.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (selectedLoaiMayLanhId == null) {
            Toast.makeText(this, "Vui lòng chọn loại máy lạnh", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (idDonGia == null) {
            Toast.makeText(this, "Không thể lấy thông tin đơn giá", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void layDanhSachLoaiMayLanh(Long idGDV) {
        Call<ApiResponse<List<LoaiMayLanh>>> call = apiInterface.danhSachLoaiMayLanh();
        call.enqueue(new Callback<ApiResponse<List<LoaiMayLanh>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<LoaiMayLanh>>> call, Response<ApiResponse<List<LoaiMayLanh>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loaiMayLanhList = response.body().getResult();
                    setupLoaiMayLanhSpinner();
                } else {
                    Toast.makeText(DatLichActivity.this, "Không thể lấy danh sách loại máy lạnh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<LoaiMayLanh>>> call, Throwable t) {
                Toast.makeText(DatLichActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupLoaiMayLanhSpinner() {
        ArrayAdapter<LoaiMayLanh> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loaiMayLanhList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerLoaiMayLanh.setAdapter(adapter);
    }

    private void layIdDonGia(Long idGDV, Long selectedLoaiMayLanhId) {
        Call<Long> call = apiInterface.layIdDonGia(idGDV, selectedLoaiMayLanhId);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.isSuccessful() && response.body() != null) {
                    idDonGia = response.body();
                } else {
                    Toast.makeText(DatLichActivity.this, "Không thể lấy thông tin đơn giá", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Toast.makeText(DatLichActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void taoDonDatLich(Long idDonGia) {
        UserSession userSession = new UserSession(this);
        Long customerId = Long.valueOf(userSession.getUserId());
        String ngayThucHien = binding.tvNgay.getText().toString();
        String diaChi = binding.edtDiaChi.getText().toString();
        DatLichDto datLichDto = new DatLichDto(customerId, idDonGia, ngayThucHien, diaChi);
        Call<DatLichDto> call = apiInterface.taoDonDatLich(datLichDto);
        call.enqueue(new Callback<DatLichDto>() {
            @Override
            public void onResponse(Call<DatLichDto> call, Response<DatLichDto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DatLichActivity.this, "Tạo đơn đặt lịch thành công", Toast.LENGTH_SHORT).show();
                    finish();  // Kết thúc activity sau khi đặt lịch thành công
                } else {
                    Toast.makeText(DatLichActivity.this, "Tạo đơn đặt lịch thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DatLichDto> call, Throwable t) {
                Toast.makeText(DatLichActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showMaterialDatePicker() {
        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker().build();

        picker.addOnPositiveButtonClickListener(selection -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String date = sdf.format(calendar.getTime());
            binding.tvNgay.setVisibility(View.VISIBLE);
            binding.tvNgay.setText(date);
        });

        picker.show(getSupportFragmentManager(), picker.toString());
    }
}