package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienBUS {
    private NhanVienDAO dao = new NhanVienDAO();

    public List<NhanVienDTO> layDanhSach() {
        try {
            return dao.getAllNhanVien();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void xuLyThem(String ten, String chucVu) {
        if (ten.isEmpty() || chucVu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            dao.insertNhanVien(new NhanVienDTO(ten, chucVu));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại.");
        }
    }

    public void xuLyXoa(int maNV) {
        try {
            dao.deleteNhanVien(maNV);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại.");
        }
    }

    public void xuLySua(int maNV, String ten, String chucVu) {
        if (ten.isEmpty() || chucVu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            dao.updateNhanVien(new NhanVienDTO(maNV, ten, chucVu));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại.");
        }
    }

    public List<NhanVienDTO> timKiemTheoTen(String tuKhoa) {
        List<NhanVienDTO> ketQua = new ArrayList<>();
        try {
            List<NhanVienDTO> danhSach = dao.getAllNhanVien();
            for (NhanVienDTO nv : danhSach) {
                if (nv.getTenNV().toLowerCase().contains(tuKhoa.toLowerCase())) {
                    ketQua.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
