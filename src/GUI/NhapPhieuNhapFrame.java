package GUI;

import BUS.ChitietSanPham_BUS;
import BUS.PhieuNhapBUS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NhapPhieuNhapFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaPN,txtMaNCC,txtMaNV,txtNgay;

    public NhapPhieuNhapFrame() {
        setTitle("Tạo phiếu nhập");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        //setLayout(new GridBagLayout());

        JPanel toPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMaPN = new JLabel("Mã phiếu nhập:");
        txtMaPN = new JTextField(20);

        JLabel lblMaNCC = new JLabel("Mã NCC:");
        txtMaNCC = new JTextField(20);

        JLabel lblMaNV = new JLabel("Mã NV:");
        txtMaNV = new JTextField(20);

        JLabel lblNgay = new JLabel("Ngày nhập:");
        txtNgay = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        txtNgay.setEditable(false);

        JButton btnLuu = new JButton("Lưu phiếu nhập");
        JButton bthAddRow = new JButton("Thêm dòng");
        JButton btnRemoveRow = new JButton("Xóa dòng");
        // Sắp xếp layout
        gbc.gridx = 0; gbc.gridy = 0;
        toPanel.add(lblMaPN, gbc);
        gbc.gridx = 1;
        toPanel.add(txtMaPN, gbc);

        gbc.gridx = 0; gbc.gridy++;
        toPanel.add(lblMaNCC, gbc);
        gbc.gridx = 1;
        toPanel.add(txtMaNCC, gbc);

        gbc.gridx = 0; gbc.gridy++;
        toPanel.add(lblMaNV, gbc);
        gbc.gridx = 1;
        toPanel.add(txtMaNV, gbc);

        gbc.gridx = 0; gbc.gridy++;
        toPanel.add(lblNgay, gbc);
        gbc.gridx = 1;
        toPanel.add(txtNgay, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        toPanel.add(btnLuu, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        toPanel.add(bthAddRow, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        toPanel.add(btnRemoveRow, gbc);
        
        model = new DefaultTableModel(new Object[]{"Mã chi tiết xe","Loại xe", "Số Khung", "Số Máy", "Trạng Thái","Màu", "Đơn giá"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(toPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        // thêm dòng
        bthAddRow.addActionListener(e -> model.addRow(new Object[]{"", "", "","","","",""}));

        // xóa dòng 
        btnRemoveRow.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) model.removeRow(row);
        });

        // Sự kiện lưu
        btnLuu.addActionListener((ActionEvent e) -> {
            String maPN = txtMaPN.getText().trim();
            String maNCC = txtMaNCC.getText().trim();
            String maNV = txtMaNV.getText().trim();

            if (maPN.isEmpty() || maNCC.isEmpty() || maNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
                return;
            }
            int maNv = Integer.parseInt(maNV);

            try {
                Date ngayNhap = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgay.getText());
                if (PhieuNhapBUS.themPhieuNhap(maPN, maNCC, maNv, ngayNhap)) {
                    xulythem();
                    JOptionPane.showMessageDialog(this, "Tạo phiếu nhập thành công!");
                    
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Tạo phiếu nhập thất bại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!");
                return;
            }
        });
    }

    public void xulythem(){
        // lấy dữ liệu table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String machitietxe = model.getValueAt(i, 0).toString();
                String loaixe = model.getValueAt(i, 1).toString();
                String sokhung = model.getValueAt(i, 2).toString();
                String somay = model.getValueAt(i, 3).toString();
                String trangthai = model.getValueAt(i, 4).toString();
                String mau = model.getValueAt(i, 5).toString();
                Double unitPrice = Double.parseDouble(model.getValueAt(i, 6).toString());
                BigDecimal Pricesp = BigDecimal.valueOf(unitPrice).setScale(2, RoundingMode.HALF_UP);
                String img = null;
                String maphieunhap =txtMaPN.getText().trim();
                ChitietSanPham_BUS a = new ChitietSanPham_BUS();
                a.themSanPham(machitietxe, loaixe, sokhung, somay, trangthai, maphieunhap, mau,"s");
            }

    }
}
