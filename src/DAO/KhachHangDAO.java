package DAO;

import DTO.KhachHangDTO;
import Util.JDBC;

import java.sql.*;
import java.util.*;

public class KhachHangDAO {
    public List<KhachHangDTO> getAllKhachHang() throws SQLException {
        List<KhachHangDTO> list = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM quanlykhachhang");

        while (rs.next()) {
            list.add(new KhachHangDTO(
                rs.getInt("MaKH"),
                rs.getString("TenKH"),
                rs.getString("DiaChi")
            ));
        }
        conn.close();
        return list;
    }

    public void insertKhachHang(KhachHangDTO kh) throws SQLException {
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO quanlykhachhang (TenKH, DiaChi) VALUES (?, ?)");
        ps.setString(1, kh.getTenKH());
        ps.setString(2, kh.getDiaChi());
        ps.executeUpdate();
        conn.close();
    }

    public void updateKhachHang(KhachHangDTO kh) throws SQLException {
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE quanlykhachhang SET TenKH=?, DiaChi=? WHERE MaKH=?");
        ps.setString(1, kh.getTenKH());
        ps.setString(2, kh.getDiaChi());
        ps.setInt(3, kh.getMaKH());
        ps.executeUpdate();
        conn.close();
    }

    public void deleteKhachHang(int maKH) throws SQLException {
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM quanlykhachhang WHERE MaKH=?");
        ps.setInt(1, maKH);
        ps.executeUpdate();
        conn.close();
    }
}
