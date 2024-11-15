package com.ltq27.baotrimaylanh.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.activity.customer.UserSession;
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDonDatLich;
import com.ltq27.baotrimaylanh.apiresponse.TrangThaiDuyetDTO;
import com.ltq27.baotrimaylanh.databinding.ActivityDanhSachDatLichBinding;
import com.ltq27.baotrimaylanh.model.ThongTinDatLich;
import com.ltq27.baotrimaylanh.recyclerview.RcvDanhSachDatLichAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDatLichActivity extends AppCompatActivity implements RcvDanhSachDatLichAdapter.OnItemClickListener {
    private ActivityDanhSachDatLichBinding binding;
    private List<DanhSachDonDatLich> danhSachDonDatLich;
    private ApiInterface apiInterface;
    private RcvDanhSachDatLichAdapter adapter;

    // Constants for approval status
    private static final String STATUS_PENDING = "1";
    private static final String STATUS_APPROVED = "2";
    private static final String STATUS_REJECTED = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDanhSachDatLichBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
        initApiInterface();
        loadDanhSachDatLich();

        binding.imgBack.setOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        binding.rcvDanhSachDatLich.setHasFixedSize(true);
        binding.rcvDanhSachDatLich.setLayoutManager(new LinearLayoutManager(this));
        danhSachDonDatLich = new ArrayList<>();
        adapter = new RcvDanhSachDatLichAdapter(this, danhSachDonDatLich, this);
        binding.rcvDanhSachDatLich.setAdapter(adapter);
    }

    private void initApiInterface() {
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
    }

    private void loadDanhSachDatLich() {
        Call<List<DanhSachDonDatLich>> call = apiInterface.layDanhSachDonDatLich();
        call.enqueue(new Callback<List<DanhSachDonDatLich>>() {
            @Override
            public void onResponse(Call<List<DanhSachDonDatLich>> call, Response<List<DanhSachDonDatLich>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    danhSachDonDatLich.clear();
                    danhSachDonDatLich.addAll(response.body());
                    Collections.sort(danhSachDonDatLich, (o1, o2) -> Long.compare(o2.getId(), o1.getId()));
                    adapter.notifyDataSetChanged();
                } else {
                    showToast("Không thể tải danh sách đặt lịch");
                }
            }

            @Override
            public void onFailure(Call<List<DanhSachDonDatLich>> call, Throwable t) {
                showToast("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

    @Override
    public void onDongY(DanhSachDonDatLich danhSachDonDatLich) {
        updateIdManagerAndStatus(danhSachDonDatLich, STATUS_APPROVED);
    }

    @Override
    public void onTuChoi(DanhSachDonDatLich danhSachDonDatLich) {
        updateIdManagerAndStatus(danhSachDonDatLich, STATUS_REJECTED);
    }

    private void updateIdManagerAndStatus(DanhSachDonDatLich danhSachDonDatLich, String trangThai) {
        UserSession userSession = new UserSession(this);
        Long maNhanVien = Long.valueOf(userSession.getUserId());
        Call<ThongTinDatLich> call = apiInterface.updateIdManager(danhSachDonDatLich.getId(), maNhanVien);
        call.enqueue(new Callback<ThongTinDatLich>() {
            @Override
            public void onResponse(Call<ThongTinDatLich> call, Response<ThongTinDatLich> response) {
                if (response.isSuccessful()) {
                    showToast("Thêm thành công mã quản lí");
                    updateTrangThaiDuyet(danhSachDonDatLich, trangThai);
                } else {
                    showToast("Lỗi khi thêm mã quản lí duyệt");
                }
            }

            @Override
            public void onFailure(Call<ThongTinDatLich> call, Throwable t) {
                showToast("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

    private void updateTrangThaiDuyet(DanhSachDonDatLich danhSachDonDatLich, String trangThai) {
        TrangThaiDuyetDTO trangThaiDuyetDTO = new TrangThaiDuyetDTO();
        trangThaiDuyetDTO.setTrangThaiDuyet(Integer.parseInt(trangThai));

        Call<TrangThaiDuyetDTO> call = apiInterface.capNhatTrangThai(danhSachDonDatLich.getId(), trangThaiDuyetDTO);
        call.enqueue(new Callback<TrangThaiDuyetDTO>() {
            @Override
            public void onResponse(Call<TrangThaiDuyetDTO> call, Response<TrangThaiDuyetDTO> response) {
                if (response.isSuccessful()) {
                    danhSachDonDatLich.setTrangThaiDuyet(trangThai);
                    adapter.notifyDataSetChanged();
                    if (STATUS_APPROVED.equals(trangThai)) {
                        showToast("Đã đồng ý");
                        navigateToChonPhanCongNhanVienActivity(danhSachDonDatLich.getId());
                    } else if (STATUS_REJECTED.equals(trangThai)) {
                        showToast("Đã từ chối");

                    }
                } else {
                    showToast("Lỗi khi cập nhật trạng thái duyệt");
                }
            }

            @Override
            public void onFailure(Call<TrangThaiDuyetDTO> call, Throwable t) {
                showToast("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
    private void navigateToChonPhanCongNhanVienActivity(Long idDonDatLich) {
        Intent intent = new Intent(this, ChonPhanCongNhanVienActivity.class);
        intent.putExtra("idDonDatLich", idDonDatLich);
        startActivity(intent);
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}