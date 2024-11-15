package com.ltq27.baotrimaylanh.recyclerview;

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

public class RcvNhanXetAdapter extends RecyclerView.Adapter<RcvNhanXetAdapter.ViewHolder> {
    private List<NhanXetResponse> list;
    private Context context;

    public RcvNhanXetAdapter(List<NhanXetResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvNhanXetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhan_xet,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvNhanXetAdapter.ViewHolder holder, int position) {
        NhanXetResponse nhanXet = list.get(position);
        holder.tvName.setText(nhanXet.getCustomerName());
        holder.tvNhanXet.setText(nhanXet.getComment());
        holder.tvLabel.setText(nhanXet.getLabel());
        holder.ratingBar.setRating( nhanXet.getStart());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvNhanXet,tvLabel;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNhanXet = itemView.findViewById(R.id.tvNhanXet);
            tvLabel=itemView.findViewById(R.id.tvLabel);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
