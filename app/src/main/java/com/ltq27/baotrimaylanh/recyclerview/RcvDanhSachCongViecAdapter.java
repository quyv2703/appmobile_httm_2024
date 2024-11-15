package com.ltq27.baotrimaylanh.recyclerview;

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

public class RcvDanhSachCongViecAdapter extends RecyclerView.Adapter<RcvDanhSachCongViecAdapter.ViewHolder> {
    private List<DanhSachDuocPhanCong> list;
    private OnItemClickListener itemClickListener;
    private OnUpdateButtonClickListener onUpdateButtonClickListener;

    public interface OnItemClickListener {
        void onItemClick(Long id);
    }

    public interface OnUpdateButtonClickListener {
        void onUpdateButtonClick(Long idHoaDon, Button updateButton);
    }

    public RcvDanhSachCongViecAdapter(List<DanhSachDuocPhanCong> list, OnItemClickListener itemClickListener, OnUpdateButtonClickListener onUpdateButtonClickListener) {
        this.list = list;
        this.itemClickListener = itemClickListener;
        this.onUpdateButtonClickListener = onUpdateButtonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_sach_cong_viec, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhSachDuocPhanCong congViec = list.get(position);
        holder.bind(congViec, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSTT, tvName, tvSDT, tvDiaChi, tvTenGoi, tvNgayThiCong, tvTrangThai;
        Button btnCapNhat, btnCapNhatHoaDon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            tvName = itemView.findViewById(R.id.tvName);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvTenGoi = itemView.findViewById(R.id.tvTenGoi);
            tvNgayThiCong = itemView.findViewById(R.id.tvNgayThiCong);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            btnCapNhat = itemView.findViewById(R.id.btnCapNhat);
            btnCapNhatHoaDon = itemView.findViewById(R.id.btnCapNhatHoaDon);
        }

        void bind(final DanhSachDuocPhanCong congViec, int position) {
            tvSTT.setText((position + 1) + ".");
            tvName.setText("Tên khách hàng: " + congViec.getThongTinDatLich().getCustomer().getName());
            tvSDT.setText("SĐT: " + congViec.getThongTinDatLich().getCustomer().getPhoneNumber());
            tvDiaChi.setText("Địa chỉ: " + congViec.getThongTinDatLich().getDiaChiThucHien());
            tvTenGoi.setText("Tên gói: " + congViec.getThongTinDatLich().getDonGia().getGoiDichVu().getName());
            tvNgayThiCong.setText("Ngày thi công: " + congViec.getThongTinDatLich().getNgayThucHien().toString());
            tvTrangThai.setText("Trạng thái: " + congViec.getTrangThai().toString());
            // đã thanh toán
            if (congViec.getThongTinDatLich().getLichSuHoaDon() != null &&

                    congViec.getThongTinDatLich().getLichSuHoaDon().getTrangThaiThanhToan() == 1) {

                btnCapNhatHoaDon.setText("Đã thanh toán");
                btnCapNhatHoaDon.setEnabled(false);

                btnCapNhat.setVisibility(View.GONE);
            }
            //chưa thanh toán
            if(congViec.getThongTinDatLich().getLichSuHoaDon().getTrangThaiThanhToan()==0) {
                btnCapNhatHoaDon.setText("Xác nhận thanh toán");
                btnCapNhatHoaDon.setEnabled(true);

                if(congViec.getTrangThai().equals("TU_CHOI")){
                    tvTrangThai.setText("Trạng thái: TU_CHOI" );
                    btnCapNhatHoaDon.setVisibility(View.GONE);
                    btnCapNhat.setVisibility(View.GONE);
                }
                if(congViec.getTrangThai().equals("CHO_XAC_NHAN")){
                    tvTrangThai.setText("Trạng thái: CHO_XAC_NHAN" );
                    btnCapNhat.setVisibility(View.VISIBLE);
                    btnCapNhatHoaDon.setVisibility(View.GONE);

                }
                if(congViec.getTrangThai().equals("DA_DONG_Y")){
                    tvTrangThai.setText("Trạng thái: DA_DONG_Y" );
                    btnCapNhatHoaDon.setVisibility(View.GONE);
                }
                if(congViec.getTrangThai().equals("DANG_THUC_HIEN")){
                    tvTrangThai.setText("Trạng thái: DANG_THUC_HIEN" );
                    btnCapNhatHoaDon.setVisibility(View.GONE);
                }
                if(congViec.getTrangThai().equals("HOAN_THANH")){
                    tvTrangThai.setText("Trạng thái: HOAN_THANH" );
                    btnCapNhat.setVisibility(View.GONE);
                }
            }



            btnCapNhat.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(congViec.getId());
                }
            });

            btnCapNhatHoaDon.setOnClickListener(v -> {
                if (onUpdateButtonClickListener != null) {
                    Long hoaDonId = congViec.getThongTinDatLich().getLichSuHoaDon().getId();
                    onUpdateButtonClickListener.onUpdateButtonClick(hoaDonId, btnCapNhatHoaDon);
                }
            });
        }
    }
}
