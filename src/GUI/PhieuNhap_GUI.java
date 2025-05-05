package GUI;

import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class PhieuNhap_GUI extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnTaoPhieuNhap;

    public PhieuNhap_GUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Quản lý phiếu nhập"));

        // Bảng dữ liệu
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã Phiếu Nhập", "Mã NCC", "Mã NV", "Ngày Nhập"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel nút
        JPanel panelButtons = new JPanel();
        btnTaoPhieuNhap = new JButton("Tạo phiếu nhập kho");

        panelButtons.add(btnTaoPhieuNhap);

        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        // Load dữ liệu
        loadPhieuNhap();

        // Sự kiện tạo phiếu
        btnTaoPhieuNhap.addActionListener(e -> {
            NhapPhieuNhapFrame nhapFrame = new NhapPhieuNhapFrame();
            nhapFrame.setVisible(true);

            // Lắng nghe sự kiện đóng frame để reload lại bảng
            nhapFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPhieuNhap();
                }
            });
        });
    }

    private void loadPhieuNhap() {
        model.setRowCount(0); // Xóa bảng
        List<PhieuNhapDTO> list = PhieuNhapBUS.getAllPhieuNhap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (PhieuNhapDTO pn : list) {
            model.addRow(new Object[]{
                pn.getMaPhieuNhap(),
                pn.getMaNCC(),
                pn.getMaNV(),
                sdf.format(pn.getNgayNhap())
            });
        }
    }
}
