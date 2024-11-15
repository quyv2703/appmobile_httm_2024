package com.ltq27.baotrimaylanh.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;
import com.ltq27.baotrimaylanh.model.GoiDichVu;

import java.util.List;

public class RcvGoiDichVuAdapter extends RecyclerView.Adapter<RcvGoiDichVuAdapter.ViewHolder> {
    List<GoiDichVuResponse> goiDichVuList;
    Context context;

    public RcvGoiDichVuAdapter(List<GoiDichVuResponse> goiDichVuList, Context context) {
        this.goiDichVuList = goiDichVuList;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvGoiDichVuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_goi_dich_vu_quan_li, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvGoiDichVuAdapter.ViewHolder holder, int position) {
        GoiDichVuResponse goiDichVu=goiDichVuList.get(position);
        holder.tvName.setText(goiDichVu.getName());
        holder.tvDescription.setText(goiDichVu.getDescription());

    }

    @Override
    public int getItemCount() {
        return goiDichVuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       TextView tvName,tvDescription;
       Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}
