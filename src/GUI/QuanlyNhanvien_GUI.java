package GUI;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuanlyNhanvien_GUI extends JPanel {
   public QuanlyNhanvien_GUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Đây là trang nhân viên"));
   }
}
