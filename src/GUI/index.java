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
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       

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
        JButton btnHome = new JButton("Home");
        JButton btnXe = new JButton("Sản phẩm");
        JButton btnNhanVien = new JButton("Nhân viên");
        JButton btnThongke = new JButton("Thống kê");
        JButton btnHoadon = new JButton("Hóa đơn");
        JButton btnKhachhang = new JButton("Khách hàng");
        JButton btnNhacungcap = new JButton("Nhà cung cấp");
        JButton btnPhieunhap = new JButton("Phiếu nhập kho");



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

    public static void main(String[] args) {
        new index().setVisible(true);;
    }
}
