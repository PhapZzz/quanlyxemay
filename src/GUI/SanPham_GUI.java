package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import BUS.HangxeBUS;


public class SanPham_GUI extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private index mainFrame;
    private SanPhamBUS SanPhamBUS = new SanPhamBUS();
    private RoundButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi, btnChitiet;

    public SanPham_GUI(index frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ======= TITLE =======
        JLabel title = new JLabel("DANH SÁCH SẢN PHẨM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // ======= TABLE =======
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã xe", "Tên xe", "Hãng", "Giá", "Số lượng"});
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ======= PANEL LEFT (FORM) =======
        JPanel panelLeft = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        HangxeBUS hangBUS = new HangxeBUS();
        List<String> listHang = hangBUS.getAllHang_Display();

        // Trường nhập liệu
        JLabel lblMaXe = new JLabel("Nhập mã xe:");
        JTextField txtMaXe = new JTextField(18);

        JLabel lblTenXe = new JLabel("Nhập tên xe:");
        JTextField txtTenXe = new JTextField(18);

        JLabel lblMaHang = new JLabel("Chọn hãng:");
        JPanel combo = new JPanel();
        JComboBox<String> cboHang = new JComboBox<>();
        for (String s : listHang) {
            cboHang.addItem(s);
        }
        JButton themhang = new JButton("thêm");
        combo.add(cboHang);
        combo.add(themhang);
        

        JLabel lblGia = new JLabel("Nhập giá bán:");
        JTextField txtGia = new JTextField(18);

        JLabel lblSL = new JLabel("Nhập số lượng:");
        JTextField txtSL = new JTextField(18);

        int row = 0;
        addFormRow(panelLeft, gbc, row++, lblMaXe, txtMaXe);
        addFormRow(panelLeft, gbc, row++, lblTenXe, txtTenXe);
        addFormRow(panelLeft, gbc, row++, lblMaHang, combo);
        addFormRow(panelLeft, gbc, row++, lblGia, txtGia);
        addFormRow(panelLeft, gbc, row++, lblSL, txtSL);

        // ======= BUTTONS =======
        JPanel panelButton = new JPanel(new GridLayout(3, 2, 15, 15));
        //btnThem = new JButton("Thêm");
        //JButtonDAO btnThem = new JButtonDAO("Thêm");
        btnThem = new RoundButton("Thêm", 30); //tên và độ cong viền
        btnThem.setBackground(Color.WHITE);     // màu nền
        btnThem.setFont(12, Color.BLACK); // màu chữ
        btnThem.setButtonSize(130, 25); // size
        btnThem.setBorderWidth(1); // độ dày của viền

        btnSua = new RoundButton("Sửa",30);
        btnSua.setBackground(Color.WHITE); 
        btnSua.setFont(12, Color.BLACK); 
        btnSua.setButtonSize(130, 25);
        btnSua.setBorderWidth(1);

        btnXoa = new RoundButton("Xóa",30);
        btnXoa.setBackground(Color.WHITE); 
        btnXoa.setFont(12, Color.BLACK); 
        btnXoa.setButtonSize(130, 25);
        btnXoa.setBorderWidth(1);

        btnTimKiem = new RoundButton("Tìm kiếm",30);
        btnTimKiem.setBackground(Color.WHITE); 
        btnTimKiem.setFont(12, Color.BLACK); 
        btnTimKiem.setButtonSize(130, 25);
        btnTimKiem.setBorderWidth(1);

        btnLamMoi = new RoundButton("Làm mới",30);
        btnLamMoi.setBackground(Color.WHITE); 
        btnLamMoi.setFont(12, Color.BLACK); 
        btnLamMoi.setButtonSize(130, 25);
        btnLamMoi.setBorderWidth(1);

        btnChitiet = new RoundButton("Chi tiết",30);
        btnChitiet.setBackground(Color.WHITE); 
        btnChitiet.setFont(12, Color.BLACK); 
        btnChitiet.setButtonSize(130, 25);
        btnChitiet.setBorderWidth(1);

        panelButton.add(btnThem);
        panelButton.add(btnSua);
        panelButton.add(btnXoa);
        panelButton.add(btnTimKiem);
        panelButton.add(btnLamMoi);
        panelButton.add(btnChitiet);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panelLeft.add(panelButton, gbc);

        // Bọc Panel_left vào 1 JPanel dùng FlowLayout căn top
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        wrapperPanel.add(panelLeft);

        add(wrapperPanel, BorderLayout.WEST);   

        // ======= SỰ KIỆN =======
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtMaXe.setText(model.getValueAt(selectedRow, 0).toString());
                    txtTenXe.setText(model.getValueAt(selectedRow, 1).toString());
                    String searchValue = model.getValueAt(selectedRow, 2).toString();
                    DefaultComboBoxModel<String> modelCombo = (DefaultComboBoxModel<String>) cboHang.getModel();
                    for (int i = 0; i < modelCombo.getSize(); i++) {
                        if (modelCombo.getElementAt(i).contains(searchValue)) {
                            cboHang.setSelectedItem(modelCombo.getElementAt(i));
                            break;
                        }
                    }
                    String giaHienThi = model.getValueAt(selectedRow, 3).toString().replaceAll("[^\\d]", "");
                    txtGia.setText(giaHienThi);
                    txtSL.setText(model.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        btnThem.addActionListener(e -> {
            if (confirmAction("Bạn có chắc chắn muốn thêm sản phẩm này?")) {
                if (validateFields(txtMaXe, txtTenXe, txtGia, txtSL)) {
                    if (SanPhamBUS.themSanPham(
                        txtMaXe.getText(), txtTenXe.getText(), (String) cboHang.getSelectedItem(), txtGia.getText(), txtSL.getText())) {
                        showMessage("Thêm sản phẩm thành công!");
                        loadTableData();
                    } else {
                        showMessage("Thêm sản phẩm thất bại!");
                    }
                }
            }
        });
        themhang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JTextField tfMaHang = new JTextField();
        JTextField tfTenHang = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Mã hãng:"));
        panel.add(tfMaHang);
        panel.add(new JLabel("Tên hãng:"));
        panel.add(tfTenHang);

        int result = JOptionPane.showConfirmDialog(null, panel, "Thêm hãng mới",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String maHang = tfMaHang.getText().trim();
            String tenHang = tfTenHang.getText().trim();

            if (maHang.isEmpty() || tenHang.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Gọi hàm thêm vào cơ sở dữ liệu ở đây
            HangxeBUS hangbus = new HangxeBUS();
            boolean success = hangbus.them(maHang, tenHang);

            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm hãng thành công!");
                cboHang.removeAllItems(); // Xóa tất cả item cũ
                List<String> updatedListHang = hangBUS.getAllHang_Display();
                for (String s : updatedListHang) {
                    cboHang.addItem(s); // Thêm lại hãng mới
                }
            } else {
                JOptionPane.showMessageDialog(null, "Thêm hãng thất bại!");
            }
        }
    }
        });

        btnSua.addActionListener(e -> {
            if (confirmAction("Bạn có chắc chắn muốn update sản phẩm này?")) {
                if (validateFields(txtMaXe, txtTenXe, txtGia, txtSL)) {
                    if (SanPhamBUS.sua(
                        txtMaXe.getText(), txtTenXe.getText(), (String) cboHang.getSelectedItem(), txtGia.getText(), txtSL.getText())) {
                        showMessage("Sửa sản phẩm thành công!");
                        loadTableData();
                    }
                }
            }
        });

        btnXoa.addActionListener(e -> {
            if (confirmAction("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
                if (SanPhamBUS.xoa(txtMaXe.getText())) {
                    showMessage("Xóa sản phẩm thành công!");
                    loadTableData();
                }
            }
        });

        btnTimKiem.addActionListener(e -> {
            List<SanPhamDTO> dstemp = SanPhamBUS.seachname(txtTenXe.getText());
            model.setRowCount(0);
            for (SanPhamDTO sp : dstemp) {
                model.addRow(new Object[]{sp.getMaxe(), sp.getTenxe(), sp.getMaHang(), FormatUtil.formatCurrency(sp.getGia()), sp.getSl()});
            }
        });

        btnChitiet.addActionListener(e -> {
            new ChiTietSanPham_GUI(txtMaXe.getText()).setVisible(true);
        });

        btnLamMoi.addActionListener(e -> {
            txtMaXe.setText("");
            txtTenXe.setText("");
            txtGia.setText("");
            txtSL.setText("");
            cboHang.setSelectedIndex(0);
            loadTableData();
        });

        // Load dữ liệu ban đầu
        loadTableData();
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

    private void loadTableData() {
        model.setRowCount(0);
        List<SanPhamDTO> listSP = SanPhamBUS.getAllSanPham();
        for (SanPhamDTO sp : listSP) {
            model.addRow(new Object[]{sp.getMaxe(), sp.getTenxe(), sp.getMaHang(), FormatUtil.formatCurrency(sp.getGia()), sp.getSl()});
        }
    }
}
