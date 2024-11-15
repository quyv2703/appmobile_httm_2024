package com.ltq27.baotrimaylanh.activity.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.apiresponse.ListEmployeeResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityListEmployeeBinding;
import com.ltq27.baotrimaylanh.model.Employee;
import com.ltq27.baotrimaylanh.recyclerview.RcvListEmployeeAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployeeActivity extends AppCompatActivity {
    private List<Employee> listEmployee;
    private ActivityListEmployeeBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd=ActivityListEmployeeBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvListEMP.setHasFixedSize(true);
        bd.rcvListEMP.setLayoutManager(new LinearLayoutManager(this));
        setEvent();
    }

    private void setEvent() {
        ApiInterface apiInterface= RetrofitClient.getClient().create(ApiInterface.class);
        Call<ListEmployeeResponse> call=apiInterface.getListEmployee();
        call.enqueue(new Callback<ListEmployeeResponse>() {
            @Override
            public void onResponse(Call<ListEmployeeResponse> call, Response<ListEmployeeResponse> response) {
                if(response.isSuccessful()){
                    listEmployee=response.body().getResult();
                    RcvListEmployeeAdapter adapter=new RcvListEmployeeAdapter(listEmployee,ListEmployeeActivity.this);
                    bd.rcvListEMP.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ListEmployeeResponse> call, Throwable t) {

            }
        });
    }
}