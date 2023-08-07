package presentation.View.Dat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import domain.GiaoDichModel;
import presentation.View.View;

import java.awt.*;
import java.util.List;

public class ViewDat extends View {
private List<GiaoDichModel> giaoDichList;
private JTable table;
private DefaultTableModel tableModel;
private JTextField idTextField;
private JTextField ngayGiaoDichTextField;
private JTextField donGiaTextField;
private JComboBox<?> loaiDatComboBox ;
private JTextField dienTichTextField;
private JButton addButton;
private JButton editButton;
private JButton deleteButton;
private JButton findButton;
private JButton saveButton;
private JButton totalButton;
private JButton meanButton;
private JButton hondonButton;

public ViewDat() {
    setTitle("Giao Dịch Đất");
    setSize(800, 600);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

    // Create JTable to display
    tableModel = new DefaultTableModel();
    tableModel.setColumnIdentifiers(new String[]{"Mã giao dịch", "Ngày giao dịch", "Đơn giá", "Loại Đất", "Diện tích", "Thành tiền"});
    table = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    // Create JPanel for input fields and buttons
    JPanel inputPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.LINE_END;
    inputPanel.add(new JLabel("Mã giao dịch: "), gbc);

    gbc.gridy++;
    inputPanel.add(new JLabel("Ngày giao dịch: "), gbc);

    gbc.gridy++;
    inputPanel.add(new JLabel("Đơn giá: "), gbc);

    gbc.gridy++;
    inputPanel.add(new JLabel("Loại Đất: "), gbc);
    gbc.gridy++;
    inputPanel.add(new JLabel("Diện tích: "), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.LINE_START;
    idTextField = new JFormattedTextField();
    idTextField.setColumns(15);
    inputPanel.add(idTextField, gbc);

    gbc.gridy++;
    ngayGiaoDichTextField = new JFormattedTextField();
    ngayGiaoDichTextField.setColumns(15);
    inputPanel.add(ngayGiaoDichTextField, gbc);

    gbc.gridy++;
    donGiaTextField = new JFormattedTextField();
    donGiaTextField.setColumns(15);
    inputPanel.add(donGiaTextField, gbc);

    gbc.gridy++;
    loaiDatComboBox = new JComboBox<>(new String[]{"A","B","C"});
    inputPanel.add(loaiDatComboBox, gbc);

    gbc.gridy++;
    dienTichTextField = new JFormattedTextField();
    dienTichTextField.setColumns(15);
    inputPanel.add(dienTichTextField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    addButton = new JButton("Thêm");
    editButton = new JButton("Sửa");
    deleteButton = new JButton("Xóa");
    findButton = new JButton("Tìm kiếm");
    saveButton = new JButton("Lưu");
    totalButton = new JButton("Tổng");
    meanButton = new JButton("Trung bình");
    hondonButton = new JButton("Xuất hóa đơn");
    JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
    buttonPanel.add(addButton);
    buttonPanel.add(editButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(findButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(totalButton);
    buttonPanel.add(meanButton);
    buttonPanel.add(hondonButton);
    inputPanel.add(buttonPanel, gbc);

    add(inputPanel, BorderLayout.SOUTH);
}

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayGiaoDich(List<GiaoDichModel> giaoDichList) {
        this.giaoDichList = giaoDichList;
        DefaultTableModel Dat = (DefaultTableModel)table.getModel();
        Dat.setRowCount(0); // Clear existing rows
        for (GiaoDichModel giaoDich : giaoDichList) {
            Object[] rowData = {
                    giaoDich.getMaGd(),
                    giaoDich.getNgayGd(), // Thêm dòng này để lấy ngày giao dịch
                    giaoDich.getDonGia(),
                    giaoDich.getLoai(),
                    giaoDich.getDienTich(),
                    giaoDich.getThanhTien()
            };
            Dat.addRow(rowData);
        }
    }

    @Override
    public JTextField getIdTextField() {
        return idTextField;
    }

    @Override
    public JTextField getNgayGiaoDichTextField() {
        return ngayGiaoDichTextField;
    }

    @Override
    public JTextField getDonGiaTextField() {
        return donGiaTextField;
    }

    @Override
    public JComboBox<?> getLoaiComboBox() {
        return loaiDatComboBox;
    }

    @Override
    public JTextField getDienTichTextField() {
        return dienTichTextField;
    }

    @Override
    public void themGiaoDich(GiaoDichModel giaoDich) {
        giaoDichList.add(giaoDich);
        Object[] rowData = {
                giaoDich.getMaGd(),
                giaoDich.getNgayGd(),
                giaoDich.getDonGia(),
                giaoDich.getLoai(),
                giaoDich.getDienTich(),
                giaoDich.getThanhTien()
        };
        tableModel.addRow(rowData);
    }


    @Override
    public void showTongThanhTien(double tongThanhTien) {
        JOptionPane.showMessageDialog(this, "Tổng thành tiền: " + tongThanhTien);
    }

    @Override
    public void showTrungBinhThanhTien(double trungBinhThanhTien) {
        JOptionPane.showMessageDialog(this, "Trung bình thành tiền: " + trungBinhThanhTien);
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public JButton getEditButton() {
        return editButton;
    }

    @Override
    public JButton getDeleteButton() {
        return deleteButton;
    }

    @Override
    public JButton getFindButton() {
        return findButton;
    }

    @Override
    public JButton getSaveButton() {
        return saveButton;
    }

    @Override
    public JButton getTotalButton() {
        return totalButton;
    }

    @Override
    public JButton getMeanButton() {
        return meanButton;
    }

    @Override
    public JButton getHondonButton() {
        return hondonButton;
    }

    @Override
    public JTable getTable() {
        return table;
    }
    @Override
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    @Override
   public JTextField getDiachiTextField() {
    JTextField diachiTextField = new JTextField(); 
    return diachiTextField; 
}

}
    
