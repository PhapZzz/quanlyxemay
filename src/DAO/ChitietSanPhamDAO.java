package DAO;
import java.sql.*;
import java.util.*;

import DTO.ChitietSanPhamDTO;
import DTO.SanPhamDTO;
import DTO.danhsachxe;
import Util.JDBC;

public class ChitietSanPhamDAO {
public List<ChitietSanPhamDTO> getAllChiTietXe(String maxe) {
        List<ChitietSanPhamDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM chitietxe WHERE MaXe LIKE ?";
        try 
            (Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))

            {
                ps.setString(1, maxe);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                ds.add(new ChitietSanPhamDTO(
                    rs.getString("MaChiTietXe"),
                    rs.getString("MaXe"),
                    rs.getString("SoKhung"),
                    rs.getString("SoMay"),
                    rs.getString("TrangThai"),
                    rs.getString("MaPhieuNhap"),
                    rs.getString("color"),
                    rs.getString("img")
                ));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean insert(ChitietSanPhamDTO sp) {
        String sql = "INSERT INTO chitietxe VALUES (?, ?, ?, ?, ?,?,?,?)";
        try (Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getMachitietXe());
            ps.setString(2, sp.getMaxe());
            ps.setString(3, sp.getSoKhung());
            ps.setString(4, sp.getSoMay());
            ps.setString(5, sp.getTrangThai());
            ps.setString(6, sp.getMaPhieuNhap());
            ps.setString(7, sp.getColor());
            ps.setString(8, sp.getImg());
            return ps.executeUpdate() > 0;// trả về true nếu có ít nhất 1 bản ghi được thêm vào
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public boolean checkmaChiTiet(String MaChiTietXe){
        String sql = "SELECT * FROM chitietxe WHERE ? = MaChiTietXe ";
        try(Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, MaChiTietXe);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){return true;}
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // sửa
    public boolean update(ChitietSanPhamDTO sp) {
        String sql = "UPDATE chitietxe SET MaXe=?, SoKhung=?, SoMay=?, TrangThai=?,MaPhieuNhap=?,color=?,img=? WHERE MaChiTietXe=?";
        try (Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(8, sp.getMachitietXe());
            ps.setString(1, sp.getMaxe());
            ps.setString(2, sp.getSoKhung());
            ps.setString(3, sp.getSoMay());
            ps.setString(4, sp.getTrangThai());
            ps.setString(5, sp.getMaPhieuNhap());
            ps.setString(6, sp.getColor());
            ps.setString(7, sp.getImg());
            
            return ps.executeUpdate() > 0;// trả về true nếu có ít nhất 1 bản ghi được thêm vào
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean delete(String MaChiTietXe) {
        String sql = "DELETE FROM chitietxe WHERE MaChiTietXe=?";
        try (
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, MaChiTietXe);
            return ps.executeUpdate()>0; // trả về true nếu có ít nhất 1 bản ghi bị xóa
           
        } catch (SQLException e) { e.printStackTrace(); return false; }
        
    }

    public List<danhsachxe> getChiTietPhieuNhapbymaphieunhap(String maPhieuNhap) {
        List<danhsachxe> list = new ArrayList<>();
        try (Connection conn = JDBC.getConnection()) {
            String sql = "SELECT ctx.MaChiTietXe, x.TenXe, x.GiaBan, ctx.SoKhung, ctx.SoMay,ctx.TrangThai, ctx.color "+
                            "FROM chitietxe ctx "+
                            "JOIN xemay x ON ctx.MaXe = x.MaXe "+
                           "WHERE ctx.MaPhieuNhap = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maPhieuNhap);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new danhsachxe(
                    rs.getString("MaChiTietXe"),
                    rs.getString("Tenxe"),
                    rs.getDouble("GiaBan"),
                    rs.getString("SoKhung"),
                    rs.getString("SoMay"),
                    rs.getString("TrangThai"),
                    rs.getString("color")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
