package BUS;
import java.util.*;

import javax.swing.JOptionPane;

import DAO.HangxeDAO;
import DTO.HangxeDTO;
public class HangxeBUS {
    private HangxeDAO dao = new HangxeDAO();
// xử lý dữ liệu, biến thành list String gồm "mã hãng xe (Tên hãng xe)". để tiện sử dụng cho jcombox

    public List<String> getAllHang_Display() {
        List<String> displayList = new ArrayList<>();
        List<HangxeDTO> rawList = dao.getAll();
        for (HangxeDTO hx : rawList) {
            displayList.add(hx.getMaHang() + " (" + hx.getTenHang() + ")");
        }
        return displayList;
    }
    public boolean them(String maHang, String tenHang){
        if(dao.them(new HangxeDTO(maHang, tenHang))){
            return true;
        }
        else{return false;}
    }
}
