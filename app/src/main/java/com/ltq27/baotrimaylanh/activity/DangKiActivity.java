package com.ltq27.baotrimaylanh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.customer.dto.DangKiDto;
import com.ltq27.baotrimaylanh.databinding.ActivityDangKiBinding;
import com.ltq27.baotrimaylanh.model.Customer;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKiActivity extends AppCompatActivity {
    private ActivityDangKiBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd=ActivityDangKiBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.backToLogin.setOnClickListener(v->finish());
        bd.buttonRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ các trường nhập liệu
                String name = bd.edtName.getText().toString().trim();
                String username = bd.edtUsername.getText().toString().trim();
                String password = bd.edtPassword.getText().toString().trim();
                String phoneNumber = bd.edtSdt.getText().toString().trim();
                String address = bd.edtDiaChi.getText().toString().trim();
                String birthYear = bd.edtNamSinh.getText().toString().trim();

                // Kiểm tra các trường nhập
                if (name.isEmpty() || username.isEmpty() || password.isEmpty() ||
                        phoneNumber.isEmpty() || address.isEmpty() || birthYear.isEmpty()) {
                    Toast.makeText(DangKiActivity.this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra tên người dùng
                if (username.isEmpty()) {
                    Toast.makeText(DangKiActivity.this, "Tên người dùng không thể chỉ chứa khoảng trắng.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra giới tính
                int selectedRadioButtonId = bd.radioG.getCheckedRadioButtonId();
                if (selectedRadioButtonId == -1) {
                    Toast.makeText(DangKiActivity.this, "Vui lòng chọn giới tính.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng DangKiDto và gán giá trị
                DangKiDto dangKiDto = new DangKiDto();
                dangKiDto.setName(name);
                dangKiDto.setUsername(username);
                dangKiDto.setPassword(password);
                dangKiDto.setPhoneNumber(phoneNumber);
                dangKiDto.setAddress(address);
                dangKiDto.setBirthYear(birthYear);

                // Xác định giới tính
                if (selectedRadioButtonId == bd.radNam.getId()) {
                    dangKiDto.setGender("Nam");
                } else {
                    dangKiDto.setGender("Nữ");
                }

                // Gửi yêu cầu đăng ký
                ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
                Call<Customer> call = apiInterface.taoTaiKhoanKhachHang(dangKiDto);
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DangKiActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            // Xóa dữ liệu các trường nhập liệu
                            bd.edtUsername.setText("");
                            bd.edtPassword.setText("");
                            bd.edtName.setText("");
                            bd.edtSdt.setText("");
                            bd.edtDiaChi.setText("");
                            bd.edtNamSinh.setText("");
                        } else {
                            Toast.makeText(DangKiActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Toast.makeText(DangKiActivity.this, "Lỗi chương trình", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}