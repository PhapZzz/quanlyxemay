package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BUS.ChitietSanPham_BUS;
import DAO.ChitietSanPhamDAO;
import DTO.ChitietSanPhamDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChiTietSanPham_GUI extends JFrame {
   private JTable table;
    private DefaultTableModel model;
    private ChitietSanPham_BUS bus = new ChitietSanPham_BUS();
    private JLabel lblImagePreview;

    public ChiTietSanPham_GUI(String maXe) {



            setTitle("Quản lý Chi Tiết Mã Xe "+'"'+maXe+'"');
            setSize(1200, 800);
            setLocationRelativeTo(null);
            JPanel Panel_N = new JPanel(new FlowLayout());
            JLabel title_maxe = new JLabel("Danh sách quản lý theo mã"+'"'+maXe+'"');
            title_maxe.setFont(new Font("Arial", Font.BOLD, 20));
            Panel_N.add(title_maxe);
            
            // Set layout
            setLayout(new BorderLayout());
            JPanel Panel_Left = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            
            JLabel MaChiTietXelbl = new JLabel("Nhập mã chi tiết xe: ");
            JTextField txtMaChiTietXe = new JTextField(18);
            JLabel MaXelbl = new JLabel("Nhập mã xe: ");
            JTextField txtMaXe = new JTextField(18);
            JLabel SoKhunglbl = new JLabel("Nhập Số Khung: ");
            JTextField txtSoKhung = new JTextField(18);
            JLabel SoMaylbl = new JLabel("Nhập Số máy: ");
            JTextField txtSoMay = new JTextField(18);
            JLabel MaPhieuNhaplbl = new JLabel("Nhập mã phiếu nhập: ");
            JTextField txtMaPhieuNhap = new JTextField(18);
            JLabel Colorlbl = new JLabel("Nhập màu xe: ");
            JTextField txtColor = new JTextField(18);
            JLabel imglbl = new JLabel("Nhập hình ảnh: ");
            JTextField txtimg = new JTextField(18);
            JLabel TrangThailbl = new JLabel("Nhập trạng thái xe: ");
            JTextField txtTrangThai = new JTextField(18);
            // các nút chức năng
            RoundButton btnThem = new RoundButton("Thêm",30);
            setButton(btnThem);
            RoundButton btnSua = new RoundButton("Sửa",30);
            RoundButton btnXoa = new RoundButton("Xóa",30);
            RoundButton btnTimKiem = new RoundButton("Tìm kiếm",30);
            RoundButton btnLamMoi = new RoundButton("Làm mới",30);
            setButton(btnSua);
            setButton(btnXoa);
            setButton(btnTimKiem);
            setButton(btnLamMoi);
            int row = 0;
            addFormRow(Panel_Left, gbc, row++, MaChiTietXelbl, txtMaChiTietXe);
            addFormRow(Panel_Left, gbc, row++, MaXelbl, txtMaXe);
            addFormRow(Panel_Left, gbc, row++, SoKhunglbl, txtSoKhung);
            addFormRow(Panel_Left, gbc, row++, SoMaylbl, txtSoMay);
            addFormRow(Panel_Left, gbc, row++, MaPhieuNhaplbl, txtMaPhieuNhap);
            addFormRow(Panel_Left, gbc, row++, Colorlbl, txtColor);
            addFormRow(Panel_Left, gbc, row++, imglbl, txtimg);
            addFormRow(Panel_Left, gbc, row++, TrangThailbl, txtTrangThai);

            JPanel panelButton = new JPanel(new GridLayout(3, 2, 10,10 ));


            panelButton.add(btnThem);
            panelButton.add(btnSua);
            panelButton.add(btnXoa);
            panelButton.add(btnTimKiem);
            panelButton.add(btnLamMoi);
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 2;
            Panel_Left.add(panelButton, gbc);

            JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            wrapperPanel.add(Panel_Left);
            add(Panel_N,BorderLayout.NORTH);
            add(wrapperPanel,BorderLayout.WEST);

            // Khởi tạo model bảng ghi dè phương thức
            //Nếu cột thứ 6 (đếm từ 0, tức cột thứ 7 trên giao diện) thì:
            // Trả về ImageIcon.class ⟹ hiện ảnh.
            // Các cột khác trả về String.class ⟹ hiện văn bản bình thường.
            model = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int column) {
                    return column == 6 ? ImageIcon.class : String.class;
                }
            };
            model.setColumnIdentifiers(new String[]{
                "MaChiTietXe", "MaXe", "SoKhung", "SoMay", "MaPhieuNhap", "Color", "Img","TrangThai"
            });
    
            // Khởi tạo JTable
            table = new JTable(model);
            table.setRowHeight(60); // khởi tạo độ cao từng hàng
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
    
            // Load dữ liệu từ DAO
            loadData(maXe);



            // xử lý thêm
            btnThem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String machitietxe = txtMaChiTietXe.getText();
                    String maxe = maXe;
                    String SoKhung = txtSoKhung.getText();
                    String somay = txtSoMay.getText();
                    String TrangThai = txtTrangThai.getText();
                    String maphieunhap = txtMaPhieuNhap.getText();
                    String color = txtColor.getText();
                    String img = txtimg.getText();
                   if(bus.themSanPham(machitietxe, maxe, SoKhung, somay, TrangThai, maphieunhap, color, img)){
                    loadData(maxe);
                   }
                    
                   

                }
            }); 
    }
    
        private void loadData(String maxe) {
            List<ChitietSanPhamDTO> ds = bus.getAll(maxe);
            model.setRowCount(0); // Xoá dữ liệu cũ
            for (ChitietSanPhamDTO xe : ds) {
                model.addRow(new Object[]{
                    xe.getMachitietXe(),
                    xe.getMaxe(),
                    xe.getSoKhung(),
                    xe.getSoMay(),
                    xe.getMaPhieuNhap(),
                    xe.getColor(),
                    getImageIcon(xe.getImg()),
                    xe.getTrangThai(),
                });
            }
        }

        // xử lý hình ảnh
        private ImageIcon getImageIcon(String path) {
            if (path == null || path.isEmpty()) return null;
            try {
                ImageIcon icon = new ImageIcon("img/no_evo200.jpg");
                Image scaled = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            } catch (Exception e) {
                System.err.println("Không thể load ảnh: " + path);
                return null;
            }
        }

        private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, JLabel label, JComponent field) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0.3;
            panel.add(label, gbc);
    
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            panel.add(field, gbc);
        }
        public void setButton(RoundButton s){
            s.setBackground(Color.WHITE); 
            s.setFont(14, Color.BLACK); 
            s.setButtonSize(120, 30);
            s.setBorderWidth(1); 
        }
    

        
    }


