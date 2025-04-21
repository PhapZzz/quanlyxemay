package DTO;

public class HangxeDTO {
    private String MaHang;
    private String TenHang;
    public HangxeDTO(String MaHang, String TenHang){
        this.MaHang = MaHang;
        this.TenHang = TenHang;

    }
    public String getMaHang() {
        return MaHang;
    }
    public void setMaHang(String maHang) {
        MaHang = maHang;
    }
    public String getTenHang() {
        return TenHang;
    }
    public void setTenHang(String tenHang) {
        TenHang = tenHang;
    }
}
