package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangBUS {
    private KhachHangDAO dao = new KhachHangDAO();

    public List<KhachHangDTO> layDanhSach() {
        try {
            return dao.getAllKhachHang();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void xuLyThem(String ten, String diaChi) {
        if (ten.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            dao.insertKhachHang(new KhachHangDTO(ten, diaChi));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại.");
        }
    }

    public void xuLyXoa(int maKH) {
        try {
            dao.deleteKhachHang(maKH);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại.");
        }
    }

    public void xuLySua(int maKH, String ten, String diaChi) {
        if (ten.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            dao.updateKhachHang(new KhachHangDTO(maKH, ten, diaChi));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sửa khách hàng thất bại.");
        }
    }

    public List<KhachHangDTO> timKiemTheoTen(String tuKhoa) {
        List<KhachHangDTO> ketQua = new ArrayList<>();
        try {
            List<KhachHangDTO> danhSach = dao.getAllKhachHang();
            for (KhachHangDTO kh : danhSach) {
                if (kh.getTenKH().toLowerCase().contains(tuKhoa.toLowerCase())) {
                    ketQua.add(kh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
