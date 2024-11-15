package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.admin.dto.BaoCaoDoanhThu;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaoCaoDoanhThuActivity extends AppCompatActivity {


    private TextView tvTotalRevenueValue;

    private TextView tvTotalPaidInvoicesValue;
    private TextView tvPendingInvoices;

    private TextView tvCompletedInvoices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_doanh_thu);

        // Khởi tạo các TextView

        tvTotalRevenueValue = findViewById(R.id.tvTotalRevenueValue);

        tvTotalPaidInvoicesValue = findViewById(R.id.tvTotalPaidInvoicesValue);
        tvPendingInvoices = findViewById(R.id.tvPendingInvoices);

        tvCompletedInvoices = findViewById(R.id.tvCompletedInvoices);

        // Tạo Retrofit API service
        ApiInterface apiService = RetrofitClient.getClient().create(ApiInterface.class);

        // Gọi API để lấy báo cáo doanh thu
        Call<BaoCaoDoanhThu> call = apiService.layBaoCaoDoanhThu();

        call.enqueue(new Callback<BaoCaoDoanhThu>() {
            @Override
            public void onResponse(Call<BaoCaoDoanhThu> call, Response<BaoCaoDoanhThu> response) {
                if (response.isSuccessful()) {
                    BaoCaoDoanhThu baoCao = response.body();

                    // Cập nhật các TextView với dữ liệu từ API
                    tvTotalRevenueValue.setText(baoCao.getTongDoanhThu() + " VNĐ");
                    tvTotalPaidInvoicesValue.setText(String.valueOf(baoCao.getTongHoaDonDaThanhToan()));
                    tvPendingInvoices.setText("Chờ xác nhận: " + baoCao.getSoHoaDonChuaThanhToan());

                    tvCompletedInvoices.setText("Hoàn thành: " + baoCao.getTongHoaDonDaThanhToan()); // Cần thêm phương thức trong BaoCaoDoanhThu để lấy số hóa đơn hoàn thành
                } else {
                    Toast.makeText(BaoCaoDoanhThuActivity.this, "Lỗi khi lấy báo cáo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaoCaoDoanhThu> call, Throwable t) {
                Toast.makeText(BaoCaoDoanhThuActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}