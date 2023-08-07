package domain;

import java.time.LocalDate;

public class GiaoDichDat extends GiaoDichModel {
    private String loaiDat;

    public GiaoDichDat(int maGd, LocalDate ngayGd, double donGia, String loaiDat, double dienTich) {
        super(maGd, ngayGd, donGia, dienTich);
        this.loaiDat = loaiDat;
        tinhThanhTien(); // Tính toán giá trị thanhTien trong constructor
    }

    @Override
    public String getLoai() {
        return loaiDat;
    }

    @Override
    public void tinhThanhTien() {
        if (loaiDat.equals("A")) {
            setThanhTien(getDienTich() * getDonGia() * 1.5);
        } else {
            setThanhTien(getDienTich() * getDonGia());
        }
    }
}