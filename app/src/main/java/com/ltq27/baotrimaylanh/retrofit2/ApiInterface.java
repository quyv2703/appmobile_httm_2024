package com.ltq27.baotrimaylanh.retrofit2;

import com.ltq27.baotrimaylanh.activity.admin.dto.BaoCaoDoanhThu;
import com.ltq27.baotrimaylanh.activity.customer.dto.CustomerDTO;
import com.ltq27.baotrimaylanh.activity.customer.dto.DangKiDto;
import com.ltq27.baotrimaylanh.activity.customer.dto.DatLichStatusDTO;
import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;
import com.ltq27.baotrimaylanh.apiresponse.ApiResponse;
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDonDatLich;
import com.ltq27.baotrimaylanh.apiresponse.DanhSachDuocPhanCong;
import com.ltq27.baotrimaylanh.apiresponse.ListEmployeeResponse;
import com.ltq27.baotrimaylanh.apiresponse.LoginResponse;
import com.ltq27.baotrimaylanh.apiresponse.NhanXetDTO;
import com.ltq27.baotrimaylanh.apiresponse.NhanXetResponse;
import com.ltq27.baotrimaylanh.apiresponse.TrangThaiDuyetDTO;
import com.ltq27.baotrimaylanh.model.Customer;
import com.ltq27.baotrimaylanh.model.DatLichDto;
import com.ltq27.baotrimaylanh.model.DichVu;
import com.ltq27.baotrimaylanh.model.DichVuVsGia;
import com.ltq27.baotrimaylanh.model.Employee;
import com.ltq27.baotrimaylanh.model.GoiDichVu;
import com.ltq27.baotrimaylanh.activity.employee.HoaDonResponse;
import com.ltq27.baotrimaylanh.model.HoaDon;
import com.ltq27.baotrimaylanh.model.LoaiMayLanh;
import com.ltq27.baotrimaylanh.model.Login;
import com.ltq27.baotrimaylanh.model.PhanCong;
import com.ltq27.baotrimaylanh.model.ThongTinDatLich;

import java.time.LocalDate;
import java.util.List;

