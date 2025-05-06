package DTO;
import java.util.Date;

public class chitietphieunhapDTO {
    private int maPhieuNhap;
    private Date ngayNhap;
    private String tenNCC;
    private String diaChi;
    private String sdt;
    private String tenNV;

    public chitietphieunhapDTO() {}

    public chitietphieunhapDTO(int maPhieuNhap, Date ngayNhap, String tenNCC, String diaChi, String sdt, String tenNV) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.tenNV = tenNV;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
}
