package DAO;

import DTO.DoanhSoDTO;
import Util.JDBC;

import java.sql.*;
import java.util.*;

public class DoanhSoDAO {
    public List<DoanhSoDTO> layDanhSachSanPhamBanChay() throws SQLException {
        List<DoanhSoDTO> list = new ArrayList<>();
        String sql = """
                SELECT xm.TenXe, COUNT(*) AS SoLuongBan
                FROM ChiTietDonHang ct
                JOIN chitietxe sp ON ct.MaSanPham = sp.MaChiTietXe
                JOIN xemay xm ON xm.MaXe = sp.MaXe
                GROUP BY xm.TenXe
                ORDER BY SoLuongBan DESC; 

        """;

        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new DoanhSoDTO(
                    rs.getString("TenXe"),
                    rs.getInt("SoLuongBan")
                ));
            }
        }
        return list;
    }


    public List<DoanhSoDTO> laySanPhamBanChayTheoKhoang(String tuNgay, String denNgay) throws SQLException {
        List<DoanhSoDTO> list = new ArrayList<>();
        String sql = """
                SELECT xm.TenXe, COUNT(*) AS SoLuongBan
                FROM ChiTietDonHang ct
                JOIN chitietxe sp ON ct.MaSanPham = sp.MaChiTietXe
                JOIN DonHang dh ON ct.MaDonHang = dh.MaDonHang
                JOIN xemay xm ON xm.MaXe = sp.MaXe
                WHERE dh.NgayMua BETWEEN ? AND ?
                GROUP BY xm.TenXe
                ORDER BY SoLuongBan DESC; 
        """;
    
        try (Connection conn = JDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                list.add(new DoanhSoDTO(
                    rs.getString("TenXe"),
                    rs.getInt("SoLuongBan")
                ));
            }
        }
        return list;
    }
    
    
}
