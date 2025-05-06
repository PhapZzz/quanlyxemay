package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BUS.ChitietSanPham_BUS;
import BUS.SanPhamBUS;
import DAO.ChitietSanPhamDAO;
import DTO.ChitietSanPhamDTO;
import java.io.File;
import java.math.BigDecimal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ChiTietSanPham_GUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private ChitietSanPham_BUS bus = new ChitietSanPham_BUS();
    private JLabel lblImagePreview;
    private JLabel MaChiTietXelbl,MaXelbl,SoKhunglbl,SoMaylbl,MaPhieuNhaplbl,Colorlbl,imglbl,TrangThailbl;
    private JTextField txtMaChiTietXe,txtMaXe,txtSoKhung,txtSoMay,txtMaPhieuNhap,txtColor,txtimg,txtTrangThai;
    private String maxe;
    public ChiTietSanPham_GUI(String maXe) {
            this.maxe = maXe;
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

            
            MaChiTietXelbl = new JLabel("Nhập mã chi tiết xe: ");
            txtMaChiTietXe = new JTextField(18);
            MaXelbl = new JLabel("Nhập mã xe: ");
            txtMaXe = new JTextField(18);
            txtMaXe.setEditable(false);
            SoKhunglbl = new JLabel("Nhập Số Khung: ");
            txtSoKhung = new JTextField(18);
            SoMaylbl = new JLabel("Nhập Số máy: ");
            txtSoMay = new JTextField(18);
            MaPhieuNhaplbl = new JLabel("Nhập mã phiếu nhập: ");
            txtMaPhieuNhap = new JTextField(18);
            Colorlbl = new JLabel("Nhập màu xe: ");
            txtColor = new JTextField(18);
            imglbl = new JLabel("Nhập hình ảnh: ");

            // gọi panelImg để gộp txtimg và nút chọn hình
            txtimg = new JTextField(18);
            JButton btnBrowse = new JButton("...");
            // btnBrowse.setIcon(new ImageIcon("img/no_evo200.jpg"));
            JPanel panelImg = new JPanel(new BorderLayout());
            panelImg.add(txtimg, BorderLayout.CENTER);
            panelImg.add(btnBrowse, BorderLayout.EAST);


            TrangThailbl = new JLabel("Nhập trạng thái xe: ");
            txtTrangThai = new JTextField(18);
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
            addFormRow(Panel_Left, gbc, row++, imglbl, panelImg);
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

            // sự kiện click vào bảng
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int selectRow = table.getSelectedRow();
                    if(selectRow != -1){
                        txtMaChiTietXe.setText(model.getValueAt(selectRow, 0).toString());
                        txtMaXe.setText(model.getValueAt(selectRow, 1).toString());
                        txtSoKhung.setText(model.getValueAt(selectRow, 2).toString());
                        txtSoMay.setText(model.getValueAt(selectRow, 3).toString());
                        txtMaPhieuNhap.setText(model.getValueAt(selectRow, 4).toString());
                        txtColor.setText(model.getValueAt(selectRow, 5).toString());
                        //txtimg.setText(model.getValueAt(selectRow, 6).toString());
                        txtTrangThai.setText(model.getValueAt(selectRow, 7).toString());
                    }
                }
            });

            // xử lý nút chọn hình ảnh
            btnBrowse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    chooseImage();
                }
            });


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
            btnSua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                if (confirmAction("Bạn có chắc chắn muốn update sản phẩm này?")) {
                if (validateFields(txtMaChiTietXe)) {
                    if (sua()) {
                        showMessage("Sửa sản phẩm thành công!");
                        loadData(maXe);
                            }
                        }
                    }
                }
            });

            btnXoa.addActionListener(e -> {
                if (confirmAction("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
                    if (bus.xoa(txtMaChiTietXe.getText())) {
                        showMessage("Xóa sản phẩm thành công!");
                        loadData(maXe);
                    }
                }
            });
    }
    // 3 hàm thông báo
    private boolean confirmAction(String message) {
        int option = JOptionPane.showConfirmDialog(null, message, "Xác nhận", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private boolean validateFields(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                showMessage("Vui lòng điền đầy đủ thông tin!");
                return false;
            }
        }
        return true;
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
                ImageIcon icon = new ImageIcon(path);
                Image scaled = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            } catch (Exception e) {
                System.err.println("Không thể load ảnh: " + path);
                return null;
            }
        }

        private void chooseImage() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("img")); 
            fileChooser.setDialogTitle("Chọn hình ảnh");
        
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                int index = imagePath.indexOf(File.separator + "img" + File.separator); //xử lý đường dẫn
                if (index != -1) {
                    String relativePath = imagePath.substring(index + 1); // Bỏ dấu '/' đầu
                    txtimg.setText(relativePath);
                } else {
                    // Nếu không chứa "/img/", thì để nguyên 
                    txtimg.setText(imagePath); 
                }

            }
        }
        // xử lý sửa
        private boolean sua(){
            String machitietxe = txtMaChiTietXe.getText();
            String maxe = this.maxe;
            String SoKhung = txtSoKhung.getText();
            String somay = txtSoMay.getText();
            String TrangThai = txtTrangThai.getText();
            String maphieunhap = txtMaPhieuNhap.getText();
            String color = txtColor.getText();
            String img = txtimg.getText();
            if(bus.suaSanPham(machitietxe, maxe, SoKhung, somay, TrangThai, maphieunhap, color, img)){
                loadData(maxe);
                return true;
            }
            else{
                return false;
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


