package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.List;

public class NhaCungCapBUS {
    private NhaCungCapDAO dao = new NhaCungCapDAO();

    public List<NhaCungCapDTO> getAll() {
        return dao.getAllNhaCungCap();
    }

    public boolean them(String ma, String ten, String diaChi, String sdt) {
        NhaCungCapDTO ncc = new NhaCungCapDTO(ma, ten, diaChi, sdt);
        return dao.them(ncc);
    }

    public boolean sua(String ma, String ten, String diaChi, String sdt) {
        NhaCungCapDTO ncc = new NhaCungCapDTO(ma, ten, diaChi, sdt);
        return dao.sua(ncc);
    }

    public boolean xoa(String ma) {
        return dao.xoa(ma);
    }

    public List<NhaCungCapDTO> timKiem(String ten) {
        return dao.timTheoTen(ten);
    }
}
