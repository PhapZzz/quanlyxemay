package DAO;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import DTO.DonHangDTO;
import Util.JDBC;

public class DonHangDAO {
    public List<DonHangDTO> getAll() {
        List<DonHangDTO> list = new ArrayList<>();
        try {
            Connection conn = JDBC.getConnection();
            String sql = "SELECT * FROM donhang";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new DonHangDTO(
                    rs.getString("MaDonHang"),
                    rs.getDate("NgayMua").toString(),
                    rs.getString("TenKhachHang"),
                    rs.getDouble("TongTien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(DonHangDTO dh) {
        try {
            Connection conn = JDBC.getConnection();
            String sql = "INSERT INTO donhang VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dh.getMaDonHang());
            stmt.setDate(2, Date.valueOf(dh.getNgayMua()));
            stmt.setString(3, dh.getTenKhachHang());
            stmt.setDouble(4, dh.getTongTien());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(DonHangDTO dh) {
        try {
            Connection conn = JDBC.getConnection();
            String sql = "UPDATE donhang SET NgayMua=?, TenKhachHang=?, TongTien=? WHERE MaDonHang=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dh.getNgayMua()));
            stmt.setString(2, dh.getTenKhachHang());
            stmt.setDouble(3, dh.getTongTien());
            stmt.setString(4, dh.getMaDonHang());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maDH) {
        try {
            Connection conn = JDBC.getConnection();
            String sql = "DELETE FROM donhang WHERE MaDonHang=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maDH);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DonHangDTO> searchByCustomerName(String name) {
        List<DonHangDTO> list = new ArrayList<>();
        try {
            Connection conn = JDBC.getConnection();
            String sql = "SELECT * FROM donhang WHERE TenKhachHang LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new DonHangDTO(
                    rs.getString("MaDonHang"),
                    rs.getDate("NgayMua").toString(),
                    rs.getString("TenKhachHang"),
                    rs.getDouble("TongTien")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
