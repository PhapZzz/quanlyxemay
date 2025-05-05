package BUS;

import DAO.NhanVienDAO;
import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class PhieuNhapBUS {
    public static boolean themPhieuNhap(String maPhieuNhap, String maNCC, int maNV, Date ngayNhap) {
        NhanVienDAO nv = new NhanVienDAO();
        if(nv.checkmanv(maNV)){
        PhieuNhapDTO pn = new PhieuNhapDTO(maPhieuNhap, maNCC, maNV, ngayNhap);
        return PhieuNhapDAO.themPhieuNhap(pn);}
        else{
            JOptionPane.showMessageDialog(null, "không tồn tại mã nhân viên");
            return false;
        }
    }

    public static List<PhieuNhapDTO> getAllPhieuNhap() {
        return PhieuNhapDAO.getAllPhieuNhap();
    }
}
