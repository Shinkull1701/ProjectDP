package presentation.View.Nha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditNhaDialog extends JDialog {
    private JTextField maGiaoDichTextField;
    private JTextField ngayGiaoDichTextField;
    private JTextField donGiaTextField;
    private JComboBox<String> loaiNhaComboBox;
    private JTextField dienTichTextField;
    private JTextField diaChiTextField;
    private int dialogResult;

    public EditNhaDialog(int maGiaoDich, LocalDate ngayGiaoDich, double donGia, String loaiNha, double dienTich, String diaChi) {
        setTitle("Sửa giao dịch");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);

        // Initialize components
        maGiaoDichTextField = new JTextField(String.valueOf(maGiaoDich));
        ngayGiaoDichTextField = new JTextField(ngayGiaoDich.toString());
        donGiaTextField = new JTextField(String.valueOf(donGia));
        loaiNhaComboBox = new JComboBox<>(new String[]{"Cao cấp", "Thường"});
        loaiNhaComboBox.setSelectedItem(loaiNha);
        dienTichTextField = new JTextField(String.valueOf(dienTich));
        diaChiTextField = new JTextField(diaChi);

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogResult = JOptionPane.YES_OPTION;
                dispose();
            }
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogResult = JOptionPane.NO_OPTION;
                dispose();
            }
        });

        // Create layout
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Mã giao dịch:"));
        panel.add(maGiaoDichTextField);
        panel.add(new JLabel("Ngày giao dịch:"));
        panel.add(ngayGiaoDichTextField);
        panel.add(new JLabel("Đơn giá:"));
        panel.add(donGiaTextField);
        panel.add(new JLabel("Loại nhà:"));
        panel.add(loaiNhaComboBox);
        panel.add(new JLabel("Diện tích:"));
        panel.add(dienTichTextField);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(diaChiTextField);
        panel.add(saveButton);
        panel.add(cancelButton);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public int showDialog() {
        setVisible(true);
        return dialogResult;
    }

    public int getMaGiaoDich() {
        String input = maGiaoDichTextField.getText();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã giao dịch phải là một số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public LocalDate getNgayGiaoDich() {
        String input = ngayGiaoDichTextField.getText();
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày giao dịch không hợp lệ. Định dạng phải là yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public double getDonGia() {
        String input = donGiaTextField.getText();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là một số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public String getLoaiNha() {
        return (String) loaiNhaComboBox.getSelectedItem();
    }

    public double getDienTich() {
        String input = dienTichTextField.getText();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Diện tích phải là một số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public String getDiaChi() {
        return diaChiTextField.getText();
    }
}