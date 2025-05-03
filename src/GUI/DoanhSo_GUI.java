package GUI;

import BUS.DoanhSoBUS;
import DTO.DoanhSoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoanhSo_GUI extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private DoanhSoBUS bus;
    private JTextField txtTuNgay, txtDenNgay;

    public DoanhSo_GUI() {
        setLayout(new BorderLayout());

        bus = new DoanhSoBUS();
        model = new DefaultTableModel(new String[]{"Tên Sản Phẩm", "Số Lượng Bán"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel lọc
        JPanel filterPanel = new JPanel();
        txtTuNgay = new JTextField(8);
        txtDenNgay = new JTextField(8);
        JButton btnLoc = new JButton("Lọc theo khoảng");

        filterPanel.add(new JLabel("Từ ngày (yyyy-MM-dd):"));
        filterPanel.add(txtTuNgay);
        filterPanel.add(new JLabel("Đến ngày:"));
        filterPanel.add(txtDenNgay);
        filterPanel.add(btnLoc);

        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        hienThiTatCa();

        btnLoc.addActionListener(e -> {
            String tuNgay = txtTuNgay.getText().trim();
            String denNgay = txtDenNgay.getText().trim();
            if (!tuNgay.matches("\\d{4}-\\d{2}-\\d{2}") || !denNgay.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng yyyy-MM-dd");
                return;
            }
            hienThiTheoKhoang(tuNgay, denNgay);
        });
    }

    private void hienThiTatCa() {
        model.setRowCount(0);
        for (DoanhSoDTO ds : bus.laySanPhamBanChay()) {
            model.addRow(new Object[]{ds.getTenSanPham(), ds.getSoLuongBan()});
        }
    }

    private void hienThiTheoKhoang(String tuNgay, String denNgay) {
        model.setRowCount(0);
        for (DoanhSoDTO ds : bus.laySanPhamBanChayTheoKhoang(tuNgay, denNgay)) {
            model.addRow(new Object[]{ds.getTenSanPham(), ds.getSoLuongBan()});
        }
    }
}
