//DAO để xủ lý với database
package DAO;
import java.sql.*;

import DTO.userDTO;
import Util.JDBC;

public class login_DAO {
    public Boolean checkUser(userDTO user){   
        String query = "SELECT * FROM user WHERE Username = ? AND Password = ?";
        //try-with-resources, sẽ tự động dóng kết nối sau khi sài khối try
        try (Connection con = JDBC.getConnection();  
             PreparedStatement st = con.prepareStatement(query))  
        {
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassWord());
            ResultSet rs = st.executeQuery();
            return rs.next();
            

        } catch (Exception e) {
            return false;
        }
    

        
    }


    public Boolean checkAvailable(String user){
        String query = "SELECT * FROM user WHERE Username = ?";
        try (Connection con = JDBC.getConnection();  
             PreparedStatement st = con.prepareStatement(query))  
        {
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            return rs.next();
            

        } catch (Exception e) {
            return false;
        }
    }

    public boolean registerUser(userDTO user) {
        String query = "{CALL RegisterUser(?, ?)}";
        try (Connection con = JDBC.getConnection();
             CallableStatement cs = con.prepareCall(query)) {
            cs.setString(1, user.getUserName());
            cs.setString(2, user.getPassWord());
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}
