package com.ltq27.baotrimaylanh.recyclerview.khachhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.customer.dto.DatLichStatusDTO;

import java.util.List;

public class RcvDatLichStatusAdapter extends RecyclerView.Adapter<RcvDatLichStatusAdapter.ViewHolder> {
    private List<DatLichStatusDTO> lichStatusDTOList;

    public RcvDatLichStatusAdapter(List<DatLichStatusDTO> lichStatusDTOList, Context context) {
        this.lichStatusDTOList = lichStatusDTOList;
    }

    @NonNull
    @Override
    public RcvDatLichStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dat_lich_khach_hang,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvDatLichStatusAdapter.ViewHolder holder, int position) {
        DatLichStatusDTO lichStatusDTO=lichStatusDTOList.get(position);
        holder.tvNgayDat.setText("Ngày đặt: "+lichStatusDTO.getNgayDat());
        holder.tvNgayThucHien.setText("Ngày thực hiện"+lichStatusDTO.getNgayThucHien());
        holder.tvDiaChi.setText("Địa chỉ: "+lichStatusDTO.getDiaChiThucHien());
        holder.tvGoiDichVu.setText("Tên gói dịch vụ: "+lichStatusDTO.getTenGoiDichVu());
        holder.tvLoaiMayLanh.setText("Tên loại máy lạnh: "+lichStatusDTO.getTenLoaiMayLanh());
      //  holder.tvTrangThai.setText(lichStatusDTO.getTrangThaiDuyet());
        String trangThai = String.valueOf(lichStatusDTO.getTrangThaiDuyet());
        switch(trangThai) {
            case "2":
                holder.tvTrangThai.setText("Trạng thái: Đã duyệt");
                break;
            case "1":
                holder.tvTrangThai.setText("Trạng thái: Chờ duyệt");
                break;
            default:
                holder.tvTrangThai.setText("Trạng thái: Từ chối");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lichStatusDTOList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgayDat,tvNgayThucHien,tvDiaChi,tvGoiDichVu,tvLoaiMayLanh,tvTrangThai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgayDat=itemView.findViewById(R.id.tvNgayDat);
            tvNgayThucHien=itemView.findViewById(R.id.tvNgayThucHien);
            tvDiaChi=itemView.findViewById(R.id.tvDiaChi);
            tvGoiDichVu=itemView.findViewById(R.id.tvGoiDichVu);
            tvLoaiMayLanh=itemView.findViewById(R.id.tvLoaiMayLanh);
            tvTrangThai=itemView.findViewById(R.id.tvTrangThai);
        }
    }
}
