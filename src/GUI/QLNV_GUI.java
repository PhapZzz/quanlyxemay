package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import DTO.NhanVienDTO;
import BUS.NhanVienBUS;

public class QLNV_GUI extends JPanel {
    private JTable table;
    private JTextField txtTen, txtChucVu, txtTimKiem;
    private DefaultTableModel model;
    private NhanVienBUS bus;

    public QLNV_GUI() {
        bus = new NhanVienBUS();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Mã NV", "Tên NV", "Chức vụ"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panelTable = new JPanel(new BorderLayout());
        panelTable.add(scrollPane, BorderLayout.CENTER);

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTen = new JTextField(15);
        txtChucVu = new JTextField(15);
        txtTimKiem = new JTextField(15);

        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnSua = new JButton("Sửa");
        JButton btnTim = new JButton("Tìm");

        panelLeft.add(new JLabel("Tên nhân viên:"));
        panelLeft.add(txtTen);
        panelLeft.add(Box.createVerticalStrut(10));

        panelLeft.add(new JLabel("Chức vụ:"));
        panelLeft.add(txtChucVu);
        panelLeft.add(Box.createVerticalStrut(10));

        panelLeft.add(btnThem);
        panelLeft.add(Box.createVerticalStrut(5));
        panelLeft.add(btnXoa);
        panelLeft.add(Box.createVerticalStrut(5));
        panelLeft.add(btnSua);
        panelLeft.add(Box.createVerticalStrut(15));

        panelLeft.add(new JLabel("Tìm theo tên:"));
        panelLeft.add(txtTimKiem);
        panelLeft.add(Box.createVerticalStrut(5));
        panelLeft.add(btnTim);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelTable);
        splitPane.setDividerLocation(0.25);
        splitPane.setResizeWeight(0.25);
        add(splitPane, BorderLayout.CENTER);

        // Gọi các hàm xử lý từ BUS
        btnThem.addActionListener(e -> {
            bus.xuLyThem(txtTen.getText().trim(), txtChucVu.getText().trim());
            loadTable();
            clearForm();
        });

        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int ma = (int) model.getValueAt(row, 0);
                bus.xuLyXoa(ma);
                loadTable();
                clearForm();
            }
        });

        btnSua.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int ma = (int) model.getValueAt(row, 0);
                bus.xuLySua(ma, txtTen.getText().trim(), txtChucVu.getText().trim());
                loadTable();
                clearForm();
            }
        });

        btnTim.addActionListener(e -> {
            String tuKhoa = txtTimKiem.getText().trim();
            loadTableTimKiem(tuKhoa);
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtTen.setText(model.getValueAt(row, 1).toString());
                txtChucVu.setText(model.getValueAt(row, 2).toString());
            }
        });

        loadTable();
    }

    private void loadTable() {
        model.setRowCount(0);
        List<NhanVienDTO> list = bus.layDanhSach();
        if (list != null) {
            for (NhanVienDTO nv : list) {
                model.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), nv.getChucVu()});
            }
        }
    }

    private void loadTableTimKiem(String tuKhoa) {
        model.setRowCount(0);
        List<NhanVienDTO> list = bus.timKiemTheoTen(tuKhoa);
        for (NhanVienDTO nv : list) {
            model.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), nv.getChucVu()});
        }
    }

    private void clearForm() {
        txtTen.setText("");
        txtChucVu.setText("");
        txtTimKiem.setText("");
    }
}
