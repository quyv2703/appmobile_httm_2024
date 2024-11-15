package com.ltq27.baotrimaylanh.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.activity.employee.HoaDonResponse;
import com.ltq27.baotrimaylanh.formatCurrencyTest;

import java.util.List;

public class RcvDanhSachHoaDonKhachHangAdapter extends RecyclerView.Adapter<RcvDanhSachHoaDonKhachHangAdapter.ViewHolder> {
    private List<HoaDonResponse> list;
    private Context context;

    public RcvDanhSachHoaDonKhachHangAdapter(List<HoaDonResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvDanhSachHoaDonKhachHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_hoa_don_khach_hang,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvDanhSachHoaDonKhachHangAdapter.ViewHolder holder, int position) {
        HoaDonResponse hoaDonResponse=list.get(position);
        holder.tvMaHoaDon.setText("Mã hóa đơn: "+hoaDonResponse.getId().toString());

        holder.tvNgayThanhToan.setText("Ngày thanh toán: "+hoaDonResponse.getNgayThanhToan());
        String trangThaiThanhToan= String.valueOf(hoaDonResponse.getTrangThaiThanhToan());
        if(trangThaiThanhToan.equals("0")){
            holder.tvTrangThaiThanhToan.setText("Trạng thái thanh toán: Chưa thanh toán");
            holder.tvNgayThanhToan.setText("Ngày thanh toán: Chưa thanh toán");
        }else{
            holder.tvNgayThanhToan.setText("Ngày thanh toán: "+hoaDonResponse.getNgayThanhToan());
            holder.tvTrangThaiThanhToan.setText("Trạng thái thanh toán: Đã thanh toán");
        }
        String tongTien=formatCurrencyTest.formatCurrency(hoaDonResponse.getTongTien());
        holder.tvTongTien.setText("Tổng tiền: "+tongTien);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaHoaDon,tvNgayThanhToan,tvTrangThaiThanhToan,tvTongTien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHoaDon=itemView.findViewById(R.id.tvMaHoaDon);
            tvNgayThanhToan=itemView.findViewById(R.id.tvNgayThanhToan);
            tvTrangThaiThanhToan=itemView.findViewById(R.id.tvTrangThaiThanhToan);
            tvTongTien=itemView.findViewById(R.id.tvTongTien);
        }

    }
}
