package presentation.View.Nha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import domain.GiaoDichModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HoadonDialog extends JDialog {
    private List<GiaoDichModel> giaoDichList;
    private JTable table;
    private DefaultTableModel tableModel;

    public HoadonDialog(JFrame parentFrame) {
        super(parentFrame, "Hóa đơn nhà", true);

        // Create the table
        String[] columnNames = {"Mã giao dịch", "Ngày giao dịch","Đơn giá" ,"Loại nhà", "Diện tích", "Địa chỉ", "Thành tiền"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Create the buttons
        JButton sumButton = new JButton("Tổng tiền");
        sumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double tongTien = 0;
                for (GiaoDichModel giaoDich : giaoDichList) {
                    tongTien += giaoDich.getThanhTien();
                }
                JOptionPane.showMessageDialog(HoadonDialog.this, "Tổng tiền: " + tongTien);
            }
        });

        JButton averageButton = new JButton("Trung bình");
        averageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double tongTien = 0;
                for (GiaoDichModel giaoDich : giaoDichList) {
                    tongTien += giaoDich.getThanhTien();
                }
                double trungBinh = tongTien / giaoDichList.size();
                JOptionPane.showMessageDialog(HoadonDialog.this, "Trung bình: " + trungBinh);
            }
        });

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sumButton);
        buttonPanel.add(averageButton);

        // Set the layout and add components to the dialog
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void setGiaoDichList(List<GiaoDichModel> giaoDichList) {
        this.giaoDichList = giaoDichList;

        // Clear the existing table data
        tableModel.setRowCount(0);

        // Add the new data to the table
        for (GiaoDichModel giaoDich : giaoDichList) {
            Object[] rowData = {giaoDich.getMaGd(),giaoDich.getNgayGd(), giaoDich.getDonGia(), giaoDich.getLoai(), 
                giaoDich.getDienTich(), giaoDich.getDiaChi(), giaoDich.getThanhTien()};
            tableModel.addRow(rowData);
        }
    }
}