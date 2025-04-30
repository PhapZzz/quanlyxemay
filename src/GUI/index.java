package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class index extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public index() {
        setTitle("Motorbike Management");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    
        //setOpaque(true);
        setBackground(Color.WHITE);

        // Tạo CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        // Thêm các "trang" từ class khác
        mainPanel.add(new SanPham_GUI(this), "Sanpham");
        mainPanel.add(new QuanlyNhanvien_GUI(), "Nhanvien");
        
        mainPanel.add(createindex_panel(), "home");

        cardLayout.show(mainPanel,"home");

        // Menu
        JPanel menuPanel = new JPanel(new BorderLayout());
        
        JPanel a = new JPanel();
        JPanel s = new JPanel();

        JPanel topmenuPanel =new JPanel(new BorderLayout());

        ImageIcon icon = new ImageIcon("logo/lovepik-motorcycle-logo-picture_501502493.jpg");
        Image img = icon.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH);
        JLabel img_label = new JLabel(new ImageIcon(img));
        img_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblTitle = new JLabel("Hệ Thống Bán Xe Máy", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        
        topmenuPanel.add(img_label, BorderLayout.WEST);
        topmenuPanel.add(lblTitle, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(192,192,192));
        buttonPanel.setOpaque(true);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        //JButton btnHome = new JButton("Home");
        RoundButton btnHome = new RoundButton("Home", 20);
        RoundButton btnXe = new RoundButton("Sản phẩm",20);
        RoundButton btnNhanVien = new RoundButton("Nhân viên",20);
        RoundButton btnThongke = new RoundButton("Thống kê",20);
        RoundButton btnHoadon = new RoundButton("Hóa đơn",20);
        RoundButton btnKhachhang = new RoundButton("Khách hàng",20);
        RoundButton btnNhacungcap = new RoundButton("Nhà cung cấp",20);
        RoundButton btnPhieunhap = new RoundButton("Phiếu nhập kho",20);
        setButton(btnHome);
        setButton(btnXe);
        setButton(btnNhanVien);
        setButton(btnThongke);
        setButton(btnHoadon);
        setButton(btnKhachhang);
        setButton(btnNhacungcap);
        setButton(btnPhieunhap);




        buttonPanel.add(btnHome);
        buttonPanel.add(btnXe);
        buttonPanel.add(btnThongke);
        buttonPanel.add(btnNhanVien);
        buttonPanel.add(btnNhacungcap);
        buttonPanel.add(btnHoadon);
        buttonPanel.add(btnKhachhang);
        buttonPanel.add(btnPhieunhap);


// --- Thêm vào menuPanel ---
        menuPanel.add(topmenuPanel, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);
        menuPanel.add(a,BorderLayout.WEST);
        menuPanel.add(s,BorderLayout.EAST);
        

        // Layout chính
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);



        // xu ly nut
        btnXe.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(mainPanel,"Sanpham");
            }
        });

        btnHome.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(mainPanel,"home");
            }
        });

        btnNhanVien.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(mainPanel,"Nhanvien");
            }
        });

    }


    public JPanel createindex_panel(){
        JPanel index_Panel = new JPanel();
            JLabel title = new JLabel("đây là trang home");
            index_Panel.add(title);

        return index_Panel;
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void setButton(RoundButton s){
        s.setBackground(Color.WHITE); 
        s.setFont(14, Color.BLACK); 
        s.setButtonSize(120, 30);
        s.setBorderWidth(1); 
    }

    public static void main(String[] args) {
        new index().setVisible(true);;
    }
}
