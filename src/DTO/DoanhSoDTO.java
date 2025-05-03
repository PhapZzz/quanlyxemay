package DTO;

public class DoanhSoDTO {
    private String tenSanPham;
    private int soLuongBan;

    public DoanhSoDTO(String tenSanPham, int soLuongBan) {
        this.tenSanPham = tenSanPham;
        this.soLuongBan = soLuongBan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }
}
