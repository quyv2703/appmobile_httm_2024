package com.ltq27.baotrimaylanh.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.model.DichVu;
import com.ltq27.baotrimaylanh.model.DichVuVsGia;
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;

import java.util.List;

public class RcvDichVuAdapter extends RecyclerView.Adapter<RcvDichVuAdapter.ViewHolder> {
    private List<DichVuVsGia> dichVuList;
    private Context context;
   private ItemClick onItemClick;

    public RcvDichVuAdapter(List<DichVuVsGia> dichVuList, Context context, ItemClick onItemClick) {
        this.dichVuList = dichVuList;
        this.context = context;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RcvDichVuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_dich_vu,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvDichVuAdapter.ViewHolder holder, int position) {
        DichVuVsGia dichVu=dichVuList.get(position);

        holder.tvNameDV.setText(dichVu.getDichVu().getName());
        if(dichVu.getDichVu().getFixedPrice()){
            holder.tvPriceDV.setText(dichVu.getDichVu().getCommonPrice());

        }else {
            holder.tvPriceDV.setText(dichVu.getMinPrice()+" - "+dichVu.getMaxPrice()+" đ");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick!= null){
                    onItemClick.onClick(dichVu.getDichVu().getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dichVuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameDV,tvPriceDV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameDV=itemView.findViewById(R.id.tvNameDV);
            tvPriceDV=itemView.findViewById(R.id.tvPriceDV);

        }
    }
}
