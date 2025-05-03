package BUS;
import java.text.Format;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.ChitietSanPhamDAO;
import DAO.SanPhamDAO;
import DTO.ChitietSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.FormatUtil;
public class ChitietSanPham_BUS {
    private ChitietSanPhamDAO dao;
    public ChitietSanPham_BUS() {
        dao = new ChitietSanPhamDAO();
    }
    
    public List<ChitietSanPhamDTO> getAll(String maxe) {
        return dao.getAllChiTietXe(maxe);
        
    }
    public boolean themSanPham(String MaChiTietXe, String MaXe, String SoKhung, String SoMay, String TrangThai,String MaPhieuNhap,String color,String img) {
        // Có thể thêm kiểm tra trùng mã, validate dữ liệu ở đây
        if(dao.checkmaChiTiet(MaChiTietXe)){
            JOptionPane.showMessageDialog(null, "Mã xe bị trùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // xử lý dữ liệu của ma hãng
       

        ChitietSanPhamDTO spnew = new ChitietSanPhamDTO(MaChiTietXe, MaXe, SoKhung, SoMay, TrangThai, MaPhieuNhap, color, img);
        return dao.insert(spnew);
    }
    public boolean suaSanPham(String MaChiTietXe, String MaXe, String SoKhung, String SoMay, String TrangThai,String MaPhieuNhap,String color,String img){
        ChitietSanPhamDTO spnew = new ChitietSanPhamDTO(MaChiTietXe, MaXe, SoKhung, SoMay, TrangThai, MaPhieuNhap, color, img);
        return dao.update(spnew);
    }

    public boolean xoa(String machitietxe){
        if(dao.checkmaChiTiet(machitietxe)){
            if(dao.delete(machitietxe)){return true;}
            else{
                JOptionPane.showMessageDialog(null, "không xóa được xe", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;}
        }
        else{
            JOptionPane.showMessageDialog(null, "không tìm thấy xe", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }







}
