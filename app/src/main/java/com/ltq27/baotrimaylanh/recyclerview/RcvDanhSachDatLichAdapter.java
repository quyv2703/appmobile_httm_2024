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
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDonDatLich;

import java.util.List;

public class RcvDanhSachDatLichAdapter extends RecyclerView.Adapter<RcvDanhSachDatLichAdapter.ViewHolder> {
    private Context context;
    private  List<DanhSachDonDatLich> danhSachDonDatLichList;
    private OnItemClickListener listener;
    // Constants for approval status
    private static final String STATUS_PENDING = "1";
    private static final String STATUS_APPROVED = "2";
    private static final String STATUS_REJECTED = "3";

    public interface OnItemClickListener {
        void onDongY(DanhSachDonDatLich thongTinDatLich);
        void onTuChoi(DanhSachDonDatLich thongTinDatLich);
    }

    public RcvDanhSachDatLichAdapter(Context context, List<DanhSachDonDatLich> danhSachDonDatLichList, OnItemClickListener listener) {
        this.context = context;
        this.danhSachDonDatLichList = danhSachDonDatLichList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_sach_dat_lich, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhSachDonDatLich danhSachDonDatLich = danhSachDonDatLichList.get(position);

        holder.tvName.setText("Tên khách hàng: " + danhSachDonDatLich.getTenKhachHang());
        holder.tvGoiDichVu.setText("Tên gói dịch vụ: " + danhSachDonDatLich.getGoiDichVu());
        holder.tvNgayThiCong.setText("Ngày thực hiện: " + danhSachDonDatLich.getNgayThucHien());
        holder.tvDiaChi.setText("Địa chỉ thực hiện: " + danhSachDonDatLich.getDiaChiThucHien());

        String trangThaiDuyet = danhSachDonDatLich.getTrangThaiDuyet();
        if (trangThaiDuyet != null) {
            switch (trangThaiDuyet) {
                case STATUS_PENDING:
                    holder.tvTrangThaiDuyet.setText("Trạng thái duyệt: Chưa duyệt");
                    holder.btnDongY.setVisibility(View.VISIBLE);
                    holder.btnTuChoi.setVisibility(View.VISIBLE);
                    break;
                case STATUS_APPROVED:
                    holder.tvTrangThaiDuyet.setText("Trạng thái duyệt: Đã duyệt");
                    holder.btnDongY.setVisibility(View.GONE);
                    holder.btnTuChoi.setVisibility(View.GONE);
                    break;
                case STATUS_REJECTED:
                    holder.tvTrangThaiDuyet.setText("Trạng thái duyệt: Từ chối");
                    holder.btnDongY.setVisibility(View.GONE);
                    holder.btnTuChoi.setVisibility(View.GONE);
                    break;
                default:
                    holder.tvTrangThaiDuyet.setText("Trạng thái duyệt: Không xác định");
                    holder.btnDongY.setVisibility(View.GONE);
                    holder.btnTuChoi.setVisibility(View.GONE);
                    break;
            }
        } else {
            holder.tvTrangThaiDuyet.setText("Trạng thái duyệt: Không xác định");
            holder.btnDongY.setVisibility(View.GONE);
            holder.btnTuChoi.setVisibility(View.GONE);
        }

        holder.btnDongY.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDongY(danhSachDonDatLich);
            }
        });
        holder.btnTuChoi.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTuChoi(danhSachDonDatLich);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachDonDatLichList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvGoiDichVu,tvNgayThiCong,tvDiaChi,tvTrangThaiDuyet;
        Button btnDongY,btnTuChoi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvGoiDichVu = itemView.findViewById(R.id.tvGoiDichVu);
            tvNgayThiCong = itemView.findViewById(R.id.tvNgayThiCong);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvTrangThaiDuyet = itemView.findViewById(R.id.tvTrangThaiDuyet);
            btnDongY = itemView.findViewById(R.id.btnDongY);
            btnTuChoi = itemView.findViewById(R.id.btnTuChoi);
        }

    }
}
