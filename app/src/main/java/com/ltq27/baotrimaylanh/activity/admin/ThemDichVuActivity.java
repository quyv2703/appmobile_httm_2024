/*
package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityThemDichVuBinding;
import com.ltq27.baotrimaylanh.model.DichVu;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemDichVuActivity extends AppCompatActivity {
    private ActivityThemDichVuBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityThemDichVuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.edtName.setText("");
        bd.edtPrice.setText("");
        setEvent();
    }

    private void setEvent() {
        bd.btnThemDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = bd.edtName.getText().toString();
                String price = bd.edtPrice.getText().toString();
                Boolean check = true;
                if(bd.radRieng.isChecked()){
                    check=false;
                }
                DichVu dichVu=new DichVu(name,price,check);
                ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
                Call<ApiResponse<DichVu>> call=apiInterface.addDichVu(dichVu);
                call.enqueue(new Callback<ApiResponse<DichVu>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<DichVu>> call, Response<ApiResponse<DichVu>> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ThemDichVuActivity.this, "Thêm dịch vụ thành công", Toast.LENGTH_SHORT).show();
                            bd.edtName.setText("");
                            bd.edtPrice.setText("");
                            bd.radChung.isChecked();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<DichVu>> call, Throwable t) {

                    }
                });
            }
        });
    }
}*/
