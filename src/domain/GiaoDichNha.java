package domain;

import java.time.LocalDate;

public class GiaoDichNha extends GiaoDichModel {
    private String loaiNha;
    private String diaChi;
    
    public GiaoDichNha(int maGd, LocalDate ngayGd, double donGia, double dienTich, String loaiNha, String diaChi) {
        super(maGd, ngayGd, donGia, dienTich);
        this.loaiNha = loaiNha;
        this.diaChi = diaChi;
        tinhThanhTien();
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    @Override
    public void tinhThanhTien() {
        if (loaiNha.equals("Cao cấp")) {
            super.setThanhTien(getDienTich() * getDonGia());
        } else {
            super.setThanhTien(getDienTich() * getDonGia() * 0.9);
        }
    }

    @Override
    public String getLoai() {
        return loaiNha;
    }
}