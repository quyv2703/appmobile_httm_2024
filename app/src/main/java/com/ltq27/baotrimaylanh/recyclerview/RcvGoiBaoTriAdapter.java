package com.ltq27.baotrimaylanh.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.NestedLinearLayoutManager;
import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.WrapContentLinearLayoutManager;
import com.ltq27.baotrimaylanh.apiresponse.ListGoiDVCustomerView;
import com.ltq27.baotrimaylanh.model.GoiDichVu;
import com.ltq27.baotrimaylanh.retrofit2.ItemClick;

import java.util.List;

public class RcvGoiBaoTriAdapter extends RecyclerView.Adapter<RcvGoiBaoTriAdapter.ViewHolder> {
   private List<GoiDichVu> listGoiDVCustomerViews;
     private Context context;
     private ItemClick itemClick;
    private ButtonClick buttonClick;
    public interface ButtonClick {
        void onButtonClick(Long goiDichVuId);
    }

    public RcvGoiBaoTriAdapter(List<GoiDichVu> listGoiDVCustomerViews, Context context, ItemClick itemClick, ButtonClick buttonClick) {
        this.listGoiDVCustomerViews = listGoiDVCustomerViews;
        this.context = context;
        this.itemClick = itemClick;
        this.buttonClick = buttonClick;
    }

    @NonNull
    @Override
    public RcvGoiBaoTriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_goi_dich_vu,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvGoiBaoTriAdapter.ViewHolder holder, int position) {
        GoiDichVu listGoiDVCustomerView = listGoiDVCustomerViews.get(position);
        holder.tvName.setText(listGoiDVCustomerView.getTenGoi());
        holder.tvDescription.setText(listGoiDVCustomerView.getDescription());
        // Set up Child RecyclerView
        ChildAdapter childAdapter = new ChildAdapter(listGoiDVCustomerView.getLoaiMayLanhGiaList());
        holder.rvLoaiMayLanh.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
        holder.rvLoaiMayLanh.setAdapter(childAdapter);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClick!= null){
                    itemClick.onClick(listGoiDVCustomerView.getGoiDichVuId());
                }
            }
        });
        holder.btnNhanXet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonClick != null) {
                    buttonClick.onButtonClick(listGoiDVCustomerView.getGoiDichVuId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGoiDVCustomerViews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvDescription;
        Button btnNhanXet;
        RecyclerView rvLoaiMayLanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rvLoaiMayLanh = itemView.findViewById(R.id.rvLoaiMayLanh);
            btnNhanXet = itemView.findViewById(R.id.btnNhanXet);
        }
    }
}
