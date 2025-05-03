package DAO;

import DTO.NhaCungCapDTO;
import java.sql.*;
import java.util.*;
import Util.JDBC;
public class NhaCungCapDAO {
    private Connection conn;

    public NhaCungCapDAO() {
        conn = JDBC.getConnection(); // Bạn cần có lớp ConnectDB
    }

    public List<NhaCungCapDTO> getAllNhaCungCap() {
        List<NhaCungCapDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ds.add(new NhaCungCapDTO(
                    rs.getString("MaNCC"),
                    rs.getString("TenNCC"),
                    rs.getString("DiaChi"),
                    rs.getString("SDT")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean them(NhaCungCapDTO ncc) {
        String sql = "INSERT INTO nhacungcap (MaNCC, TenNCC, DiaChi, SDT) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getDiaChi());
            ps.setString(4, ncc.getSdt());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sua(NhaCungCapDTO ncc) {
        String sql = "UPDATE nhacungcap SET TenNCC = ?, DiaChi = ?, SDT = ? WHERE MaNCC = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getDiaChi());
            ps.setString(3, ncc.getSdt());
            ps.setString(4, ncc.getMaNCC());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoa(String maNCC) {
        String sql = "DELETE FROM nhacungcap WHERE MaNCC = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNCC);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhaCungCapDTO> timTheoTen(String ten) {
        List<NhaCungCapDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap WHERE TenNCC LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new NhaCungCapDTO(
                    rs.getString("MaNCC"),
                    rs.getString("TenNCC"),
                    rs.getString("DiaChi"),
                    rs.getString("SDT")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
