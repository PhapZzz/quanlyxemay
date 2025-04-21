package GUI;
import javax.swing.*;

import BUS.login_BUS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    public Login_GUI(){
        setTitle("Login/Register");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình

         // Tạo CardLayout
         cardLayout = new CardLayout();
         mainPanel = new JPanel(cardLayout);

          // Thêm các Panel cho từng giao diện
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createRegisterPanel(), "Register");

        cardLayout.show(mainPanel, "Login");

        JPanel forgotPanel = new JPanel();
        JButton forgotButton = new JButton("Forgot Username / Password?");
        forgotButton.setBorderPainted(false);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setForeground(Color.BLUE);
        forgotPanel.add(forgotButton);

               // Xử lý sự kiện cho nút Forgot
        forgotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Login_GUI.this, 
                        "Vui lòng liên hệ quản trị viên để khôi phục tài khoản", 
                        "Quên mật khẩu", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // JPanel login_back = new JPanel();
        // JButton btnlogin_back = new JButton("Quay lai đăng nhập");
        // btnlogin_back.setBorderPainted(false);
        // btnlogin_back.setContentAreaFilled(false);
        // btnlogin_back.setForeground(Color.BLUE);
        // login_back.add(login_back);
        
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(forgotPanel, BorderLayout.SOUTH);
        setVisible(true);
        
    }
    public JPanel createLoginPanel(){

        // Tạo panel chính với BorderLayout
        JPanel mainLogin = new JPanel(new BorderLayout(10, 10));
        mainLogin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel cho tiêu đề
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        
        // Panel cho các trường nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 10));
        
        // User field
        JPanel UserPanel = new JPanel(new BorderLayout(5, 5));
        JLabel UserLabel = new JLabel("Username");
        JTextField txt_User = new JTextField();
        UserPanel.add(UserLabel, BorderLayout.NORTH);
        UserPanel.add(txt_User, BorderLayout.CENTER);
        
        // Password field
        JPanel passwordPanel = new JPanel(new BorderLayout(5, 5));
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        
        inputPanel.add(UserPanel);
        inputPanel.add(passwordPanel);
        // inputPanel.add(new JButton("sss"));
        
        // Panel cho nút Login,registerButton
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Thêm các panel vào mainLogin
        mainLogin.add(titlePanel, BorderLayout.NORTH);
        mainLogin.add(inputPanel, BorderLayout.CENTER);
        mainLogin.add(buttonPanel, BorderLayout.SOUTH);
        
        // Xử lý sự kiện cho nút Login
        getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txt_User.getText();
                String password = new String(passwordField.getPassword());
                login_BUS s = new login_BUS();

                if(s.login(user, password)){
                    dispose();  // Đóng cửa sổ đăng nhập
                    index index = new index(); // mở home
                    index.setVisible(true);
                
                }
                
            }
        });
        
// xử lý xự kiện cho nút đăng ký
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(mainPanel, "Register");
            }
        });
        
        return mainLogin;
    }

    //panel của đăng ký
    private JPanel createRegisterPanel() {
        JPanel mainRegis = new JPanel(new BorderLayout(10, 10));
        mainRegis.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

                // Panel cho tiêu đề
                JPanel titlePanel = new JPanel();
                JLabel titleLabel = new JLabel("Register");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
                titlePanel.add(titleLabel);
                
                // Panel cho các trường nhập liệu
                JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 10));
                
                // User field
                JPanel UserPanel = new JPanel(new BorderLayout(5, 5));
                JLabel UserLabel = new JLabel("Username");
                JTextField txt_User = new JTextField();
                UserPanel.add(UserLabel, BorderLayout.NORTH);
                UserPanel.add(txt_User, BorderLayout.CENTER);
                
                // Password field
                JPanel passwordPanel = new JPanel(new BorderLayout(5, 5));
                JLabel passwordLabel = new JLabel("Password");
                JPasswordField passwordField = new JPasswordField();
                passwordPanel.add(passwordLabel, BorderLayout.NORTH);
                passwordPanel.add(passwordField, BorderLayout.CENTER);

                //comfim Password field
                JPanel comfim_passwordPanel = new JPanel(new BorderLayout(5, 5));
                JLabel comfim_passwordLabel = new JLabel("Comfim_Password");
                JPasswordField comfim_passwordField = new JPasswordField();
                comfim_passwordPanel.add(comfim_passwordLabel, BorderLayout.NORTH);
                comfim_passwordPanel.add(comfim_passwordField, BorderLayout.CENTER);
                
                inputPanel.add(UserPanel);
                inputPanel.add(passwordPanel);
                inputPanel.add(comfim_passwordPanel);
                // inputPanel.add(new JButton("sss"));
                
                // Panel cho nút registerButton
                JPanel buttonPanel = new JPanel();
                JButton registerButton = new JButton("Register");
                
                buttonPanel.add(registerButton);
        
                // Thêm các panel vào mainLogin
                mainRegis.add(titlePanel, BorderLayout.NORTH);
                mainRegis.add(inputPanel, BorderLayout.CENTER);
                mainRegis.add(buttonPanel, BorderLayout.SOUTH);
                
                
        // xử lý xự kiện cho nút đăng ký
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String user = txt_User.getText();
                        String password = new String(passwordField.getPassword());
                        String comfim_password = new String(comfim_passwordField.getPassword());
                        login_BUS s = new login_BUS();
                        if(s.register(user, password, comfim_password)){
                            cardLayout.show(mainPanel, "Login");
                        }
                    }
                });
                
                return mainRegis;
    }
}