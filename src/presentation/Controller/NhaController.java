package presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data_access_object.GiaoDichDAO;
import domain.GiaoDichModel;
import domain.GiaoDichNha;
import presentation.View.View;
import presentation.View.Nha.EditNhaDialog;
import presentation.View.Nha.HoadonDialog;

public class NhaController {
    private GiaoDichDAO giaoDichDAO;
    private View view;
    public NhaController(GiaoDichDAO giaoDichDAO, View view) {
        this.giaoDichDAO = giaoDichDAO;
        this.view = view;
        view.getAddButton().addActionListener(new AddButtonListener());
        view.getEditButton().addActionListener(new EditButtonListener());
        view.getDeleteButton().addActionListener(new DeleteButtonListener());
        view.getFindButton().addActionListener(new FindButtonListener());
        view.getSaveButton().addActionListener(new SaveButtonListener());
        view.getTotalButton().addActionListener(new TotalButtonListener());
        view.getMeanButton().addActionListener(new MeanButtonListener());
        view.getHondonButton().addActionListener(new HoadonButtonListener());

        updateTable();
    }
    private void updateTable() {
            List<GiaoDichModel> danhSachGiaoDich = giaoDichDAO.layDanhSachGiaodich();
            view.displayGiaoDich(danhSachGiaoDich);
        }
    // Bộ lắng nghe sự kiện cho nút "Thêm"
    class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int maGiaoDich = Integer.parseInt(view.getIdTextField().getText());
                String ngayGiaoDichText = view.getNgayGiaoDichTextField().getText();
                double donGia = Double.parseDouble(view.getDonGiaTextField().getText());
                double dienTich = Double.parseDouble(view.getDienTichTextField().getText());
                String diaChi = view.getDiachiTextField().getText();
                String loaiNha = (String)view.getLoaiComboBox().getSelectedItem();

                if (giaoDichDAO.kiemTraMaGiaoDichTonTai(maGiaoDich)) {
                    JOptionPane.showMessageDialog(view, "Mã giao dịch đã tồn tại. Vui lòng chọn mã giao dịch khác.");
                    return;
                }

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate ngayGiaoDich = null;

