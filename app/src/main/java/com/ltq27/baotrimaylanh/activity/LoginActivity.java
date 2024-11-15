package com.ltq27.baotrimaylanh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ltq27.baotrimaylanh.activity.admin.AdminMainActivity;
import com.ltq27.baotrimaylanh.activity.customer.CustomerMainWithBottomNavActivity;
import com.ltq27.baotrimaylanh.activity.customer.dto.CustomerManager;
import com.ltq27.baotrimaylanh.activity.customer.UserSession;
import com.ltq27.baotrimaylanh.activity.customer.dto.CustomerDTO;
import com.ltq27.baotrimaylanh.activity.employee.EmployeeActivity;
import com.ltq27.baotrimaylanh.apiresponse.LoginResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityLoginBinding;
import com.ltq27.baotrimaylanh.model.Login;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);

        setUpListeners();
    }

    private void setUpListeners() {
        binding.textViewDangKi.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, DangKiActivity.class)));

        binding.buttonLogin.setOnClickListener(view -> attemptLogin());
    }

    private void attemptLogin() {
        String username = binding.edtUsername.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.buttonLogin.setEnabled(false);  // Disable button to prevent multiple clicks
        Login login = new Login(username, password);

        apiInterface.login(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                binding.buttonLogin.setEnabled(true);  // Re-enable button
                if (response.isSuccessful() && response.body() != null) {
                    handleLoginResponse(response.body(), username);
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                binding.buttonLogin.setEnabled(true);  // Re-enable button
                Toast.makeText(getApplicationContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLoginResponse(LoginResponse loginResponse, String username) {
        if ("Login successful".equals(loginResponse.getMessage())) {
            Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
            String role = loginResponse.getRole().getName();
            fetchUserIdBasedOnRole(username, role);
            fetchUserInforBasedOnUsername(username, role);
        } else {
            Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchUserInforBasedOnUsername(String username,String role) {

        if ("ADMIN".equals(role) || "EMPLOYEE".equals(role)) {
            Call<CustomerDTO> call1 = apiInterface.layThongTinNhanVien(username);
            call1.enqueue(new Callback<CustomerDTO>() {
                @Override
                public void onResponse(Call<CustomerDTO> call, Response<CustomerDTO> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        CustomerDTO customerDTO = response.body();
                        CustomerManager.getInstance().setUser(customerDTO);
                    }
                }

                @Override
                public void onFailure(Call<CustomerDTO> call, Throwable t) {

                }
            });
        } else if ("CUSTOMER".equals(role)) {
            Call<CustomerDTO> call = apiInterface.layThongTinKhachHang(username);
            call.enqueue(new Callback<CustomerDTO>() {
                @Override
                public void onResponse(Call<CustomerDTO> call, Response<CustomerDTO> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        CustomerDTO customerDTO = response.body();
                        CustomerManager.getInstance().setUser(customerDTO);
                    }
                }

                @Override
                public void onFailure(Call<CustomerDTO> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Unknown role", Toast.LENGTH_SHORT).show();

        }

    }

    private void fetchUserIdBasedOnRole(String username, String role) {
        Call<Long> call;
        if ("ADMIN".equals(role) || "EMPLOYEE".equals(role)) {
            call = apiInterface.getEmployeeIdByUsername(username);
        } else if ("CUSTOMER".equals(role)) {
            call = apiInterface.getCustomerIdByUsername(username);
        } else {
            Toast.makeText(getApplicationContext(), "Unknown role", Toast.LENGTH_SHORT).show();
            return;
        }

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveUserSessionAndNavigate(response.body(), role);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to get user ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserSessionAndNavigate(Long userId, String role) {
        UserSession userSession = new UserSession(LoginActivity.this);
        userSession.saveUserId(userId.toString());
        userSession.saveUserRole(role);  // Add this line to save the user's role
        navigateToRoleSpecificActivity(role);
    }

    private void navigateToRoleSpecificActivity(String role) {
        Intent intent;
        switch (role) {
            case "ADMIN":
                intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                break;
            case "EMPLOYEE":
                intent = new Intent(LoginActivity.this, EmployeeActivity.class);
                break;
            case "CUSTOMER":
                intent = new Intent(LoginActivity.this, CustomerMainWithBottomNavActivity.class);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Unknown role", Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(intent);
        finish();  // Close LoginActivity so user can't go back to it using back button
    }
}