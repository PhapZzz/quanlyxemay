package BUS;
import java.text.Format;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import GUI.FormatUtil;

public class SanPhamBUS {
    private SanPhamDAO dao;

    public SanPhamBUS() {
        dao = new SanPhamDAO();
    }

    public List<SanPhamDTO> getAllSanPham() {
        return dao.getAll();
    }

    public boolean themSanPham(String masp, String tensp, String mahang, String Gia, String Sl) {
        // Có thể thêm kiểm tra trùng mã, validate dữ liệu ở đây
        if(dao.checkmasp(masp)){
            JOptionPane.showMessageDialog(null, "Mã xe bị trùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // xử lý dữ liệu của ma hãng
        String Mahang = mahang.split(" ")[0];
        Double gia = Double.parseDouble(Gia);
        int sl = Integer.parseInt(Sl);
        SanPhamDTO spnew = new SanPhamDTO(masp,tensp,Mahang,gia,sl);
        return dao.insert(spnew);
    }

    public boolean sua(String masp, String tensp, String mahang, String Gia, String Sl){
        if(dao.checkmasp(masp)){
            String Mahang = mahang.split(" ")[0];
            Double gia = Double.parseDouble(Gia);
            int sl = Integer.parseInt(Sl);
            SanPhamDTO spnew = new SanPhamDTO(masp,tensp,Mahang,gia,sl);
            if(dao.update(spnew)){return true;}
            else{
                JOptionPane.showMessageDialog(null, "không sửa được xe", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;}
        }
        else{
            JOptionPane.showMessageDialog(null, "không tìm thấy xe", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean xoa(String masp){
        if(dao.checkmasp(masp)){
            if(dao.delete(masp)){return true;}
            else{
                JOptionPane.showMessageDialog(null, "không xóa được xe", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;}
        }
        else{
            JOptionPane.showMessageDialog(null, "không tìm thấy xe", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<SanPhamDTO> seachname(String name) {
        
        return dao.searchByName(name);
    }
}


