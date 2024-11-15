package com.ltq27.baotrimaylanh.activity.admin.capnhatphancong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.admin.ChonPhanCongNhanVienActivity;
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDuocPhanCong;
import com.ltq27.baotrimaylanh.databinding.ActivityCapNhatPhanCongBiTuChoiBinding;
import com.ltq27.baotrimaylanh.model.Employee;
import com.ltq27.baotrimaylanh.recyclerview.RcvDanhSachChonPhanCongNhanVienAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;
import com.ltq27.baotrimaylanh.retrofit2.TrangThaiPhanCong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapNhatPhanCongBiTuChoiActivity extends AppCompatActivity implements ItemClick {
    private ActivityCapNhatPhanCongBiTuChoiBinding bd;
    private List<Employee> employeeList;
    private Long idPhanCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityCapNhatPhanCongBiTuChoiBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvPhanCongNhanVien.setHasFixedSize(true);
        bd.rcvPhanCongNhanVien.setLayoutManager(new LinearLayoutManager(this));
        setEvent();
    }

    private void setEvent() {
        bd.back.setOnClickListener(v -> finish());
        getIntentData();
        loadEmployeeList();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        idPhanCong=intent.getLongExtra("idPhanCong",0);
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
                    RcvDanhSachChonPhanCongNhanVienAdapter adapter = new RcvDanhSachChonPhanCongNhanVienAdapter(employeeList, CapNhatPhanCongBiTuChoiActivity.this, CapNhatPhanCongBiTuChoiActivity.this);
                    bd.rcvPhanCongNhanVien.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CapNhatPhanCongBiTuChoiActivity.this, "Không thể tải danh sách nhân viên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(CapNhatPhanCongBiTuChoiActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(Long id) {
        bd.tvIdChon.setText("ID nhân viên: " + id);
        // update phân công
        bd.btnChon.setOnClickListener(view -> UpdatePhanCong(id));
    }

    private void UpdatePhanCong(Long id) {
      // đây là nhân viên id
      ApiInterface apiInterface=RetrofitClient.getClient().create(ApiInterface.class);
      Call<DanhSachDuocPhanCong> call=apiInterface.capNhatNhanVienPhanCongVaTrangThaiPhanCong(idPhanCong,id);
      call.enqueue(new Callback<DanhSachDuocPhanCong>() {
          @Override
          public void onResponse(Call<DanhSachDuocPhanCong> call, Response<DanhSachDuocPhanCong> response) {
              if (response.isSuccessful()){
                  Toast.makeText(CapNhatPhanCongBiTuChoiActivity.this, "Cập nhật thành công nhân viên có id: "+id+". Với mã trạng thái là: "+ TrangThaiPhanCong.CHO_XAC_NHAN, Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(CapNhatPhanCongBiTuChoiActivity.this, DanhSachPhanCongBiTuChoiActivity.class);
                    startActivity(intent);
                    finish();
              }
              else {
                  Toast.makeText(CapNhatPhanCongBiTuChoiActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onFailure(Call<DanhSachDuocPhanCong> call, Throwable t) {
              Toast.makeText(CapNhatPhanCongBiTuChoiActivity.this, "Cập nhật api bị lỗi", Toast.LENGTH_SHORT).show();
          }
      });
    }
}