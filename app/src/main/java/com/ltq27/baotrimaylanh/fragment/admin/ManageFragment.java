package com.ltq27.baotrimaylanh.fragment.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ltq27.baotrimaylanh.activity.admin.BaoCaoDoanhThuActivity;
import com.ltq27.baotrimaylanh.activity.admin.BoLocNhanXetActivity;
import com.ltq27.baotrimaylanh.activity.admin.ChonNgayBaoCaoActivity;
import com.ltq27.baotrimaylanh.activity.admin.DanhSachDatLichActivity;
import com.ltq27.baotrimaylanh.activity.admin.capnhatphancong.DanhSachPhanCongBiTuChoiActivity;
import com.ltq27.baotrimaylanh.activity.admin.GoiDichVuActivity;
import com.ltq27.baotrimaylanh.activity.admin.LoaiMayLanhActivity;
import com.ltq27.baotrimaylanh.databinding.FragmentManageBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManageBinding bd;

    public ManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageFragment newInstance(String param1, String param2) {
        ManageFragment fragment = new ManageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = FragmentManageBinding.inflate(inflater, container, false);
        return bd.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bd.btnDanhSachDatLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachDatLichActivity.class);
                startActivity(intent);
            }
        });
        bd.btnLoaiMayLanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoaiMayLanhActivity.class);
                startActivity(intent);
            }
        });
        bd.btnGoiDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GoiDichVuActivity.class);
                startActivity(intent);
            }
        });
        bd.btnBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BaoCaoDoanhThuActivity.class);
                startActivity(intent);
            }
        });
        bd.btnBaoCaoTheoNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ChonNgayBaoCaoActivity.class);
                startActivity(intent);
            }
        });
        bd.btnDanhSachPhanCongBiTuChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DanhSachPhanCongBiTuChoiActivity.class);
                startActivity(intent);
            }
        });
        bd.btnBoLocNhanXet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BoLocNhanXetActivity.class);
                startActivity(intent);
            }
        });
    }
}