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
            JPanel Panel_Left = new JPanel(new GridLayout(11,2,1,10));
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
            JButton btnThem = new JButton("Thêm");
            JButton btnSua = new JButton("Sửa");
            JButton btnXoa = new JButton("Xóa");
            JButton btnTimKiem = new JButton("Tìm kiếm");
            JButton btnLamMoi = new JButton("Làm mới");
           

            Panel_Left.add(MaChiTietXelbl);Panel_Left.add(txtMaChiTietXe);
            //Panel_Left.add(MaXelbl);Panel_Left.add(txtMaXe);
            Panel_Left.add(SoKhunglbl);Panel_Left.add(txtSoKhung);
            Panel_Left.add(SoMaylbl);Panel_Left.add(txtSoMay);
            Panel_Left.add(MaPhieuNhaplbl);Panel_Left.add(txtMaPhieuNhap);
            Panel_Left.add(Colorlbl);Panel_Left.add(txtColor);
            Panel_Left.add(imglbl);Panel_Left.add(txtimg );
            Panel_Left.add(TrangThailbl);Panel_Left.add(txtTrangThai);

            Panel_Left.add(btnThem);
            Panel_Left.add(btnSua);
            Panel_Left.add(btnXoa);
            Panel_Left.add(btnTimKiem);
            Panel_Left.add(btnLamMoi);

            add(Panel_N,BorderLayout.NORTH);
            add(Panel_Left,BorderLayout.WEST);

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

        
    }


