package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BUS.DonHangBUS;
import DTO.DonHangDTO;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DonHang_GUI extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaDH, txtNgayMua, txtTenKH, txtTongTien;
    private DonHangBUS donHangBUS = new DonHangBUS();

    public DonHang_GUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã Đơn", "Ngày Mua", "Tên Khách", "Tổng Tiền"});
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Form input
        JPanel panelLeft = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMaDH = new JTextField(15);
        txtNgayMua = new JTextField(15);
        txtTenKH = new JTextField(15);
        txtTongTien = new JTextField(15);

        addFormRow(panelLeft, gbc, 0, new JLabel("Mã đơn hàng:"), txtMaDH);
        addFormRow(panelLeft, gbc, 1, new JLabel("Ngày mua (yyyy-MM-dd):"), txtNgayMua);
        addFormRow(panelLeft, gbc, 2, new JLabel("Tên khách hàng:"), txtTenKH);
        addFormRow(panelLeft, gbc, 3, new JLabel("Tổng tiền:"), txtTongTien);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnTim = new JButton("Tìm kiếm");
        JButton btnLamMoi = new JButton("Làm mới");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTim);
        buttonPanel.add(btnLamMoi);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelLeft.add(buttonPanel, gbc);

        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        wrapperPanel.add(panelLeft);

        add(wrapperPanel, BorderLayout.WEST);

        // Table click event
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtMaDH.setText(model.getValueAt(row, 0).toString());
                    txtNgayMua.setText(model.getValueAt(row, 1).toString());
                    txtTenKH.setText(model.getValueAt(row, 2).toString());
                    txtTongTien.setText(model.getValueAt(row, 3).toString().replaceAll("[^\\d.]", ""));
                }
            }
        });

        // Button events
        btnThem.addActionListener(e -> {
            if (validateFields()) {
                DonHangDTO dh = new DonHangDTO(
                    txtMaDH.getText(),
                    txtNgayMua.getText(),
                    txtTenKH.getText(),
                    Double.parseDouble(txtTongTien.getText())
                );
                if (donHangBUS.themDonHang(dh)) {
                    showMessage("Thêm thành công!");
                    loadData();
                } else {
                    showMessage("Thêm thất bại!");
                }
            }
        });

        btnSua.addActionListener(e -> {
            if (validateFields()) {
                DonHangDTO dh = new DonHangDTO(
                    txtMaDH.getText(),
                    txtNgayMua.getText(),
                    txtTenKH.getText(),
                    Double.parseDouble(txtTongTien.getText())
                );
                if (donHangBUS.suaDonHang(dh)) {
                    showMessage("Sửa thành công!");
                    loadData();
                } else {
                    showMessage("Sửa thất bại!");
                }
            }
        });

        btnXoa.addActionListener(e -> {
            String ma = txtMaDH.getText();
            if (!ma.isEmpty()) {
                if (donHangBUS.xoaDonHang(ma)) {
                    showMessage("Xóa thành công!");
                    loadData();
                } else {
                    showMessage("Xóa thất bại!");
                }
            }
        });

        btnTim.addActionListener(e -> {
            String keyword = txtTenKH.getText();
            List<DonHangDTO> list = donHangBUS.timKiemTheoTenKhachHang(keyword);
            model.setRowCount(0);
            for (DonHangDTO dh : list) {
                model.addRow(new Object[]{
                    dh.getMaDonHang(),
                    dh.getNgayMua(),
                    dh.getTenKhachHang(),
                    FormatUtil.formatCurrency(dh.getTongTien())
                });
            }
        });

        btnLamMoi.addActionListener(e -> {
            clearFields();
            loadData();
        });

        loadData();
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, JLabel label, JTextField field) {
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private boolean validateFields() {
        if (txtMaDH.getText().isEmpty() || txtNgayMua.getText().isEmpty()
                || txtTenKH.getText().isEmpty() || txtTongTien.getText().isEmpty()) {
            showMessage("Vui lòng điền đầy đủ thông tin!");
            return false;
        }
        try {
            Double.parseDouble(txtTongTien.getText());
        } catch (NumberFormatException e) {
            showMessage("Tổng tiền không hợp lệ!");
            return false;
        }
        return true;
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void clearFields() {
        txtMaDH.setText("");
        txtNgayMua.setText("");
        txtTenKH.setText("");
        txtTongTien.setText("");
    }

    private void loadData() {
        List<DonHangDTO> list = donHangBUS.getAllDonHang();
        model.setRowCount(0);
        for (DonHangDTO dh : list) {
            model.addRow(new Object[]{
                dh.getMaDonHang(),
                dh.getNgayMua(),
                dh.getTenKhachHang(),
                FormatUtil.formatCurrency(dh.getTongTien())
            });
        }
    }
}
