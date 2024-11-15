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
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;

import java.util.List;

public class RcvDanhSachChonPhanCongNhanVienAdapter extends RecyclerView.Adapter<RcvDanhSachChonPhanCongNhanVienAdapter.ViewHolder> {
    private List<Employee> employeeList;
    private ItemClick itemClick;
    private Context context;

    public RcvDanhSachChonPhanCongNhanVienAdapter(List<Employee> employeeList, ItemClick itemClick, Context context) {
        this.employeeList = employeeList;
        this.itemClick = itemClick;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvDanhSachChonPhanCongNhanVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_phan_cong_nhan_vien,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvDanhSachChonPhanCongNhanVienAdapter.ViewHolder holder, int position) {
        Employee employee=employeeList.get(position);
        holder.tvSoThuTu.setText(String.valueOf(position+1)+". ");
        holder.tvEmployeeId.setText("ID: "+String.valueOf(employee.getId()));
        holder.tvName.setText("Tên: "+employee.getName());
        holder.tvSdt.setText("SĐT: "+employee.getPhoneNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(employee.getId());
            }});
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoThuTu,tvEmployeeId,tvName,tvSdt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoThuTu=itemView.findViewById(R.id.tvSoThuTu);
            tvEmployeeId=itemView.findViewById(R.id.tvEmployeeId);
            tvName=itemView.findViewById(R.id.tvName);
            tvSdt=itemView.findViewById(R.id.tvSdt);
        }
    }
}
