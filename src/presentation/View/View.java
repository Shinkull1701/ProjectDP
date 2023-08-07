package presentation.View;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import domain.GiaoDichModel;

public abstract class View extends JFrame{
    protected DefaultTableModel tableModel;
    protected JTable table;
    protected JButton addButton;
    protected JButton editButton;
    protected JButton deleteButton;
    protected JButton findButton;
    protected JButton saveButton;
    protected JButton totalButton;
    protected JButton meanButton;
    protected JButton hondonButton;
    protected JTextField idTextField;
    protected JTextField ngayGiaoDichTextField;
    protected JTextField donGiaTextField;
    protected JTextField loaiDatTextField;
    protected JTextField diaChiTextField;
    protected JTextField dienTichTextField;

    public abstract JTextField getIdTextField();

    public abstract JTextField getNgayGiaoDichTextField();

    public abstract JTextField getDonGiaTextField();

    public abstract JComboBox<?> getLoaiComboBox();

    public abstract JTextField getDienTichTextField();

    public abstract JTable getTable();

    public abstract JButton getAddButton();

    public abstract JButton getEditButton();

    public abstract JButton getDeleteButton();

    public abstract JButton getFindButton();

    public abstract JButton getSaveButton();

    public abstract JButton getTotalButton();

    public abstract JButton getMeanButton();

    public abstract JButton getHondonButton();

    public abstract DefaultTableModel getTableModel();

    public abstract void showMessage(String message);

    public abstract void displayGiaoDich(List<GiaoDichModel> giaoDichList);

    public abstract void showTrungBinhThanhTien(double trungBinhThanhTien);

    public abstract void showTongThanhTien(double tongThanhTien);

    public abstract void themGiaoDich(GiaoDichModel giaoDich);
    
    public abstract JTextField getDiachiTextField();
}