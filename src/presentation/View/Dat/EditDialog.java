package presentation.View.Dat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditDialog extends JDialog {
    private JTextField maGiaoDichTextField;
    private JTextField ngayGiaoDichTextField;
    private JTextField donGiaTextField;
    private JComboBox<String> loaiDatComboBox;
    private JTextField dienTichTextField;
    private int dialogResult;

    public EditDialog(int maGiaoDich, LocalDate ngayGiaoDich, double donGia, String loaiDat, double dienTich) {
        setTitle("Sửa giao dịch");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);

        // Initialize components
        maGiaoDichTextField = new JTextField(String.valueOf(maGiaoDich));
        ngayGiaoDichTextField = new JTextField(ngayGiaoDich.toString());
        donGiaTextField = new JTextField(String.valueOf(donGia));
        loaiDatComboBox = new JComboBox<>(new String[]{"A", "B", "C"});
        loaiDatComboBox.setSelectedItem(loaiDat);
        dienTichTextField = new JTextField(String.valueOf(dienTich));

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
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Mã giao dịch:"));
        panel.add(maGiaoDichTextField);
        panel.add(new JLabel("Ngày giao dịch:"));
        panel.add(ngayGiaoDichTextField);
        panel.add(new JLabel("Đơn giá:"));
        panel.add(donGiaTextField);
        panel.add(new JLabel("Loại đất:"));
        panel.add(loaiDatComboBox);
        panel.add(new JLabel("Diện tích:"));
        panel.add(dienTichTextField);
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

    public String getLoaiDat() {
        return (String) loaiDatComboBox.getSelectedItem();
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
}