import kotlin.ParameterName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    // thêm phần gọi api phân loại

    // khách hàng đăng kí tài khoản
    @POST("accounts/customer")
    Call<Customer> taoTaiKhoanKhachHang(@Body DangKiDto dangKiDto);
    @GET("customers/id")
    Call<Long> getCustomerIdByUsername(@Query("username") String username);
    // lấy thông tin khách hàng
    @GET("customers/username/{usnername}")
    Call<CustomerDTO> layThongTinKhachHang(@Path("usnername") String username);
    @GET("employees/id")
    Call<Long> getEmployeeIdByUsername(@Query("username") String username);
    // khách hàng tạo đơn đặt lịch
    @POST("thongtindatlich")
    Call<DatLichDto> taoDonDatLich(@Body DatLichDto datLichDto);

    @GET("loaimaylanh")
    Call<ApiResponse<List<LoaiMayLanh>>> danhSachLoaiMayLanh();
   /* @GET("goidichvu/customerview")
    Call<List<ListGoiDVCustomerView>> goiDichVuCustomerView();*/
    @GET("dongia/search")
    Call<Long> layIdDonGia(  @Query("goiDichVuId") Long goiDichVuId,
                             @Query("loaiMayLanhId") Long loaiMayLanhId
    );
    // lấy danh sách nhân viên kĩ thuật
    @GET("employees/by-role/{roleId}")
    Call<List<Employee>> danhSachNhanVienPhanCong(@Path("roleId") Long roleId);
    // lấy thông tin nhân viên
    @GET("employees/username/{usnername}")
    Call<CustomerDTO> layThongTinNhanVien(@Path("usnername") String username);
    @POST("phancong")
    Call<PhanCong> taoPhanCong(@Body PhanCong phanCong);
    @GET("thongtindatlich")
    Call<List<DanhSachDonDatLich>> layDanhSachDonDatLich();
    // kĩ thuật viên cập nhật đồng ý hay từ chối bảo trì
    @PATCH("phancong/{id}/trang-thai")
    Call<DanhSachDuocPhanCong> capNhatTrangThaiPhanCong(@Path("id") Long id, @Body com.ltq27.baotrimaylanh.activity.employee.TrangThaiDuyetDTO trangThaiDuyetDTO);
   // cập nhật hóa đơn thanh toán thành công và ngày hoàn thành
    @PATCH("hoadon/{id}")
    Call<ApiResponse<HoaDonResponse>> capNhatHoaDon(@Path("id") Long id);
 // danh sách nhận xét
    @GET("nhanxet/goiDichVu/{id}")
    Call<List<NhanXetResponse>> danhSachNhanXet(@Path("id") Long id);
    //bộ lọc nhận xét
    @GET("nhanxet/filter")
    Call<List<NhanXetResponse>> boLocNhanXet(@Query("label") String label);
    // thêm nhâận xét
    @POST("nhanxet/them")
    Call<NhanXetResponse> themNhanXet(@Body NhanXetDTO nhanXetDTO);
    //Quản lí cập nhật trạng thái của đơn đặt lịch
    @PATCH("thongtindatlich/{id}/trang-thai")
    Call<TrangThaiDuyetDTO> capNhatTrangThai(@Path("id") Long id, @Body TrangThaiDuyetDTO trangThaiDuyetDTO);
    // cập nhật quản lí nào duyệt
    @PATCH("thongtindatlich/manager/{idDatLich}/{idManager}")
    Call<ThongTinDatLich> updateIdManager(@Path("idDatLich") Long idDatLich, @Path("idManager") Long idManager);
    // quản lí lấy danh sách phân công bị nhân viên từ chối
   /* @GET("phancong/trangthaituchoi")
    Call<List<DanhSachDuocPhanCong>> layDanhSachPhanCongBiTuChoi(@Query("trangThai") TrangThaiPhanCong trangThaiPhanCong);*/
    // nhân viên kĩ thuật lấy list công việc được phân công
    @GET("phancong/nhanvien/{id}")
    Call<List<DanhSachDuocPhanCong>> layDanhSachDuocPhanCong(@Path("id") Long id);
    @POST("login")
    Call<LoginResponse> login(@Body Login loginRequest);
    @GET("bao-cao-doanh-thu/report")
    Call<BaoCaoDoanhThu> layBaoCaoDoanhThu();
    @GET("hoadon/report/revenue")
    Call<BaoCaoDoanhThu> layBaoCaoDoanhThuTheoNgay(@Query("startDate") LocalDate startDate,
                                                   @Query("endDate") LocalDate endDate);



    // khách xem
    @GET("goidichvu/customerview")
    Call<List<GoiDichVu>> getListGoiDichVu();
    // quan li xem GÓI DỊCH VỤ
    @GET("goidichvu")
    Call<ApiResponse<List<GoiDichVuResponse>>> getListGoiDichVuQuanLi();
    @POST("goidichvu")
    Call<ApiResponse<GoiDichVuResponse>> taoGoiDichVu(@Body GoiDichVuResponse goiDichVu);
    @GET("accounts/listemployee")
    Call<ListEmployeeResponse> getListEmployee();

    // hóa đơn
    @GET("hoadon/customer/{id}")
    Call<List<HoaDonResponse>> layDanhSachHoaDon(@Path("id") Long id);
    //LOẠI MÁY LẠNH
    @GET("loaimaylanh")
    Call<ApiResponse<List<LoaiMayLanh>>> layDanhSachLoaiMayLanh();
    // THÔNG TIN ĐẶT LỊCH CỦA KHÁCH HÀNG
    @GET("thongtindatlich/status")
    Call<List<DatLichStatusDTO>> layDanhSachDonDatLichKhachHang(@Query("customerId") Long CustomerId);
    // QUẢN LÍ LẤY DANH SÁCH NHÂN VIÊN TỪ CHỐI PHÂN CÔNG
    @GET("phancong/trangthaituchoi")
    Call<List<DanhSachDuocPhanCong>> layDanhSachPhanCongBiTuChoi(@Query("trangThai") TrangThaiPhanCong trangThaiPhanCong);
    @PUT("phancong/{id}/nhan-vien")
    Call<DanhSachDuocPhanCong> capNhatNhanVienPhanCongVaTrangThaiPhanCong(@Path("id") Long id, @Query("maNhanVienMoi") Long maNhanVienMoi);
}
