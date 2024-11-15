package com.ltq27.baotrimaylanh.activity.employee;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.activity.customer.UserSession;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDuocPhanCong;

import com.ltq27.baotrimaylanh.databinding.ActivityDanhSachCongViecBinding;
import com.ltq27.baotrimaylanh.recyclerview.RcvDanhSachCongViecAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCongViecActivity extends AppCompatActivity
        implements RcvDanhSachCongViecAdapter.OnUpdateButtonClickListener, RcvDanhSachCongViecAdapter.OnItemClickListener {

    private ActivityDanhSachCongViecBinding bd;
    private List<DanhSachDuocPhanCong> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityDanhSachCongViecBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvDanhSachCongViec.setHasFixedSize(true);
        bd.rcvDanhSachCongViec.setLayoutManager(new LinearLayoutManager(this));
        setEvent();
    }

    private void setEvent() {
        bd.imgBack.setOnClickListener(v -> finish());

        UserSession userSession = new UserSession(this);
        Long idNV = Long.valueOf(userSession.getUserId());
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<DanhSachDuocPhanCong>> call = apiInterface.layDanhSachDuocPhanCong(idNV);
        call.enqueue(new Callback<List<DanhSachDuocPhanCong>>() {
            @Override
            public void onResponse(Call<List<DanhSachDuocPhanCong>> call, Response<List<DanhSachDuocPhanCong>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DanhSachDuocPhanCong> danhSachDuocPhanCongList = response.body();

                    // Sắp xếp danh sách theo trạng thái và sau đó theo ID từ lớn xuống nhỏ
                    Collections.sort(danhSachDuocPhanCongList, new Comparator<DanhSachDuocPhanCong>() {
                        @Override
                        public int compare(DanhSachDuocPhanCong o1, DanhSachDuocPhanCong o2) {
                            // So sánh trạng thái trước
                            int trangThaiComparison = o1.getTrangThai().compareTo(o2.getTrangThai());
                            if (trangThaiComparison != 0) {
                                return trangThaiComparison;
                            }
                            // Nếu trạng thái giống nhau, so sánh theo ID từ lớn đến nhỏ
                            return Long.compare(o2.getId(), o1.getId());
                        }
                    });
                    list = danhSachDuocPhanCongList;
                    RcvDanhSachCongViecAdapter adapter = new RcvDanhSachCongViecAdapter(list,
                            DanhSachCongViecActivity.this, DanhSachCongViecActivity.this);
                    bd.rcvDanhSachCongViec.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DanhSachCongViecActivity.this, "Lỗi không lấy được danh sách phân công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DanhSachDuocPhanCong>> call, Throwable t) {
                Toast.makeText(DanhSachCongViecActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(Long id) {
        Intent intent = new Intent(DanhSachCongViecActivity.this, CapNhatTrangThaiPhanCongActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void onUpdateButtonClick(Long idHoaDon, Button updateButton) {
        showConfirmationDialog(idHoaDon, updateButton);
    }

    private void capNhatHoaDon(Long idHoaDon, Button updateButton) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<ApiResponse<HoaDonResponse>> call = apiInterface.capNhatHoaDon(idHoaDon);
        call.enqueue(new Callback<ApiResponse<HoaDonResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<HoaDonResponse>> call, Response<ApiResponse<HoaDonResponse>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DanhSachCongViecActivity.this, "Xác nhận thanh toán thành công", Toast.LENGTH_SHORT).show();
                    updateButton.setEnabled(false); // Disable the button
                    updateButton.setText("Đã thanh toán");

                } else {
                    Toast.makeText(DanhSachCongViecActivity.this, "Cập nhật hóa đơn không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<HoaDonResponse>> call, Throwable t) {
                Toast.makeText(DanhSachCongViecActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showConfirmationDialog(Long idHoaDon, Button updateButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn xác nhận thanh toán cho hóa đơn này không?");
        builder.setPositiveButton("Có", (dialog, which) -> capNhatHoaDon(idHoaDon, updateButton));
        builder.setNegativeButton("Không", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
