package DTO;

public class SanPhamDTO {
    private String Maxe;
    private String Tenxe;
    private String MaHang;
    private Double Gia;
    private int sl;
    public SanPhamDTO(String Maxe,String Tenxe,String MaHang,Double Gia,int sl){
        this.Maxe = Maxe;
        this.Tenxe = Tenxe;
        this.MaHang = MaHang;
        this.Gia = Gia;
        this.sl = sl;
    }
    public String getMaxe() {
        return Maxe;
    }
    public void setMaxe(String maxe) {
        Maxe = maxe;
    }
    public String getTenxe() {
        return Tenxe;
    }
    public void setTenxe(String tenxe) {
        Tenxe = tenxe;
    }
    public String getMaHang() {
        return MaHang;
    }
    public void setMaHang(String maHang) {
        MaHang = maHang;
    }
    public Double getGia() {
        return Gia;
    }
    public void setGia(Double gia) {
        Gia = gia;
    }
    public int getSl() {
        return sl;
    }
    public void setSl(int sl) {
        this.sl = sl;
    }


    
}
