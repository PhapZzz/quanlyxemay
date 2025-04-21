package DTO;

public class ChitietSanPhamDTO {
    private String MachitietXe;
    private String Maxe;
    private String SoKhung;
    private String SoMay;
    private String TrangThai;
    private String MaPhieuNhap;
    private String color;
    private String img;
    public ChitietSanPhamDTO(String machitietxe, String maxe,String sokhung,String somay,String TrangThai, String maphieunhap, String color, String img){
        this.MachitietXe = machitietxe;
        this.Maxe = maxe;
        this.SoKhung = sokhung;
        this.SoMay = somay;
        this.TrangThai = TrangThai;
        this.MaPhieuNhap = maphieunhap;
        this.color = color;
        this.img = img;
    }
    public String getMachitietXe() {
        return MachitietXe;
    }
    public void setMachitietXe(String machitietXe) {
        MachitietXe = machitietXe;
    }
    public String getMaxe() {
        return Maxe;
    }
    public void setMaxe(String maxe) {
        Maxe = maxe;
    }
    public String getSoKhung() {
        return SoKhung;
    }
    public void setSoKhung(String soKhung) {
        SoKhung = soKhung;
    }
    public String getSoMay() {
        return SoMay;
    }
    public void setSoMay(String soMay) {
        SoMay = soMay;
    }
    public String getTrangThai() {
        return TrangThai;
    }
    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }
    public void setMaPhieuNhap(String maPhieuNhap) {
        MaPhieuNhap = maPhieuNhap;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }


}
