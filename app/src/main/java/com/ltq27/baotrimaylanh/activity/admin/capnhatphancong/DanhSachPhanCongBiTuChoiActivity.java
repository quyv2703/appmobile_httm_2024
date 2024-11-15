package com.ltq27.baotrimaylanh.activity.admin.capnhatphancong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.apiresponse.DanhSachDuocPhanCong;
import com.ltq27.baotrimaylanh.databinding.ActivityDanhSachPhanCongBiTuChoiBinding;
import com.ltq27.baotrimaylanh.recyclerview.admin.RcvDanhSachPhanCongBiTuChoiAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;
import com.ltq27.baotrimaylanh.retrofit2.TrangThaiPhanCong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPhanCongBiTuChoiActivity extends AppCompatActivity implements RcvDanhSachPhanCongBiTuChoiAdapter.onItemCapNhatPhanCongListener {
    private ActivityDanhSachPhanCongBiTuChoiBinding binding;
    private List<DanhSachDuocPhanCong> danhSachDuocPhanCongList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDanhSachPhanCongBiTuChoiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rcvDanhSachPhanCongBiTuChoi.setHasFixedSize(true);
        binding.rcvDanhSachPhanCongBiTuChoi.setLayoutManager(new LinearLayoutManager(this));
        setEvent();
    }

    private void setEvent() {
        binding.back.setOnClickListener(v -> finish());


        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<DanhSachDuocPhanCong>> call=apiInterface.layDanhSachPhanCongBiTuChoi(TrangThaiPhanCong.TU_CHOI);
        call.enqueue(new Callback<List<DanhSachDuocPhanCong>>() {
            @Override
            public void onResponse(Call<List<DanhSachDuocPhanCong>> call, Response<List<DanhSachDuocPhanCong>> response) {
                if (response.isSuccessful()){
                    danhSachDuocPhanCongList=response.body();
                    if(danhSachDuocPhanCongList.isEmpty()){
                        Toast.makeText(DanhSachPhanCongBiTuChoiActivity.this, "Không có dữ liệu nào có trạng thái là "+TrangThaiPhanCong.TU_CHOI, Toast.LENGTH_SHORT).show();
                    }
                    RcvDanhSachPhanCongBiTuChoiAdapter rcvDanhSachPhanCongBiTuChoiAdapter=
                            new RcvDanhSachPhanCongBiTuChoiAdapter(danhSachDuocPhanCongList,DanhSachPhanCongBiTuChoiActivity.this,DanhSachPhanCongBiTuChoiActivity.this);
                    binding.rcvDanhSachPhanCongBiTuChoi.setAdapter(rcvDanhSachPhanCongBiTuChoiAdapter);
                }
                else {
                    Toast.makeText(DanhSachPhanCongBiTuChoiActivity.this, "Lỗi lấy danh sách", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DanhSachDuocPhanCong>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCapNhatPhanCong(DanhSachDuocPhanCong phanCong) {
        Intent intent=new Intent(DanhSachPhanCongBiTuChoiActivity.this,CapNhatPhanCongBiTuChoiActivity.class);
        intent.putExtra("idPhanCong",phanCong.getId());
        startActivity(intent);
        finish();
    }
}