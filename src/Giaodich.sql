use giaodich;
CREATE TABLE GiaoDichDat (
  maGd INT PRIMARY KEY,
  ngayGd DATE,
  donGia DOUBLE,
  loaiDat VARCHAR(10),
  dienTich DOUBLE,
  thanhTien DOUBLE
);

CREATE TABLE GiaoDichNha (
  maGd INT PRIMARY KEY,
  ngayGd DATE,
  donGia DOUBLE,
  loaiNha VARCHAR(20),
  diaChi VARCHAR(100),
  dienTich DOUBLE,
  thanhTien DOUBLE
);