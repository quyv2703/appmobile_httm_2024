package com.ltq27.baotrimaylanh.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.model.Employee;

import java.util.List;

public class RcvListEmployeeAdapter extends RecyclerView.Adapter<RcvListEmployeeAdapter.ViewHolder> {
    List<Employee> listEmployee;
    Context context;

    public RcvListEmployeeAdapter(List<Employee> listEmployee, Context context) {
        this.listEmployee = listEmployee;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvListEmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_employee,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvListEmployeeAdapter.ViewHolder holder, int position) {
        Employee employee=listEmployee.get(position);
        holder.tvName.setText(employee.getName());
        holder.tvUsername.setText(employee.getAccount().getUsername());
        holder.tvRole.setText(employee.getAccount().getRole().getName());
        holder.tvActive.setText(employee.getAccount().isActive()?"Hoạt động":"Không hoạt động");
        holder.tvPhoneNumber.setText(employee.getPhoneNumber());
        holder.tvAddress.setText(employee.getAddress());
        holder.tvBirthYear.setText(employee.getBirthYear()+"");
        holder.tvGender.setText(employee.getGender());
        holder.tvIdCard.setText(employee.getIdCard());
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvUsername,tvRole,tvActive,tvPhoneNumber,tvAddress,tvBirthYear,tvGender,tvIdCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvUsername=itemView.findViewById(R.id.tvUsername);
            tvRole=itemView.findViewById(R.id.tvRole);
            tvActive=itemView.findViewById(R.id.tvActive);
            tvPhoneNumber=itemView.findViewById(R.id.tvPhoneNumber);
            tvAddress=itemView.findViewById(R.id.tvAddress);
            tvBirthYear=itemView.findViewById(R.id.tvBirthYear);
            tvGender=itemView.findViewById(R.id.tvGender);
            tvIdCard=itemView.findViewById(R.id.tvIdCard);
        }
    }
}
