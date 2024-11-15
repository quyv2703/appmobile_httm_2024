package com.ltq27.baotrimaylanh.activity.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.admin.dto.BaoCaoDoanhThu;
import com.ltq27.baotrimaylanh.databinding.ActivityChonNgayBaoCaoBinding;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChonNgayBaoCaoActivity extends AppCompatActivity {
    private ActivityChonNgayBaoCaoBinding bd;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityChonNgayBaoCaoBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.back.setOnClickListener(view -> finish());
        bd.tvBD.setOnClickListener(view -> showDatePicker(date -> {
            startDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            bd.tvBD.setText(date);
            bd.tvBD.setTextColor(Color.BLACK);
            bd.tvBD.setTextSize(20);
        }));

        bd.tvKT.setOnClickListener(view -> showDatePicker(date -> {
            endDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
                Toast.makeText(ChonNgayBaoCaoActivity.this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu", Toast.LENGTH_SHORT).show();
            } else {
                bd.tvKT.setText(date);
                bd.tvKT.setTextColor(Color.BLACK);
                bd.tvKT.setTextSize(20);
            }
        }));
        bd.btnGui.setOnClickListener(view -> {
            String ngayBD = bd.tvBD.getText().toString();
            String ngayKT = bd.tvKT.getText().toString();

            if (ngayBD.isEmpty() || ngayKT.isEmpty()) {
                Toast.makeText(ChonNgayBaoCaoActivity.this, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc", Toast.LENGTH_SHORT).show();
            } else {
                ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
                Call<BaoCaoDoanhThu> call=apiInterface.layBaoCaoDoanhThuTheoNgay(LocalDate.parse(ngayBD) , LocalDate.parse(ngayKT));
                call.enqueue(new Callback<BaoCaoDoanhThu>() {
                    @Override
                    public void onResponse(Call<BaoCaoDoanhThu> call, Response<BaoCaoDoanhThu> response) {
                        if(response.isSuccessful()){
                            BaoCaoDoanhThu baoCaoDoanhThu=response.body();
                            if(baoCaoDoanhThu.getTongDoanhThu()==null){
                                bd.tvTongDoanhThu.setText("Không có dữ liệu");
                            }
                            bd.tvTotalRevenue.setText("Tổng doanh thu từ "+startDate+" đến "+endDate+":");
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
    });
}
    private void showDatePicker(DateSelectedCallback callback) {
        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker().build();
        picker.addOnPositiveButtonClickListener(selection -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String date = sdf.format(calendar.getTime());
            callback.onDateSelected(date);
        });
        picker.show(getSupportFragmentManager(), picker.toString());
    }

    interface DateSelectedCallback {
        void onDateSelected(String date);
    }
}