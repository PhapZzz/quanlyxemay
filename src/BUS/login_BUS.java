package BUS;

import javax.swing.JOptionPane;

import DAO.login_DAO;
import DTO.userDTO;

public class login_BUS {
        private userDTO user ;
        private login_DAO login_DAO;
        public login_BUS() {
            login_DAO = new login_DAO();  
        }

        // kiem tra gia tri nhap
        public Boolean login(String username, String password) {    
            
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        user = new userDTO(username, password);
        if( login_DAO.checkUser(user)){
            JOptionPane.showMessageDialog(null, "thanh cong", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Không tồn tại tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
    }


    // hàm đăng ký

    public Boolean register(String username, String password, String confirm_password) {
        // Kiểm tra username
        if (username == null || username.trim().isEmpty() || username.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Username không hợp lệ (không được trống hoặc chứa khoảng trắng)");
            return false;
        }
    
        // Kiểm tra password, ký tự
        if (password == null || password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password phải có ít nhất 6 ký tự");
            return false;
        }
    
        // Kiểm tra confirm password
        if (!password.equals(confirm_password)) {
            JOptionPane.showMessageDialog(null, "Confirm password không trùng với password");
            return false;
        }
    
        // Kiểm tra username đã tồn tại
        if (login_DAO.checkAvailable(username)) {
            JOptionPane.showMessageDialog(null, "Username đã tồn tại");
            return false;
        }
    
        // Tất cả điều kiện hợp lệ → tiến hành đăng ký
        userDTO usernew = new userDTO(username, password);
        if (login_DAO.registerUser(usernew)) {
            JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Lỗi đăng ký!");
            return false;
        }
    }
}
    