package domain;

import java.time.LocalDate;

public abstract class GiaoDichModel {
    private int maGd;
    private LocalDate ngayGd;
    private double donGia;
    private double dienTich;
    protected double thanhTien;
    protected String diaChi;
    public GiaoDichModel(int maGd, LocalDate ngayGd, double donGia, double dienTich) {
        this.maGd = maGd;
        this.ngayGd = ngayGd;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }

    public int getMaGd() {
        return maGd;
    }

    public LocalDate getNgayGd() {
        return ngayGd;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getDienTich() {
        return dienTich;
    }

    public abstract void tinhThanhTien();

    public abstract String getLoai();

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public String getDiaChi() {
        return diaChi;
    }
}