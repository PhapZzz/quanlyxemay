package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.ChitietSanPhamDAO;
import DAO.PhieuNhapDAO;
import DTO.chitietphieunhapDTO;
import DTO.danhsachxe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.print.Printable;


public class InPhieuNhap extends JFrame {
    private JLabel lblMaPhieu, lblNgayNhap, lblTenNCC, lblDiaChi, lblSDT, lblNhanVien, lblTongTien;
    private JTable tableChiTiet;
    private JButton btnIn, btnDong;

    public InPhieuNhap(String maPhieuNhap) {
        setTitle("Phiếu nhập kho");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Truy vấn dữ liệu
        PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
        ChitietSanPhamDAO chiTietDAO = new ChitietSanPhamDAO();

        chitietphieunhapDTO phieu = phieuNhapDAO.getThongTinPhieuNhap(maPhieuNhap);
        List<danhsachxe> chiTietList = chiTietDAO.getChiTietPhieuNhapbymaphieunhap(maPhieuNhap);

        // Panel thông tin
        JPanel panelThongTin = new JPanel(new GridLayout(3, 2, 10, 5));
        lblMaPhieu = new JLabel("Mã phiếu: " + phieu.getMaPhieuNhap());
        lblNgayNhap = new JLabel("Ngày nhập: " + phieu.getNgayNhap());
        lblTenNCC = new JLabel("Nhà cung cấp: " + phieu.getTenNCC());
        lblDiaChi = new JLabel("Địa chỉ: " + phieu.getDiaChi());
        lblSDT = new JLabel("SĐT: " + phieu.getSdt());
        lblNhanVien = new JLabel("Nhân viên: " + phieu.getTenNV());

        panelThongTin.setBorder(BorderFactory.createTitledBorder(" Thông tin phiếu nhập "));
        panelThongTin.add(lblMaPhieu);
        panelThongTin.add(lblNgayNhap);
        panelThongTin.add(lblTenNCC);
        panelThongTin.add(lblSDT);
        panelThongTin.add(lblDiaChi);
        panelThongTin.add(lblNhanVien);

        add(panelThongTin, BorderLayout.NORTH);

        // Bảng chi tiết
        String[] columnNames = {"STT", "Tên xe", "Màu", "Số khung", "Số máy","Trạng thái", "Đơn giá"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        int stt = 1;
        double tongTien = 0;
        for (danhsachxe item : chiTietList) {
            model.addRow(new Object[]{
                    stt++,
                    item.getTenXe(),
                    item.getColor(),
                    item.getSoKhung(),
                    item.getSoMay(),
                    item.getTrangThai(),
                    String.format("%,.0f", item.getDonGia())
            });
            tongTien += item.getDonGia();
        }
        tableChiTiet = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableChiTiet);
        add(scrollPane, BorderLayout.CENTER);

        // Panel dưới
        JPanel panelBottom = new JPanel(new BorderLayout());
        lblTongTien = new JLabel("Tổng tiền: " + String.format("%,.0f VND", tongTien));
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 14));

        btnIn = new JButton("In phiếu");
        btnDong = new JButton("Đóng");

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnIn);
        panelBtn.add(btnDong);

        panelBottom.add(lblTongTien, BorderLayout.WEST);
        panelBottom.add(panelBtn, BorderLayout.EAST);

        add(panelBottom, BorderLayout.SOUTH);

        // Sự kiện
        btnDong.addActionListener(e -> dispose());
        btnIn.addActionListener(e -> inPhieu());

        setVisible(true);
    }

    private void inPhieu() {
        try {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            
            // Đặt Printable cho PrinterJob
            printerJob.setPrintable((Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
                if (pageIndex >= 1) {
                    return Printable.NO_SUCH_PAGE;
                }
    
                // Cài đặt margin
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    
                // In toàn bộ nội dung JFrame
                printComponent(g2d, (JComponent) this.getContentPane());
    
                return Printable.PAGE_EXISTS;
            });
    
            // Hiển thị hộp thoại chọn máy in
            if (printerJob.printDialog()) {
                printerJob.print();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể in phiếu: " + ex.getMessage());
        }
    }
    
    private void printComponent(Graphics2D g2d, JComponent component) {
        // Phương thức in toàn bộ component
        component.paint(g2d);
    }
    

}
