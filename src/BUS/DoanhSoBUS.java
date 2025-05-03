package BUS;

import DAO.DoanhSoDAO;
import DTO.DoanhSoDTO;

import java.util.*;

public class DoanhSoBUS {
    private DoanhSoDAO dao = new DoanhSoDAO();

    public List<DoanhSoDTO> laySanPhamBanChay() {
        try {
            return dao.layDanhSachSanPhamBanChay();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<DoanhSoDTO> laySanPhamBanChayTheoKhoang(String tuNgay, String denNgay) {
        try {
            return dao.laySanPhamBanChayTheoKhoang(tuNgay, denNgay);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    
}
