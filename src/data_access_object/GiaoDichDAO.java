package data_access_object;

import java.sql.Connection;
import java.util.List;
import domain.GiaoDichModel;

public abstract class GiaoDichDAO {
    protected Connection connection;

    public GiaoDichDAO(Connection connection) {
        MySQLConnection mySQLConnection = new MySQLConnection(connection);
        this.connection = mySQLConnection.getConnection();
    }

    // Phương thức thêm giao dịch (trừu tượng)
    public abstract void themGiaodich(GiaoDichModel giaodich);

    // Phương thức sửa giao dịch (trừu tượng)
    public abstract void suaGiaodich(GiaoDichModel giaodich);

    // Phương thức xóa giao dịch (trừu tượng)
    public abstract void xoaGiaodich(int maGd);

    // Phương thức lấy danh sách giao dịch (trừu tượng)
    public abstract List<GiaoDichModel> layDanhSachGiaodich();

    // Phương thức tìm giao dịch (trừu tượng)
    public abstract List<GiaoDichModel> timGiaoDich(int id);

    // Phương thức đếm số lượng giao dịch theo loại (trừu tượng)
    public abstract int demSoLuongTheoLoai(String loai);

    // Phương thức tính trung bình thành tiền của giao dịch (trừu tượng)
    public abstract double tinhTrungBinhThanhTienGiaodich();

    // Phương thức tính tổng giá trị giao dịch (trừu tượng)
    public abstract double tinhTongGiaTri();

    // Phương thức kiểm tra mã giao dịch tồn tại (trừu tượng)
    public abstract boolean kiemTraMaGiaoDichTonTai(int maGd);

    // Phương thức xuất hóa đơn theo tháng và năm (trừu tượng)
    public abstract List<GiaoDichModel> xuatHoaDonTheoThangNam(int thang, int nam);
}
