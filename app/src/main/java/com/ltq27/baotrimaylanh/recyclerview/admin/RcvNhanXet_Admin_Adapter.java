package com.ltq27.baotrimaylanh.recyclerview.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.apiresponse.NhanXetResponse;

import java.util.List;

public class RcvNhanXet_Admin_Adapter extends RecyclerView.Adapter<RcvNhanXet_Admin_Adapter.ViewHolder> {
    private List<NhanXetResponse> list;
    private Context context;

    public RcvNhanXet_Admin_Adapter(List<NhanXetResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvNhanXet_Admin_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhan_xet_admin,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvNhanXet_Admin_Adapter.ViewHolder holder, int position) {
        NhanXetResponse nhanXet = list.get(position);
        holder.tvName.setText("Tên: "+nhanXet.getCustomerName());
        holder.tvNhanXet.setText("Nhận xét: "+nhanXet.getComment());
        holder.tvLabel.setText("Nhãn: "+nhanXet.getLabel());
        holder.tvGoiDV.setText("Tên dịch vụ: "+nhanXet.getServicePakageName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvNhanXet,tvLabel,tvGoiDV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNhanXet = itemView.findViewById(R.id.tvNhanXet);
            tvLabel=itemView.findViewById(R.id.tvLabel);
            tvGoiDV=itemView.findViewById(R.id.tvGoiDV);
        }
    }
}
