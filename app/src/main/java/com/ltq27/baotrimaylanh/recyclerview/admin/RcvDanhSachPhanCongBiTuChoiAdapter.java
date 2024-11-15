package com.ltq27.baotrimaylanh.recyclerview.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDuocPhanCong;

import java.util.List;

public class RcvDanhSachPhanCongBiTuChoiAdapter extends RecyclerView.Adapter<RcvDanhSachPhanCongBiTuChoiAdapter.ViewHolder> {
    private List<DanhSachDuocPhanCong> list;
    private Context context;
    private onItemCapNhatPhanCongListener listener;

    public interface onItemCapNhatPhanCongListener{
        void onCapNhatPhanCong(DanhSachDuocPhanCong phanCong);
    }

    public RcvDanhSachPhanCongBiTuChoiAdapter(List<DanhSachDuocPhanCong> list, Context context, onItemCapNhatPhanCongListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RcvDanhSachPhanCongBiTuChoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_danh_sach_phan_cong_tu_choi_quan_li,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvDanhSachPhanCongBiTuChoiAdapter.ViewHolder holder, int position) {
        DanhSachDuocPhanCong phanCong=list.get(position);
        holder.tvMaPhanCong.setText("Mã phân công: "+String.valueOf(phanCong.getId()));
        holder.tvTenKhachHang.setText("Tên khách hàng: "+phanCong.getThongTinDatLich().getCustomer().getName());
        holder.tvTenGoiDichVu.setText("Tên gói dịch vụ: "+phanCong.getThongTinDatLich().getDonGia().getGoiDichVu().getName());
        holder.tvTenLoaiMayLanh.setText("Tên loại máy lạnh: "+phanCong.getThongTinDatLich().getDonGia().getLoaiMayLanh().getName());
        holder.tvNgayDat.setText("Ngày đặt: "+phanCong.getThongTinDatLich().getNgayDat());
        holder.tvNgayThucHien.setText("Ngày thực hiện: "+phanCong.getThongTinDatLich().getNgayThucHien());
        holder.tvDiaChi.setText("Địa chỉ thực hiện: "+phanCong.getThongTinDatLich().getDiaChiThucHien());
        holder.tvMaNhanVien.setText("Mã nhân viên đã được phân công: "+String.valueOf(phanCong.getNhanVien().getId()));
        holder.tvTenNhanVien.setText("Tên nhân viên: "+phanCong.getNhanVien().getName());
        holder.btnCapNhatPhanCong.setOnClickListener(v->{
            if(list!= null){
                listener.onCapNhatPhanCong(phanCong);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPhanCong,tvTenKhachHang,tvTenGoiDichVu,tvTenLoaiMayLanh,tvNgayDat,tvNgayThucHien,tvDiaChi,tvMaNhanVien,tvTenNhanVien,tvTrangThaiTuChoi;
        Button btnCapNhatPhanCong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhanCong=itemView.findViewById(R.id.tvMaPhanCong);
            tvTenKhachHang=itemView.findViewById(R.id.tvTenKhachHang);
            tvTenGoiDichVu=itemView.findViewById(R.id.tvTenGoiDichVu);
            tvTenLoaiMayLanh=itemView.findViewById(R.id.tvTenLoaiMayLanh);
            tvNgayDat=itemView.findViewById(R.id.tvNgayDat);
            tvNgayThucHien=itemView.findViewById(R.id.tvNgayThucHien);
            tvDiaChi=itemView.findViewById(R.id.tvDiaChi);
            tvMaNhanVien=itemView.findViewById(R.id.tvMaNhanVien);
            tvTenNhanVien=itemView.findViewById(R.id.tvTenNhanVien);
            btnCapNhatPhanCong=itemView.findViewById(R.id.btnCapNhatPhanCong);
        }
    }
}
