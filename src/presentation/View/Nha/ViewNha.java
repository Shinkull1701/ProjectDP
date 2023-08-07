package presentation.View.Nha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import domain.GiaoDichModel;
import presentation.View.View;

import java.awt.*;
import java.util.List;

public class ViewNha extends View {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton saveButton;
    private JButton totalButton;
    private JButton meanButton;
    private JButton hondonButton;
    private JTextField idTextField;
    private JTextField ngayGiaoDichTextField;
    private JTextField donGiaTextField;
    private JTextField diaChiTextField;
    private JTextField dienTichTextField;
    private JComboBox<String> loaiNhaComboBox;
    private List<GiaoDichModel> giaoDichList;
    public ViewNha() {
        // Initialize JFrame
        setTitle("Giao Dịch Nha");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create JTable to display
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã giao dịch", "Ngày giao dịch", "Đơn giá", "Nhà", "Địa chỉ", "Diện tích", "Thanh tien"});
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
        inputPanel.add(new JLabel("Loại nhà: "), gbc);

        gbc.gridy++;
        inputPanel.add(new JLabel("Địa chỉ: "), gbc);

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
        loaiNhaComboBox = new JComboBox<>(new String[]{"Cao cấp", "Thường"});
        inputPanel.add(loaiNhaComboBox, gbc);

        gbc.gridy++;
        diaChiTextField = new JFormattedTextField();
        diaChiTextField.setColumns(15);
        inputPanel.add(diaChiTextField, gbc);

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
        return loaiNhaComboBox;
    }
    
    @Override
    public JTextField getDienTichTextField() {
        return dienTichTextField;
    }
    
     @Override
  public JTextField getDiachiTextField(){
        return diaChiTextField;
    }
    @Override
    public void themGiaoDich(GiaoDichModel giaoDich) {
        giaoDichList.add(giaoDich);
        Object[] rowData = {
                giaoDich.getMaGd(),
                giaoDich.getNgayGd(),
                giaoDich.getDonGia(),
                giaoDich.getLoai(),
                giaoDich.getDiaChi(),
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
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    @Override
    public void displayGiaoDich(List<GiaoDichModel> giaoDichList) {
        this.giaoDichList = giaoDichList;
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0); // Clear existing rows
    
        for (GiaoDichModel giaoDich : giaoDichList) {
            Object[] rowData = {
                    giaoDich.getMaGd(),
                giaoDich.getNgayGd(),
                giaoDich.getDonGia(),
                giaoDich.getLoai(),
                giaoDich.getDiaChi(),
                giaoDich.getDienTich(),
                giaoDich.getThanhTien()
            };
            model.addRow(rowData);
        }
    }


 
 
}