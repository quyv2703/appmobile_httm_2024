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
import com.ltq27.baotrimaylanh.activity.customer.dto.DatLichStatusDTO;
import com.ltq27.baotrimaylanh.databinding.ActivityCustomerXemTrangThaiDatLichBinding;
import com.ltq27.baotrimaylanh.recyclerview.khachhang.RcvDatLichStatusAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerXemTrangThaiDatLichActivity extends AppCompatActivity {

    private ActivityCustomerXemTrangThaiDatLichBinding bd;
    private List<DatLichStatusDTO> datLichStatusDTOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityCustomerXemTrangThaiDatLichBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvTrangThaiDatLich.setHasFixedSize(true);
        bd.rcvTrangThaiDatLich.setLayoutManager(new LinearLayoutManager(this));
        setEvent();
    }

    private void setEvent() {
        bd.back.setOnClickListener(v -> finish());
        UserSession userSession = new UserSession(this);
        Long maKhachHang = Long.valueOf(userSession.getUserId());
        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<DatLichStatusDTO>> call=apiInterface.layDanhSachDonDatLichKhachHang(maKhachHang);
        call.enqueue(new Callback<List<DatLichStatusDTO>>() {
            @Override
            public void onResponse(Call<List<DatLichStatusDTO>> call, Response<List<DatLichStatusDTO>> response) {
                if(response.isSuccessful()){
                    List<DatLichStatusDTO> list=response.body();
                    datLichStatusDTOList=list;
                    RcvDatLichStatusAdapter rcvDatLichStatusAdapter=new RcvDatLichStatusAdapter(datLichStatusDTOList,CustomerXemTrangThaiDatLichActivity.this);
                    bd.rcvTrangThaiDatLich.setAdapter(rcvDatLichStatusAdapter);
                }else {
                    Toast.makeText(CustomerXemTrangThaiDatLichActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DatLichStatusDTO>> call, Throwable t) {

            }
        });
    }
}