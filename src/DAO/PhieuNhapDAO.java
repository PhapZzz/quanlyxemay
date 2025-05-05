package DAO;

import DTO.PhieuNhapDTO;
import Util.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    public static boolean themPhieuNhap(PhieuNhapDTO pn) {
        String sql = "INSERT INTO phieunhap (MaPhieuNhap, MaNCC, MaNV, NgayNhap) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pn.getMaPhieuNhap());
            stmt.setString(2, pn.getMaNCC());
            stmt.setInt(3, pn.getMaNV());
            stmt.setDate(4, new java.sql.Date(pn.getNgayNhap().getTime()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<PhieuNhapDTO> getAllPhieuNhap() {
        List<PhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieunhap";
        try (Connection conn = JDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO(
                    rs.getString("MaPhieuNhap"),
                    rs.getString("MaNCC"),
                    rs.getInt("MaNV"),
                    rs.getDate("NgayNhap")
                );
                list.add(pn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