                try {
                    ngayGiaoDich = LocalDate.parse(ngayGiaoDichText, dateFormatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(view, "Ngày giao dịch không hợp lệ. Vui lòng nhập đúng định dạng: yyyy-MM-dd.");
                    return;
                }

                String errorMessage = "";
                if (maGiaoDich <= 0) {
                    errorMessage += "- Mã giao dịch không hợp lệ.\n";
                }

                if (donGia <= 0) {
                    errorMessage += "- Đơn giá không hợp lệ.\n";
                }

                if (dienTich <= 0) {
                    errorMessage += "- Diện tích không hợp lệ.\n";
                }

                if (diaChi.isEmpty()) {
                    errorMessage += "- Địa chỉ không được để trống.\n";
                }

                if (loaiNha.isEmpty()) {
                    errorMessage += "- Loại nhà không được để trống.\n";
                }

                if (!errorMessage.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Vui lòng kiểm tra lại các trường dữ liệu:\n" + errorMessage);
                    return;
                }
               // Tao GiaoDichNha object
                   GiaoDichNha giaoDichNha = new GiaoDichNha(maGiaoDich, ngayGiaoDich, donGia, dienTich, loaiNha, diaChi);

                // Them GiaoDichNha vao database
                    giaoDichDAO.themGiaodich(giaoDichNha);

                     updateTable();

                JOptionPane.showMessageDialog(view, "Thêm giao dịch thành công!");
                } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đúng định dạng số cho các trường số liệu.");
            }
        }
    }
      // Bộ lắng nghe sự kiện cho nút "Sua"
    class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một giao dịch để sửa!");
                return;
            }
    
            // Lấy giá trị từ bảng
            int maGiaoDich = Integer.parseInt(view.getTable().getValueAt(selectedRow, 0).toString());
            LocalDate ngayGiaoDich = LocalDate.parse(view.getTable().getValueAt(selectedRow, 1).toString());
            double donGia = Double.parseDouble(view.getTable().getValueAt(selectedRow, 2).toString());
            String loaiNha = view.getTable().getValueAt(selectedRow, 3).toString();
            String diaChi = view.getTable().getValueAt(selectedRow, 4).toString();
            double dienTich = Double.parseDouble(view.getTable().getValueAt(selectedRow, 5).toString());
            
            // Hiển thị cửa sổ sửa đổi
            EditNhaDialog editDialog = new EditNhaDialog(maGiaoDich, ngayGiaoDich, donGia, loaiNha, dienTich, diaChi);
            if (editDialog.showDialog() == JOptionPane.OK_OPTION) {
                // Lấy giá trị đã chỉnh sửa từ cửa sổ sửa đổi
                maGiaoDich = editDialog.getMaGiaoDich();
                ngayGiaoDich = editDialog.getNgayGiaoDich();
                donGia = editDialog.getDonGia();
                String newLoaiNha = editDialog.getLoaiNha();
                dienTich = editDialog.getDienTich();
    
                // Tạo đối tượng GiaoDichModel đã chỉnh sửa
                GiaoDichModel giaoDich = new GiaoDichNha(maGiaoDich, ngayGiaoDich, donGia, dienTich, newLoaiNha, diaChi);
    
                // Sửa giao dịch
                giaoDichDAO.suaGiaodich(giaoDich);
                updateTable();
                JOptionPane.showMessageDialog(view, "Sửa giao dịch thành công!");
            }
        }
    }
    // Bộ lắng nghe sự kiện cho nút "Xóa"
    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một giao dịch để xóa!");
                return;
            }
            
            // Lấy giá trị từ cột mã giao dịch của hàng được chọn
            int maGiaoDich = (int) view.getTable().getValueAt(selectedRow, 0);
            giaoDichDAO.xoaGiaodich(maGiaoDich);
            
            // Xóa hàng được chọn trong JTable
            view.getTableModel().removeRow(selectedRow);
            
            JOptionPane.showMessageDialog(view, "Xóa giao dịch thành công!");
        }
    }
      // Bộ lắng nghe sự kiện cho nút "Tìm kiếm"
      class FindButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(view, "Nhập mã giao dịch:");
            if (input != null && !input.isEmpty()) {
                try {
                    int maGiaoDich = Integer.parseInt(input);
                    List<GiaoDichModel> giaoDichList = giaoDichDAO.timGiaoDich(maGiaoDich);
                    if (!giaoDichList.isEmpty()) {
                        showResultInTable(giaoDichList);
                    } else {
                        JOptionPane.showMessageDialog(view, "Không tìm thấy giao dịch có mã " + maGiaoDich + "!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Mã giao dịch không hợp lệ!");
                }
            }
        }
         // Hiển thị kết quả tìm kiếm trong bảng
    private void showResultInTable(List<GiaoDichModel> giaoDichList) {
        Object[][] data = new Object[giaoDichList.size()][7];

        for (int i = 0; i < giaoDichList.size(); i++) {
            GiaoDichModel giaoDich = giaoDichList.get(i);
            // Điền dữ liệu vào mỗi hàng của bảng
            data[i] = new Object[]{giaoDich.getMaGd(),giaoDich.getNgayGd(), giaoDich.getDonGia(), giaoDich.getLoai(), giaoDich.getDienTich(), 
                giaoDich.getDiaChi(), giaoDich.getThanhTien()};
        }

        // Tạo mô hình cho bảng
        String[] columnNames = {"Mã giao dịch", "Ngày giao dịch","Đơn giá" ,"Loại nhà", "Diện tích", "Địa chỉ", "Thành tiền"};
        JTable table = new JTable(data, columnNames);

        // Tạo cửa sổ mới để hiển thị bảng
        JFrame resultFrame = new JFrame("Kết quả tìm kiếm");
        resultFrame.add(new JScrollPane(table));
        resultFrame.pack();
        resultFrame.setVisible(true);
     }
    }
    class TotalButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double tongGiaTri = giaoDichDAO.tinhTongGiaTri();
            JOptionPane.showMessageDialog(view, "Tổng giá trị giao dịch: " + tongGiaTri);
        }
    }
    // Bộ lắng nghe sự kiện cho nút "Trung bình"
    class MeanButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double giaTriTrungBinh = giaoDichDAO.tinhTrungBinhThanhTienGiaodich();
            JOptionPane.showMessageDialog(view, "Giá trị trung bình của giao dịch: " + giaTriTrungBinh);
        }
    }
       // Bộ lắng nghe sự kiện cho nút "Lưu"
    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            giaoDichDAO.layDanhSachGiaodich();
            JOptionPane.showMessageDialog(view, "Lưu danh sách giao dịch thành công!");
        }
    }
     //xuat hoa don theo thang, nam
    class HoadonButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String monthInput = JOptionPane.showInputDialog(view, "Nhập tháng (1-12):");
            if (monthInput != null && !monthInput.isEmpty()) {
                String yearInput = JOptionPane.showInputDialog(view, "Nhập năm:");
                if (yearInput != null && !yearInput.isEmpty()) {
                    try {
                        int month = Integer.parseInt(monthInput);
                        int year = Integer.parseInt(yearInput);
    
                        // Kiểm tra tính hợp lệ của tháng và năm
                        if (month < 1 || month > 12) {
                            JOptionPane.showMessageDialog(view, "Tháng không hợp lệ!");
                            return;
                        }
    
                        // Gọi phương thức trong GiaoDichDAO để lấy danh sách giao dịch theo tháng và năm
                        List<GiaoDichModel> giaoDichList = giaoDichDAO.xuatHoaDonTheoThangNam(month, year);
    
                        // Create and show the HoadonDialog
                        HoadonDialog hoadonDialog = new HoadonDialog(view);
                        hoadonDialog.setGiaoDichList(giaoDichList);
                        hoadonDialog.setVisible(true);
    
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(view, "Năm phải là số nguyên!");
                    }
                }
            }
        }
    }
    public void displayData() {
        List<GiaoDichModel> danhSachGiaoDich = giaoDichDAO.layDanhSachGiaodich();
        view.displayGiaoDich(danhSachGiaoDich);
    }
}