package BUS;
import java.util.List;

import DAO.DonHangDAO;
import DTO.DonHangDTO;

public class DonHangBUS {
    private DonHangDAO donHangDAO;

    public DonHangBUS() {
        donHangDAO = new DonHangDAO();
    }

    public List<DonHangDTO> getAllDonHang() {
        return donHangDAO.getAll();
    }

    public boolean themDonHang(DonHangDTO dh) {
        return donHangDAO.insert(dh);
    }

    public boolean suaDonHang(DonHangDTO dh) {
        return donHangDAO.update(dh);
    }

    public boolean xoaDonHang(String maDH) {
        return donHangDAO.delete(maDH);
    }

    public List<DonHangDTO> timKiemTheoTenKhachHang(String keyword) {
        return donHangDAO.searchByCustomerName(keyword);
    }
    public List<DonHangDTO> timKiemTheoNgay(String fromDate, String toDate) {
        return donHangDAO.searchByDateRange(fromDate, toDate);
    }
    
}
