package com.ltq27.baotrimaylanh.recyclerview;

import static com.ltq27.baotrimaylanh.formatCurrencyTest.formatCurrency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.model.LoaiMayLanhGia;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {
    private List<LoaiMayLanhGia> loaiMayLanhGiaList;

    public ChildAdapter(List<LoaiMayLanhGia> loaiMayLanhGiaList) {
        this.loaiMayLanhGiaList = loaiMayLanhGiaList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        LoaiMayLanhGia loaiMayLanhGia = loaiMayLanhGiaList.get(position);
        holder.tvTenLoaiMayLanh.setText(loaiMayLanhGia.getTenLoaiMayLanh());
        holder.tvGia.setText(formatCurrency(loaiMayLanhGia.getGia()));
    }

    @Override
    public int getItemCount() {
        return loaiMayLanhGiaList.size();
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiMayLanh, tvGia;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiMayLanh = itemView.findViewById(R.id.tvTenLoaiMayLanh);
            tvGia = itemView.findViewById(R.id.tvGia);
        }
    }
}
