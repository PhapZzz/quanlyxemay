package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import BUS.HangxeBUS;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SanPham_GUI extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private index mainFrame;
    private SanPhamBUS SanPhamBUS = new SanPhamBUS();
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi, btnChitiet;

    public SanPham_GUI(index frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("DANH SÁCH SẢN PHẨM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã xe", "Tên xe", "Hãng", "Giá", "sl"});
        
        table = new JTable(model);
        
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
        


        HangxeBUS hangBUS = new HangxeBUS();

        List<String> listHang = hangBUS.getAllHang_Display();

        JComboBox<String> cboHang = new JComboBox<>();
        for (String s : listHang) {
            cboHang.addItem(s);
        }


        // các nút chức năng, trường nhập liệu
        JPanel Panel_left = new JPanel(new GridLayout(8,2,1,10));
        JLabel MaXelbl = new JLabel("Nhập mã xe: ");
        JTextField txtMaXe = new JTextField(18);
        JLabel TenXelbl = new JLabel("Nhập tên xe: ");
        JTextField txtTenXe = new JTextField(18);
        JLabel MaHanglbl = new JLabel("Nhập mã hãng: ");
        //JTextField txtMaHang = new JTextField(15);
        JLabel Gialbl = new JLabel("Nhập Giá bán: ");
        JTextField txtGia = new JTextField(18);
        JLabel sllbl = new JLabel("Nhập số lượng: ");
        JTextField txtsl = new JTextField(18);




        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm kiếm");
        btnLamMoi = new JButton("Làm mới");
        btnChitiet = new JButton("Chi Tiết");
        Panel_left.add(MaXelbl);Panel_left.add(txtMaXe);
        Panel_left.add(TenXelbl);Panel_left.add(txtTenXe);
        Panel_left.add(MaHanglbl);Panel_left.add(cboHang);
        Panel_left.add(Gialbl);Panel_left.add(txtGia);
        Panel_left.add(sllbl);Panel_left.add(txtsl);
        Panel_left.add(btnThem);
        Panel_left.add(btnSua);
        Panel_left.add(btnXoa);
        Panel_left.add(btnTimKiem);
        Panel_left.add(btnLamMoi);
        Panel_left.add(btnChitiet);

        add(Panel_left, BorderLayout.WEST);

        // // === SỰ KIỆN TẠM (chỉ load dữ liệu) ===
        // loadData();

            // sự kiện click vào sản phẩm sẽ tự động điền vào Jtextfile
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int selectedRow = table.getSelectedRow(); // Lấy chỉ số hàng được chọn
                    if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
                        txtMaXe.setText(model.getValueAt(selectedRow, 0).toString()); 
                        txtTenXe.setText(model.getValueAt(selectedRow, 1).toString()); 
                        // Lấy phần tử bạn muốn tìm kiếm (dữ liệu từ JTable)
                        String searchValue = model.getValueAt(selectedRow, 2).toString();
                        // Lấy mô hình của JComboBox
                        DefaultComboBoxModel modelCombo = (DefaultComboBoxModel) cboHang.getModel();
                        // Duyệt qua tất cả các phần tử trong JComboBox
                        for (int i = 0; i < modelCombo.getSize(); i++) {
                            String item = modelCombo.getElementAt(i).toString();
                            
                            // Kiểm tra nếu phần tử trong JComboBox chứa chuỗi bạn tìm kiếm
                            if (item.contains(searchValue)) {
                                // Nếu tìm thấy, chọn mục đó trong JComboBox
                                cboHang.setSelectedItem(item);
                                break; // Dừng vòng lặp khi tìm thấy
                            }
                        }
                        String giaHienThi = table.getValueAt(selectedRow, 3).toString();
                        // Bỏ dấu chấm và chữ " VND" nếu có
                        giaHienThi = giaHienThi.replaceAll("[^\\d]", ""); 
                        txtGia.setText(giaHienThi);  // → ví dụ: "1000000"
                        txtsl.setText(model.getValueAt(selectedRow, 4).toString());
                    }
                }
            });
    

        // sự kiện thêm xe
        btnThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn thêm sản phẩm này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
                );
                if(option == JOptionPane.YES_OPTION){
                String maxe = txtMaXe.getText();
                String tenxe = txtTenXe.getText();
                String mahang = (String) cboHang.getSelectedItem();
                String Gia = txtGia.getText();
                String sl =txtsl.getText();
                if (txtMaXe.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mã xe không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtTenXe.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tên xe không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtGia.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Giá xe không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtsl.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "SL xe không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(SanPhamBUS.themSanPham(maxe,tenxe,mahang,Gia,sl)){
                    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData();
                }
                else{JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);}

                }
                else {
                    // Nếu chọn "Không", không làm gì cả
                    JOptionPane.showMessageDialog(null, "Hành động bị hủy.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        );

        // sự kiện sửa xe
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn update sản phẩm này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                    );
                    if(option == JOptionPane.YES_OPTION){
                    String maxe = txtMaXe.getText();
                    String tenxe = txtTenXe.getText();
                    String mahang = (String) cboHang.getSelectedItem();
                    String Gia = txtGia.getText();
                    String sl =txtsl.getText();
                    if(SanPhamBUS.sua(maxe, tenxe, mahang, Gia, sl)){
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadTableData();
                    }
            }
        }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                    int option = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn xóa này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                    );
                    if(option == JOptionPane.YES_OPTION){
                    String maxe = txtMaXe.getText();
                    if(SanPhamBUS.xoa(maxe)){
                        JOptionPane.showMessageDialog(null, "xóa sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadTableData();
                    }
            }}
        });

        btnTimKiem.addActionListener((new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String tenxe = txtTenXe.getText();
                List<SanPhamDTO> dstemp = SanPhamBUS.seachname(tenxe);
                model.setRowCount(0);
                for (SanPhamDTO sp : dstemp) {
                    model.addRow(new Object[]{sp.getMaxe(),sp.getTenxe(),sp.getMaHang(),FormatUtil.formatCurrency(sp.getGia()),sp.getSl()});
                }
            }
        }));
         loadTableData();

         btnChitiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String maXe = txtMaXe.getText();
                new ChiTietSanPham_GUI(maXe).setVisible(true);
                
                
            }
        });

    }


    public void loadTableData() { 
        
        model.setRowCount(0);//xóa hết dữ liệu trong Jtable
        List<SanPhamDTO> listSP =SanPhamBUS.getAllSanPham();
        for (SanPhamDTO sp : listSP) {
            model.addRow(new Object[]{sp.getMaxe(),sp.getTenxe(),sp.getMaHang(),FormatUtil.formatCurrency(sp.getGia()),sp.getSl()});
        }

     
    }


}

