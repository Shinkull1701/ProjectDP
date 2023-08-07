package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.GiaoDichModel;
import domain.GiaoDichNha;

public class GiaoDichNhaDAO extends GiaoDichDAO {
    
    public GiaoDichNhaDAO(Connection connection) {
        super(connection);
    }
    
    @Override
    public void themGiaodich(GiaoDichModel giaodich) {
        String sql = "INSERT INTO GiaoDichNha(maGd, ngayGd, donGia, loaiNha, diaChi, dienTich, thanhTien)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, giaodich.getMaGd());
            statement.setDate(2, Date.valueOf(giaodich.getNgayGd()));
            statement.setDouble(3, giaodich.getDonGia());
            statement.setString(4, ((GiaoDichNha) giaodich).getLoai());
            statement.setString(5, ((GiaoDichNha) giaodich).getDiaChi());
            statement.setDouble(6, giaodich.getDienTich());
            statement.setDouble(7, giaodich.getThanhTien());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void suaGiaodich(GiaoDichModel giaodich) {
    String sql = "UPDATE GiaoDichNha SET ngayGd = ?, donGia = ?, loaiNha = ?, diaChi = ?, dientich = ?, thanhTien = ? " +
                 "WHERE maGd = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, Date.valueOf(giaodich.getNgayGd()));
        statement.setDouble(2, giaodich.getDonGia());
        statement.setString(3, ((GiaoDichNha) giaodich).getLoai());
        statement.setString(4, ((GiaoDichNha) giaodich).getDiaChi());
        statement.setDouble(5, giaodich.getDienTich());
        statement.setDouble(6, giaodich.getThanhTien()); // Bổ sung thành tiền
        statement.setInt(7, giaodich.getMaGd());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    @Override
    public void xoaGiaodich(int maGd) {
        String sql = "DELETE FROM GiaoDichNha WHERE maGd = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, maGd);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int demSoLuongTheoLoai(String loai) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM GiaoDichNha WHERE loaiNha = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loai);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public double tinhTrungBinhThanhTienGiaodich() {
        double trungBinhThanhTien = 0;
        String sql = "SELECT AVG(thanhTien) AS trungBinhThanhTien FROM GiaoDichNha";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                trungBinhThanhTien = resultSet.getDouble("trungBinhThanhTien");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trungBinhThanhTien;
    }
    @Override
    public double tinhTongGiaTri() {
        double tongGiaTri = 0.0;
        String sql = "SELECT SUM(thanhTien) AS tongGiaTri FROM GiaoDichNha";
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                tongGiaTri = resultSet.getDouble("tongGiaTri");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongGiaTri;
    }

    @Override
    public List<GiaoDichModel> layDanhSachGiaodich() {
      List<GiaoDichModel> list = new ArrayList<>();
        String sql = "SELECT * FROM GiaoDichNha";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int maGd = resultSet.getInt("maGd");
                LocalDate ngayGd = resultSet.getDate("ngayGd").toLocalDate();
                double donGia = resultSet.getDouble("donGia");
                double dienTich = resultSet.getDouble("dienTich");
                String diaChi = resultSet.getString("diaChi");
                String loaiNha = resultSet.getString("loaiNha");
                GiaoDichNha giaodich = new GiaoDichNha(maGd, ngayGd, donGia, dienTich, loaiNha, diaChi);
                giaodich.tinhThanhTien();
                list.add(giaodich);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<GiaoDichModel> timGiaoDich(int maGd) {
        List<GiaoDichModel> list = new ArrayList<>();
        String sql = "SELECT * FROM GiaoDichNha WHERE maGd = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, maGd);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LocalDate ngayGd = resultSet.getDate("ngayGd").toLocalDate();
                double donGia = resultSet.getDouble("donGia");
                double dienTich = resultSet.getDouble("dientich");
                String diaChi = resultSet.getString("diaChi");
                String loaiNha = resultSet.getString("loaiNha");
                GiaoDichNha giaodich = new GiaoDichNha(maGd, ngayGd, donGia, dienTich, loaiNha, diaChi);
                list.add(giaodich);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // ngayGd = ?, donGia = ?, loaiNha = ?, diaChi = ?, dientich = ?, thanhTien
    @Override
    public List<GiaoDichModel> xuatHoaDonTheoThangNam(int thang, int nam) {
        List<GiaoDichModel> list = new ArrayList<>();
        String sql = "SELECT maGd, ngayGd, donGia, loaiNha, diaChi, dienTich, thanhTien FROM GiaoDichNha " +
             "WHERE MONTH(ngayGd) = ? AND YEAR(ngayGd) = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, thang);
            statement.setInt(2, nam);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int maGd = resultSet.getInt("maGd");
                LocalDate ngayGd = resultSet.getDate("ngayGd").toLocalDate();
                double donGia = resultSet.getDouble("donGia");
                double dienTich = resultSet.getDouble("dienTich");
                String loaiNha = resultSet.getString("loaiNha");
                String diaChi = resultSet.getString("diaChi"); 
                GiaoDichModel giaoDich = new GiaoDichNha(maGd, ngayGd, donGia, dienTich, loaiNha, diaChi);
                list.add(giaoDich);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean kiemTraMaGiaoDichTonTai(int maGd) {
        List<GiaoDichModel> danhSachGiaoDich = layDanhSachGiaodich();
        for (GiaoDichModel gd : danhSachGiaoDich) {
            if (gd.getMaGd() == maGd) {
                return true; // Mã giao dịch đã tồn tại
            }
        }
        return false; // Mã giao dịch chưa tồn tại
    }

}