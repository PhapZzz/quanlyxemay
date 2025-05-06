package DAO;
import java.sql.*;
import java.util.*;

import DTO.HangxeDTO;

import Util.JDBC;
public class HangxeDAO {
    public List<HangxeDTO> getAll() {
        List<HangxeDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM hangxe";
        try (Connection conn = JDBC.getConnection();
            Statement st = conn.createStatement(); 

            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new HangxeDTO(
                    rs.getString("MaHang"),
                    rs.getString("TenHang")

                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public boolean them(HangxeDTO a){
        try (Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO hangxe VALUES (?, ?)");
        ) {
            ps.setString(1, a.getMaHang());
            ps.setString(2, a.getTenHang());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace(); return false;
        }
    }
}
