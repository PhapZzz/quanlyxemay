package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;

public class NhaCungCap_GUI extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMa, txtTen, txtDiaChi, txtSDT;
    private JComboBox<String> cboSearch;
    private NhaCungCapBUS nccBUS = new NhaCungCapBUS();

    public NhaCungCap_GUI() {
        setLayout(new BorderLayout(5, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("DANH SÁCH NHÀ CUNG CẤP", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã NCC", "Tên NCC", "Địa chỉ", "SĐT"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Form nhập liệu
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMa = new JTextField(18);
        txtTen = new JTextField(18);
        txtDiaChi = new JTextField(18);
        txtSDT = new JTextField(18);

        int row = 0;
        addFormRow(formPanel, gbc, row++, new JLabel("Mã NCC:"), txtMa);
        addFormRow(formPanel, gbc, row++, new JLabel("Tên NCC:"), txtTen);
        addFormRow(formPanel, gbc, row++, new JLabel("Địa chỉ:"), txtDiaChi);
        addFormRow(formPanel, gbc, row++, new JLabel("SĐT:"), txtSDT);

        // Nút chức năng
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 15, 15));

        RoundButton btnThem = new RoundButton("Thêm", 30); //tên và độ cong viền
        btnThem.setBackground(Color.WHITE);     // màu nền
        btnThem.setFont(12, Color.BLACK); // màu chữ
        btnThem.setButtonSize(125, 25); // size
        btnThem.setBorderWidth(1); // độ dày của viền

        RoundButton btnSua = new RoundButton("Sửa",30);
        btnSua.setBackground(Color.WHITE); 
        btnSua.setFont(12, Color.BLACK); 
        btnSua.setButtonSize(125, 25);
        btnSua.setBorderWidth(1);

        RoundButton btnXoa = new RoundButton("Xóa",30);
        btnXoa.setBackground(Color.WHITE); 
        btnXoa.setFont(12, Color.BLACK); 
        btnXoa.setButtonSize(125, 25);
        btnXoa.setBorderWidth(1);

        RoundButton btnTimKiem = new RoundButton("Tìm kiếm",30);
        btnTimKiem.setBackground(Color.WHITE); 
        btnTimKiem.setFont(12, Color.BLACK); 
        btnTimKiem.setButtonSize(125, 25);
        btnTimKiem.setBorderWidth(1);

        RoundButton btnLamMoi = new RoundButton("Làm mới",30);
        btnLamMoi.setBackground(Color.WHITE); 
        btnLamMoi.setFont(12, Color.BLACK); 
        btnLamMoi.setButtonSize(125, 25);
        btnLamMoi.setBorderWidth(1);
        

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnLamMoi);
        

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.add(formPanel);
        add(wrapper, BorderLayout.WEST);

        // ====== SỰ KIỆN ======
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                if (i != -1) {
                    txtMa.setText(model.getValueAt(i, 0).toString());
                    txtTen.setText(model.getValueAt(i, 1).toString());
                    txtDiaChi.setText(model.getValueAt(i, 2).toString());
                    txtSDT.setText(model.getValueAt(i, 3).toString());
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    boolean success = nccBUS.them(
                        txtMa.getText(), txtTen.getText(), txtDiaChi.getText(), txtSDT.getText()
                    );
                    if (success) {
                        showMessage("Thêm thành công!");
                        loadTableData();
                    } else {
                        showMessage("Thêm thất bại!");
                    }
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    boolean success = nccBUS.sua(
                        txtMa.getText(), txtTen.getText(), txtDiaChi.getText(), txtSDT.getText());
                    if (success) {
                        showMessage("Sửa thành công!");
                        loadTableData();
                    } else {
                        showMessage("Sửa thất bại!");
                    }
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = nccBUS.xoa(txtMa.getText());
                    if (success) {
                        showMessage("Xóa thành công!");
                        loadTableData();
                    } else {
                        showMessage("Xóa thất bại!");
                    }
                }
            }
        });

        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<NhaCungCapDTO> result = nccBUS.timKiem(txtTen.getText());
                model.setRowCount(0);
                for (NhaCungCapDTO ncc : result) {
                    model.addRow(new Object[]{ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSdt()});
                }
            }
        });

        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                loadTableData();
            }
        });
    }


    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, JLabel label, JTextField field) {
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private boolean validateFields() {
        if (txtMa.getText().trim().isEmpty() ||
            txtTen.getText().trim().isEmpty() ||
            txtDiaChi.getText().trim().isEmpty() ||
            txtSDT.getText().trim().isEmpty()) {
            showMessage("Vui lòng điền đầy đủ thông tin!");
            return false;
        }
        return true;
    }

    private void loadTableData() {
        model.setRowCount(0);
        List<NhaCungCapDTO> list = nccBUS.getAll();
        for (NhaCungCapDTO ncc : list) {
            model.addRow(new Object[]{ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSdt()});
        }
    }
}
