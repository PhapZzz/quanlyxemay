package DTO;

import java.util.Date;

public class PhieuNhapDTO {
    private String maPhieuNhap;
    private String maNCC;
    private int maNV;
    private Date ngayNhap;

    public PhieuNhapDTO(String maPhieuNhap, String maNCC, int maNV, Date ngayNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
