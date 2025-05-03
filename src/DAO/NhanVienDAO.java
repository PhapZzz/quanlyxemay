package DAO;

import DTO.NhanVienDTO;
import Util.JDBC;

import java.sql.*;
import java.util.*;

public class NhanVienDAO {
    public List<NhanVienDTO> getAllNhanVien() throws SQLException {
        List<NhanVienDTO> list = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM quanlynhanvien");

        while (rs.next()) {
            list.add(new NhanVienDTO(
                rs.getInt("MaNV"),
                rs.getString("TenNV"),
                rs.getString("ChucVu")
            ));
        }
        conn.close();
        return list;
    }

    public void insertNhanVien(NhanVienDTO nv) throws SQLException {
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO quanlynhanvien (TenNV, ChucVu) VALUES (?, ?)");
        ps.setString(1, nv.getTenNV());
        ps.setString(2, nv.getChucVu());
        ps.executeUpdate();
        conn.close();
    }

    public void updateNhanVien(NhanVienDTO nv) throws SQLException {
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE quanlynhanvien SET TenNV=?, ChucVu=? WHERE MaNV=?");
        ps.setString(1, nv.getTenNV());
        ps.setString(2, nv.getChucVu());
        ps.setInt(3, nv.getMaNV());
        ps.executeUpdate();
        conn.close();
    }

    public void deleteNhanVien(int maNV) throws SQLException {
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM quanlynhanvien WHERE MaNV=?");
        ps.setInt(1, maNV);
        ps.executeUpdate();
        conn.close();
    }
}
