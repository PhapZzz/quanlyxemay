package DAO;

import java.sql.*;
import java.util.*;

import DTO.SanPhamDTO;
import Util.JDBC;
public class SanPhamDAO {

    public List<SanPhamDTO> getAll() {
        List<SanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM xemay";
        try (Connection conn = JDBC.getConnection();
            Statement st = conn.createStatement(); 

            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new SanPhamDTO(
                    rs.getString("MaXe"),
                    rs.getString("TenXe"),
                    rs.getString("MaHang"),
                    rs.getDouble("GiaBan"),
                    rs.getInt("sl")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean insert(SanPhamDTO sp) {
        String sql = "INSERT INTO xemay (MaXe, TenXe, MaHang, GiaBan, sl) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sp.getMaxe());
            ps.setString(2, sp.getTenxe());
            ps.setString(3, sp.getMaHang());
            ps.setDouble(4, sp.getGia());
            ps.setDouble(5, sp.getSl());
            return ps.executeUpdate() > 0;// trả về true nếu có ít nhất 1 bản ghi được thêm vào
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean checkmasp(String masp){
        String sql = "SELECT * FROM xemay WHERE ? = maxe ";
        try(Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, masp);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){return true;}
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean update(SanPhamDTO sp) {
        String sql = "UPDATE xemay SET TenXe=?, MaHang=?, GiaBan=?, sl=? WHERE MaXe=?";
        try (
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, sp.getTenxe());
            ps.setString(2, sp.getMaHang());
            ps.setDouble(3, sp.getGia());
            ps.setInt(4, sp.getSl());
            ps.setString(5, sp.getMaxe()); // điều kiện where
            return ps.executeUpdate()>0;
            
        } catch (SQLException e) { e.printStackTrace(); return false; }
        
    }

    public boolean delete(String maXe) {
        String sql = "DELETE FROM xemay WHERE MaXe=?";
        try (
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, maXe);
            return ps.executeUpdate()>0; // trả về true nếu có ít nhất 1 bản ghi bị xóa
           
        } catch (SQLException e) { e.printStackTrace(); return false; }
        
    }

    public List<SanPhamDTO> searchByName(String keyword) {
        List<SanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM xemay WHERE tenXe LIKE ?";
        try (
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SanPhamDTO(
                    rs.getString("MaXe"),
                    rs.getString("TenXe"),
                    rs.getString("MaHang"),
                    rs.getDouble("GiaBan"),
                    rs.getInt("sl")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }


}
