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
import com.ltq27.baotrimaylanh.model.LoaiMayLanh;

import java.util.List;

public class RcvLoaiMayLanhAdapter extends RecyclerView.Adapter<RcvLoaiMayLanhAdapter.ViewHolder>{
    private List<LoaiMayLanh> loaiMayLanhList;
    private Context context;

    public RcvLoaiMayLanhAdapter(List<LoaiMayLanh> loaiMayLanhList, Context context) {
        this.loaiMayLanhList = loaiMayLanhList;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvLoaiMayLanhAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_may_lanh_quan_li,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvLoaiMayLanhAdapter.ViewHolder holder, int position) {
        LoaiMayLanh loaiMayLanh=loaiMayLanhList.get(position);
        holder.tvName.setText(loaiMayLanh.getName());
    }

    @Override
    public int getItemCount() {
        return loaiMayLanhList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            btnSua=itemView.findViewById(R.id.btnSua);
            btnXoa=itemView.findViewById(R.id.btnXoa);
        }
    }
}
