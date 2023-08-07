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

        import domain.GiaoDichDat;
        import domain.GiaoDichModel;

        public class GiaoDichDatDAO extends GiaoDichDAO {
            
            public GiaoDichDatDAO(Connection connection) {
                super(connection);
            }
            
            @Override
            public void themGiaodich(GiaoDichModel giaodich) {
                String sql = "INSERT INTO GiaoDichDat(maGd, ngayGd, donGia, dienTich, loaiDat, thanhTien)" +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, giaodich.getMaGd());
                    statement.setDate(2, Date.valueOf(giaodich.getNgayGd()));
                    statement.setDouble(3, giaodich.getDonGia());
                    statement.setDouble(4, giaodich.getDienTich());
                    statement.setString(5, ((GiaoDichDat)giaodich).getLoai());
                    statement.setDouble(6, giaodich.getThanhTien());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            @Override
             public void suaGiaodich(GiaoDichModel giaodich) {
               String sql = "UPDATE GiaoDichDat SET ngayGd = ?, donGia = ?, dienTich = ?, loaiDat = ?, thanhTien = ? " +
                 "WHERE maGd = ?";
                try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setDate(1, Date.valueOf(giaodich.getNgayGd()));
                    statement.setDouble(2, giaodich.getDonGia());
                    statement.setDouble(3, giaodich.getDienTich());
                    statement.setString(4, ((GiaoDichDat) giaodich).getLoai());
                    statement.setDouble(5, giaodich.getThanhTien()); 
                    statement.setInt(6, giaodich.getMaGd());
                    statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
            @Override
            public void xoaGiaodich(int maGd) {
                String sql = "DELETE FROM GiaoDichDat WHERE maGd = ?";
                try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, maGd);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }     
            @Override
            public List<GiaoDichModel> layDanhSachGiaodich() {
            List<GiaoDichModel> list = new ArrayList<>();
            String sql = "SELECT * FROM GiaoDichDat";
            try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            int maGd = resultSet.getInt("maGd");
            LocalDate ngayGd = resultSet.getDate("ngayGd").toLocalDate();
            double donGia = resultSet.getDouble("donGia");
            double dienTich = resultSet.getDouble("dienTich");
            String loaiDat = resultSet.getString("loaiDat");
            GiaoDichDat giaodich = new GiaoDichDat(maGd, ngayGd, donGia, loaiDat, dienTich);
            list.add(giaodich);
            }
            } catch (SQLException e) {e.printStackTrace();
            }return list;
            }
            

            public int demSoLuongTheoLoai(String loai) {
                int count = 0;
                String sql = "SELECT COUNT(*) FROM GiaoDich WHERE loai = ?";
                try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, loai);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        count = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return count;
            }
            
            public double tinhTrungBinhThanhTienGiaodich() {
                double trungBinh = 0;
                String sql = "SELECT AVG(thanhTien) FROM GiaoDichDat";
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    if (resultSet.next()) {
                        trungBinh = resultSet.getDouble(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return trungBinh;
            }

            @Override
            public List<GiaoDichModel> timGiaoDich(int maGd) {
                List<GiaoDichModel> list = new ArrayList<>();
                String sql = "SELECT * FROM GiaoDichDat WHERE maGd = ?";
                try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, maGd);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        LocalDate ngayGd = resultSet.getDate("ngayGd").toLocalDate();
                        double donGia = resultSet.getDouble("donGia");
                        double dienTich = resultSet.getDouble("dienTich");
                        String loaiDat = resultSet.getString("loaiDat");
                        GiaoDichDat giaodich = new GiaoDichDat(maGd, ngayGd, donGia, loaiDat, dienTich);
                        list.add(giaodich);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return list;
            }
            @Override
            public double tinhTongGiaTri() {
                double tongGiaTri = 0.0;
                String sql = "SELECT SUM(thanhTien) AS tongGiaTri FROM GiaoDichDat";
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
        public List<GiaoDichModel> xuatHoaDonTheoThangNam(int thang, int nam) {
            List<GiaoDichModel> list = new ArrayList<>();
            String sql = "SELECT maGd, ngayGd, donGia, dienTich, loaiDat, thanhTien FROM GiaoDichDat " +
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
                    String loaiDat = resultSet.getString("loaiDat");
        
                    GiaoDichModel giaoDich = new GiaoDichDat(maGd, ngayGd, donGia, loaiDat, dienTich);
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