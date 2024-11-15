package com.ltq27.baotrimaylanh.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ltq27.baotrimaylanh.apiresponse.DanhSachDuocPhanCong;
import com.ltq27.baotrimaylanh.databinding.ActivityCapNhatTrangThaiPhanCongBinding;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;
import com.ltq27.baotrimaylanh.retrofit2.TrangThaiPhanCong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapNhatTrangThaiPhanCongActivity extends AppCompatActivity  {
    private ActivityCapNhatTrangThaiPhanCongBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd=ActivityCapNhatTrangThaiPhanCongBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        Intent intent=getIntent();

        Long id=intent.getLongExtra("id",0);
        bd.imgBack.setOnClickListener(view -> finish());
        bd.btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangThaiDuyetDTO trangThaiDuyetDTO=new TrangThaiDuyetDTO();
                String trangThai="";
                if(bd.radDongY.isChecked()){
                    trangThai=TrangThaiPhanCong.DA_DONG_Y.toString();
                } else if(bd.radTuChoi.isChecked()){
                    trangThai=TrangThaiPhanCong.TU_CHOI.toString();
                } else if(bd.radDangThucHien.isChecked()){
                    trangThai=TrangThaiPhanCong.DANG_THUC_HIEN.toString();
                } else {
                    trangThai=TrangThaiPhanCong.HOAN_THANH.toString();
                }
                trangThaiDuyetDTO.setTrangThai(trangThai);
                ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
                Call<DanhSachDuocPhanCong> call=apiInterface.capNhatTrangThaiPhanCong(id,trangThaiDuyetDTO);
                String finalTrangThai = trangThai;

                call.enqueue(new Callback<DanhSachDuocPhanCong>() {
                    @Override
                    public void onResponse(Call<DanhSachDuocPhanCong> call, Response<DanhSachDuocPhanCong> response) {
                        if(response.isSuccessful()){

                            Toast.makeText(CapNhatTrangThaiPhanCongActivity.this,
                                    "Cập nhật thành công trạng thái "+ finalTrangThai, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(CapNhatTrangThaiPhanCongActivity.this, DanhSachCongViecActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(CapNhatTrangThaiPhanCongActivity.this,
                                    "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DanhSachDuocPhanCong> call, Throwable t) {

                    }
                });
            }
        });
    }
}